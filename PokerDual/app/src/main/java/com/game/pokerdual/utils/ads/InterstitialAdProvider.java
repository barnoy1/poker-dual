package com.game.pokerdual.utils.ads;

import com.google.android.gms.ads.AdListener;

public interface InterstitialAdProvider {

    void createInterstitialAd(AdListener adListener);

    void showInterstitial();

    void loadInterstitial();

    boolean IsInterstitialLoaded();



}
