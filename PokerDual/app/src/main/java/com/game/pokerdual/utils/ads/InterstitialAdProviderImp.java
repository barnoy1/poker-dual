package com.game.pokerdual.utils.ads;

import android.content.Context;

import com.game.pokerdual.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import javax.inject.Inject;

public class InterstitialAdProviderImp implements InterstitialAdProvider {

    //region Fields
    private final static String TAG = InterstitialAdProviderImp.class.getSimpleName();

    private Context context;
    private InterstitialAd interstitialAd;
    private static final int AD_NOT_READY_ERR_CODE = -1000;
    private static String adUnitID = null;

    //endregion

    //region ctor

    @Inject
    public InterstitialAdProviderImp(Context context) {

        this.context = context;

        adUnitID = this.context.getString(R.string
                .interstitial_ad_unit_id);
    }

    //endregion

    //region InterstitialAdProvider interface implementation

    @Override
    public void createInterstitialAd(AdListener adListener){

         createInterstitialAdInternal(adListener);
    }

    @Override
    public void showInterstitial() {
        interstitialAd.show();
    }

    @Override
    public boolean IsInterstitialLoaded() {
        return interstitialAd != null && interstitialAd.isLoaded();
    }

    //endregion

    // region Private Methods

    private void createInterstitialAdInternal(AdListener adListener) {

        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId(adUnitID);
        interstitialAd.loadAd(new AdRequest.Builder().build());

        interstitialAd.setAdListener(adListener);
    }

    @Override
    public void loadInterstitial() {

        AdRequest adRequest = new AdRequest.Builder()
                /*
                .addTestDevice(this.context.getString
                        (R.string.android_test_device_id))
               */
                .build();

        interstitialAd.loadAd(adRequest);
    }

    //endregion

}
