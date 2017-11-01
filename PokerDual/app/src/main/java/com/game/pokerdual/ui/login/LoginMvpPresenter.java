package com.game.pokerdual.ui.login;

import com.game.pokerdual.di.PerActivity;
import com.game.pokerdual.ui.base.MvpPresenter;

/**
 * Created by ron on 9/30/2018.
 */


@PerActivity
public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {

    void onServerLoginClick(String email, String password);

    void onGoogleLoginClick();

    void onFacebookLoginClick();

}