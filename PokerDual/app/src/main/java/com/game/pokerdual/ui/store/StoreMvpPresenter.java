package com.game.pokerdual.ui.store;

import com.game.pokerdual.di.PerActivity;
import com.game.pokerdual.ui.base.MvpPresenter;

/**
 * Created by ron on 9/30/2018.
 */


@PerActivity
public interface StoreMvpPresenter<V extends StoreMvpView> extends MvpPresenter<V> {

    //void onServerLoginClick(String email, String password);

}