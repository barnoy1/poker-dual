package com.game.pokerdual.utils.AssetLoader;

import android.annotation.SuppressLint;

import com.game.pokerdual.utils.AppLogger;
import com.game.pokerdual.utils.AssetLoader.InterstitialAdProperties.InterstitialAdProperties;
import com.game.pokerdual.utils.AssetLoader.ReferenceCounter.ReferenceCounter;
import com.game.pokerdual.utils.AssetLoader.StorageDownableContent.AppStorageItem;
import com.game.pokerdual.utils.AssetLoader.StorageDownableContent.StorageItemProperties;
import com.game.pokerdual.utils.ads.InterstitialAdProvider;
import com.game.pokerdual.utils.rx.SchedulerProvider;
import com.google.android.gms.ads.AdListener;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class AppAssetLoadManager implements AssetLoadManager {

    private CompositeDisposable mCompositeDisposable;
    private SchedulerProvider mSchedulerProvider;
    private ReferenceCounter mReferenceCounter;
    private InterstitialAdProvider mInterstitialAdProvider;
    private ArrayList<StorageItemProperties> mStorageItemPropertiesArrayList;
    private int fillAdCounter = 3;
    private Map<String, File> mStorageItemMap = new HashMap<>();

    private static String TAG = AppAssetLoadManager.class.getSimpleName();
    private final static int FILL_AD_COUNTER_LIMIT = 3;
    private InterstitialAdProperties mInterstitialAdProperties;


    @Override
    public void configure(AssetLoaderProperties assetLoaderProperties) {
        mInterstitialAdProperties = assetLoaderProperties.interstitialAdProperties;
        if (mInterstitialAdProperties != null) {
            createInterstitialAd(mInterstitialAdProperties);
        }
        mStorageItemPropertiesArrayList = assetLoaderProperties.storageItemPropertiesArrayList;
        if (mStorageItemPropertiesArrayList != null) {
            downloadAssets(mStorageItemPropertiesArrayList);
        }

    }

    @SuppressLint("CheckResult")
    public void createInterstitialAd(InterstitialAdProperties interstitialAdProperties) {
        this.mInterstitialAdProperties = interstitialAdProperties;

        ProgressBarObserverable()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())

                .subscribe(
                        mInterstitialAdProperties.onNext,
                        mInterstitialAdProperties.onError,
                        mInterstitialAdProperties.onComplete);

        createInterstitialAdInternal();
    }

    @Override
    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }


    @Override
    public InterstitialAdProvider getInterstitialAdProvider() {
        return mInterstitialAdProvider;
    }

    @Override
    public boolean isAssetsLoaded() {
        return mReferenceCounter.IsRefCounterZero();
    }

    @Override
    public Map<String, File> getmStorageItemMap() {
        return mStorageItemMap;
    }

    @Inject
    public AppAssetLoadManager(InterstitialAdProvider interstitialAdProvider,
                               ReferenceCounter referenceCounter,
                               SchedulerProvider schedulerProvider,
                               CompositeDisposable compositeDisposable) {
        this.mInterstitialAdProvider = interstitialAdProvider;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = compositeDisposable;
        this.mReferenceCounter = referenceCounter;
    }


    private Observable<Long> ProgressBarObserverable() {
        return Observable.intervalRange(
                mInterstitialAdProperties.startIntervalValue,
                mInterstitialAdProperties.endValueInterval,
                mInterstitialAdProperties.rangeInitDelay,
                mInterstitialAdProperties.rangePeriod,
                TimeUnit.MILLISECONDS);
    }

    // region InterstitialAd
    private void createInterstitialAdInternal() {

        // invoke ad request and callback
        mInterstitialAdProvider.createInterstitialAd(new AdListener() {
            @Override
            public void onAdLoaded() {
                String message = String.format("onAdLoaded - " +
                        "interstitial add loaded successfully");
                AppLogger.i(message);
                mReferenceCounter.DecreaseRefCounter();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {

                @SuppressLint("DefaultLocale") String message =
                        String.format("onAdFailedToLoad - " +
                                "error code: " +
                                "%d", errorCode);
                AppLogger.e(message);

                if (fillAdCounter <= FILL_AD_COUNTER_LIMIT) {
                    fillAdCounter++;
                    mInterstitialAdProvider.loadInterstitial();
                }
            }

            @Override
            public void onAdClosed() {
                String message = String.format("onAdClosed");
                AppLogger.i(message);


                // load the next interstitialAd
                mInterstitialAdProvider.loadInterstitial();

                if (mReferenceCounter.IsRefCounterZero()) {
                    try {
                        mInterstitialAdProperties.onAdClose.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    // endregion

    // region downloadable storage items
    private void downloadAssets(ArrayList<StorageItemProperties> mStorageItemPropertiesArrayList)
    {
        //Get ExecutorService from Executors utility class, thread pool size is 10
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (StorageItemProperties storageItemProperties : mStorageItemPropertiesArrayList) {
            executor.submit(new AppStorageItem(storageItemProperties));
        }

        //shut down the executor service now
        executor.shutdown();
    }
    //endregion
}
