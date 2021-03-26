package com.game.pokerdual.ui.main;

import android.os.Build;
import android.util.Pair;

import androidx.annotation.RequiresApi;

import com.game.pokerdual.R;
import com.game.pokerdual.data.DataManager;
import com.game.pokerdual.data.json.structs.Elixir;
import com.game.pokerdual.data.json.structs.Item;
import com.game.pokerdual.data.json.structs.StoreInventory;
import com.game.pokerdual.ui.base.BasePresenter;
import com.game.pokerdual.ui.splash.SplashMvpPresenter;
import com.game.pokerdual.ui.splash.SplashMvpView;
import com.game.pokerdual.utils.AssetLoader.AssetLoadManager;
import com.game.pokerdual.utils.AssetLoader.AssetLoaderProperties;
import com.game.pokerdual.utils.AssetLoader.AssetLoaderPropertiesBuilder;
import com.game.pokerdual.utils.AssetLoader.InterstitialAdProperties.InterstitialAdPropertiesBuilder;
import com.game.pokerdual.utils.AssetLoader.StorageDownableContent.StorageItemProperties;
import com.game.pokerdual.utils.AssetLoader.StorageDownableContent.StorageItemPropertiesBuilder;
import com.game.pokerdual.utils.GsonUtils;
import com.game.pokerdual.utils.Logger;
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

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {


    @Inject
    public MainPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable,
                         AssetLoadManager assetLoadManager) {

        super(dataManager, schedulerProvider, compositeDisposable, assetLoadManager);

    }


    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }

}
