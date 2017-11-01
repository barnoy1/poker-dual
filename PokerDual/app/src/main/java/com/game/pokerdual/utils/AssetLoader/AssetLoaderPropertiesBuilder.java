package com.game.pokerdual.utils.AssetLoader;

import com.game.pokerdual.utils.AssetLoader.InterstitialAdProperties.InterstitialAdPropertiesBuilder;
import com.game.pokerdual.utils.AssetLoader.StorageDownableContent.StorageItemProperties;

import java.util.ArrayList;

public class AssetLoaderPropertiesBuilder {

    private InterstitialAdPropertiesBuilder interstitialAdPropertiesBuilder;
    private ArrayList<StorageItemProperties> storageItemPropertiesArrayList;

    public AssetLoaderPropertiesBuilder DownableStorageItemPropertiesArrayList(ArrayList<StorageItemProperties> storageItemPropertiesArrayList) {
        this.storageItemPropertiesArrayList = storageItemPropertiesArrayList;
        return this;
    }

    public AssetLoaderPropertiesBuilder InterstitialAdPropertiesBuilder(InterstitialAdPropertiesBuilder interstitialAdPropertiesBuilder ) {
        this.interstitialAdPropertiesBuilder = interstitialAdPropertiesBuilder;
        return this;
    }



    public AssetLoaderProperties build () {
        AssetLoaderProperties assetLoaderProperties = new AssetLoaderProperties();
        assetLoaderProperties.storageItemPropertiesArrayList = storageItemPropertiesArrayList;
        assetLoaderProperties.interstitialAdProperties = interstitialAdPropertiesBuilder.build();

        return assetLoaderProperties;
    }
}