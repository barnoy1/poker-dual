package com.game.pokerdual.utils.AssetLoader;

import com.game.pokerdual.utils.AssetLoader.InterstitialAdProperties.InterstitialAdProperties;
import com.game.pokerdual.utils.AssetLoader.StorageDownableContent.StorageItemProperties;

import java.util.ArrayList;

public class AssetLoaderProperties {

    public InterstitialAdProperties getInterstitialAdProperties() {
        return interstitialAdProperties;
    }

    protected InterstitialAdProperties interstitialAdProperties;

    public ArrayList<StorageItemProperties> getStorageItemPropertiesArrayList() {
        return storageItemPropertiesArrayList;
    }

    protected ArrayList<StorageItemProperties> storageItemPropertiesArrayList;
}
