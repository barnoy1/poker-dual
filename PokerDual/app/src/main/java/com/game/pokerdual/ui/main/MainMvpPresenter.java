package com.game.pokerdual.ui.main;

import com.game.pokerdual.di.PerActivity;
import com.game.pokerdual.ui.base.MvpPresenter;
import com.game.pokerdual.ui.store.StoreMvpView;

/**
 * Created by ron on 9/30/2018.
 */


@PerActivity
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

}