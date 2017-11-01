package com.game.pokerdual.utils.AssetLoader;

import com.game.pokerdual.utils.ads.InterstitialAdProvider;

import java.io.File;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;


public interface AssetLoadManager  {

    void configure(AssetLoaderProperties assetLoaderProperties);

    CompositeDisposable getCompositeDisposable();

    InterstitialAdProvider getInterstitialAdProvider();

    boolean isAssetsLoaded();

    Map<String, File> getmStorageItemMap();


}
