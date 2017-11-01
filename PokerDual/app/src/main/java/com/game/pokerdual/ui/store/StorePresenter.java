package com.game.pokerdual.ui.store;

import com.firebase.authwrapper.manager.ProviderManager;
import com.game.pokerdual.data.DataManager;
import com.game.pokerdual.ui.base.BasePresenter;
import com.game.pokerdual.utils.AssetLoader.AssetLoadManager;
import com.game.pokerdual.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ron on 9/30/2018.
 */

public class StorePresenter<V extends StoreMvpView> extends BasePresenter<V>
        implements StoreMvpPresenter<V> {

    private final static String TAG = StorePresenter.class.getSimpleName();

    @Inject
    public ProviderManager mProviderManager;

    @Inject
    public StorePresenter(DataManager dataManager,
                          SchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable,
                          AssetLoadManager assetLoadManager) {

        super(dataManager, schedulerProvider, compositeDisposable,  assetLoadManager);

    }

}
