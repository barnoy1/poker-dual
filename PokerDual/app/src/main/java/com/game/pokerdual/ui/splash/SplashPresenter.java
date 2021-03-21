package com.game.pokerdual.ui.splash;

import android.os.Build;
import androidx.annotation.RequiresApi;
import android.util.Pair;

import com.game.pokerdual.R;
import com.game.pokerdual.data.DataManager;
import com.game.pokerdual.data.json.structs.Elixir;
import com.game.pokerdual.data.json.structs.Item;
import com.game.pokerdual.data.json.structs.StoreInventory;
import com.game.pokerdual.ui.base.BasePresenter;
import com.game.pokerdual.utils.Logger;
import com.game.pokerdual.utils.AssetLoader.AssetLoadManager;
import com.game.pokerdual.utils.AssetLoader.AssetLoaderProperties;
import com.game.pokerdual.utils.AssetLoader.AssetLoaderPropertiesBuilder;
import com.game.pokerdual.utils.AssetLoader.InterstitialAdProperties.InterstitialAdPropertiesBuilder;
import com.game.pokerdual.utils.AssetLoader.StorageDownableContent.StorageItemProperties;
import com.game.pokerdual.utils.AssetLoader.StorageDownableContent.StorageItemPropertiesBuilder;
import com.game.pokerdual.utils.GsonUtils;
import com.game.pokerdual.utils.SchedulerProvider.SchedulerProvider;
import com.google.firebase.storage.FileDownloadTask;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

import static com.game.pokerdual.utils.GsonUtils.getGson;

/**
 * Created by ron on 9/27/2018.
 */

public class SplashPresenter<V extends SplashMvpView> extends BasePresenter<V>
        implements SplashMvpPresenter<V> {

    private final static String TAG = SplashPresenter.class.getSimpleName();

    private final long endValueInterval = 100;
    private AssetLoadManager mAssetLoadManager;
    private Random mSeed;

    private long mStatusProgressCounter = 0;

    final int min = 0;
    final int max = 100;
    final int random = new Random().nextInt((max - min) + 1) + min;
    ArrayList<String> mCaptionList;
    long mStatusProgressChangeRate = 0;
    int mCurrentCaptionIndex = 0;

    @Inject
    public SplashPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable,
                           AssetLoadManager assetLoadManager) {

        super(dataManager, schedulerProvider, compositeDisposable, assetLoadManager);


        this.mAssetLoadManager = getAssetLoadManager();

    }


    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);

        getMvpView().startSyncService();

        long startIntervalValue = 1;
        long rangePeriod = 75;
        long rangeInitDelay = 700;

        AssetLoaderProperties assetLoaderProperties = new AssetLoaderPropertiesBuilder()

                .InterstitialAdPropertiesBuilder(new InterstitialAdPropertiesBuilder()
                    .OnNext(this::OnNextIntervalAd)
                    .OnError(this::OnProgressBarErrorAd)
                    .OnComplete(this::OnProgressBarCompleteAd)
                    .OnAdClose(this::OnAdClose)

                    .StartIntervalValue(startIntervalValue)
                    .RangeInitDelay(rangeInitDelay)
                    .RangePeriod(rangePeriod)
                    .EndValueInterval(endValueInterval))


                .DownableStorageItemPropertiesArrayList(new ArrayList<StorageItemProperties>() {{

                    add(new StorageItemPropertiesBuilder()
                            .RelPath("store.json")
                            .Url(getMvpView().getStringByID(R.string.fire_base_storage_url))
                            .OnNext(SplashPresenter.this::onAssetLoadProgressNext)
                            .OnError(SplashPresenter.this::onAssetLoadFailed)
                            .OnComplete(SplashPresenter.this::onAssetLoadComplete)
                        .build());

                }})

        .build();

        mCaptionList = new ArrayList<>(Arrays.asList(
            getMvpView().getStringByID(R.string.sneaking_aces_in_sleeves),
            getMvpView().getStringByID(R.string.marking_cards_in_deck),
            getMvpView().getStringByID(R.string.checking_inner_pockets),
            getMvpView().getStringByID(R.string.peeking_top_cards),
            getMvpView().getStringByID(R.string.using_cold_deck),
            getMvpView().getStringByID(R.string.false_shuffling_deck)
        ));

        java.util.Collections.shuffle(mCaptionList);
        mStatusProgressChangeRate = 100 / mCaptionList.size();

        mAssetLoadManager.configure(assetLoaderProperties);
    }

    // region OnAssetLoad Methods

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void onAssetLoadComplete(@NotNull Pair<String, File> storageItemFilePair){
        File localFile = storageItemFilePair.second;

        Logger.i("local tem file created: " + localFile.toString());

        if (localFile.canRead()){

            mAssetLoadManager.getmStorageItemMap()
                    .putIfAbsent(
                            storageItemFilePair.first,
                            storageItemFilePair.second);

            File file = mAssetLoadManager.getmStorageItemMap().get("store.json");
            String jsonData = GsonUtils.readJsonFile(Objects.requireNonNull(file));
            StoreInventory storeInventoryObject = getGson().fromJson(jsonData, StoreInventory.class);
            Item item = storeInventoryObject.getItems().get(0);
            Elixir elixir = storeInventoryObject.getElixirs().get(0);

        }
    }

    private void onAssetLoadFailed(@org.jetbrains.annotations.NotNull Exception exception)  {
        Logger.i("local tem file not created:" + exception.toString());
    }

    private void onAssetLoadProgressNext(@NotNull FileDownloadTask.TaskSnapshot taskSnapshot){
        // progress percentage
        double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
        Logger.i("download in progress" + String.valueOf(progress));
    }
    //endregion

    //region Interstitial Ad Observable

    private void OnAdClose() {
        getMvpView().openLoginActivity();
    }

    private void OnProgressBarCompleteAd() {

        // clear observable
        mAssetLoadManager.getCompositeDisposable().clear();

        getMvpView().updateProgressBar(endValueInterval);

        // present ad if it was loaded successfully
        if (mAssetLoadManager.getInterstitialAdProvider()
                .IsInterstitialLoaded())
        {
            mAssetLoadManager.getInterstitialAdProvider()
                    .showInterstitial();
        }
        else
        {
            if (mAssetLoadManager.isAssetsLoaded())
            {
                getMvpView().openLoginActivity();
            }
        }

    }

    private void OnProgressBarErrorAd(Throwable t) {

        if (isViewAttached()) {
            return;
        }
        getMvpView().onError(t.getMessage());
        OnProgressBarCompleteAd();
    }

    private void OnNextIntervalAd(Long progress){


        if (mAssetLoadManager.getInterstitialAdProvider()
                .IsInterstitialLoaded()){
            OnProgressBarCompleteAd();
            return;
        }

        if ((progress <= endValueInterval)) {
            getMvpView().updateProgressBar(progress);

            if ( progress % mStatusProgressChangeRate == 0) {
                getMvpView().updateTextViewProgressBar(mCaptionList.get(mCurrentCaptionIndex));
                mCurrentCaptionIndex++;
            }

            /*
            if (mStatusProgressCounter == 0){
                long mStatusProgressChangeRate = 10;
                mStatusProgressCounter = mStatusProgressChangeRate;

                getMvpView().updateTextViewProgressBar(generateStatusBar());
            }
            mStatusProgressCounter--;
            */

        } else {
            OnProgressBarCompleteAd();
        }
    }

    //endregion

}
