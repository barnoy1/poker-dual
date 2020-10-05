package com.game.pokerdual.ui.main;

import com.game.pokerdual.data.DataManager;
import com.game.pokerdual.ui.base.BasePresenter;
import com.game.pokerdual.utils.AssetLoader.AssetLoadManager;
import com.game.pokerdual.utils.SchedulerProvider.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ron on 9/27/2018.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {


    private AssetLoadManager mAssetLoadManager;

    @Inject
    public MainPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable,
                         AssetLoadManager assetLoadManager) {

        super(dataManager, schedulerProvider, compositeDisposable, assetLoadManager);


        this.mAssetLoadManager = getAssetLoadManager();

    }


    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);

    }

}
