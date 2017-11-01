package com.game.pokerdual.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.firebase.authwrapper.manager.ProviderManager;
import com.firebase.authwrapper.providers.common.properties.ProviderProperties;
import com.game.pokerdual.di.ActivityContext;
import com.game.pokerdual.di.PerActivity;
import com.game.pokerdual.ui.login.LoginMvpPresenter;
import com.game.pokerdual.ui.login.LoginMvpView;
import com.game.pokerdual.ui.login.LoginPresenter;
import com.game.pokerdual.ui.splash.SplashMvpPresenter;
import com.game.pokerdual.ui.splash.SplashMvpView;
import com.game.pokerdual.ui.splash.SplashPresenter;
import com.game.pokerdual.ui.store.StoreMvpPresenter;
import com.game.pokerdual.ui.store.StoreMvpView;
import com.game.pokerdual.ui.store.StorePresenter;
import com.game.pokerdual.utils.AssetLoader.AppAssetLoadManager;
import com.game.pokerdual.utils.AssetLoader.AssetLoadManager;
import com.game.pokerdual.utils.AssetLoader.ReferenceCounter.AppReferenceCounter;
import com.game.pokerdual.utils.AssetLoader.ReferenceCounter.ReferenceCounter;
import com.game.pokerdual.utils.ads.AppInterstitialAdProvider;
import com.game.pokerdual.utils.ads.InterstitialAdProvider;
import com.game.pokerdual.utils.rx.AppSchedulerProvider;
import com.game.pokerdual.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ron on 8/18/2018.
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    InterstitialAdProvider provideInterstitialAdProvider(){
        return new AppInterstitialAdProvider(mActivity);
    }


    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(
            SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(
            LoginPresenter<LoginMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    StoreMvpPresenter<StoreMvpView> provideStorePresenter(
            StorePresenter<StoreMvpView> presenter) {
        return presenter;
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }


    @Provides
    @PerActivity
    AssetLoadManager provideAssetLoadManager(InterstitialAdProvider interstitialAdProvider, ReferenceCounter referenceCounter,
                                             SchedulerProvider schedulerProvider,
                                             CompositeDisposable compositeDisposable){
        return new AppAssetLoadManager(interstitialAdProvider, referenceCounter, schedulerProvider, compositeDisposable);
    }

    @Provides
    @PerActivity
    ReferenceCounter provideAppReferenceCounter(){
        return new AppReferenceCounter();
    }

    @Provides
    @PerActivity
    ProviderManager provideAuthenticationManager(){

        ProviderProperties authProviderConfigurations = new
                ProviderProperties.AuthProviderPropertiesBuilder()
                .targetActivity(mActivity)
                .build();

        ProviderManager.getInstace().Configure(authProviderConfigurations);

        return ProviderManager.getInstace();
    }
}
