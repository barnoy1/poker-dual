package com.game.pokerdual.ui.login;

import com.game.pokerdual.di.PerActivity;
import com.game.pokerdual.ui.base.MvpPresenter;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by ron on 9/30/2018.
 */


@PerActivity
public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {

    void onServerLoginClick(String email, String password);

    void onGoogleLoginClick();

    void onFacebookLoginClick();

    boolean verifyUserDisplayName(FirebaseUser firebaseUser);

    void UpdateUserDisplayName(FirebaseUser firebaseUser, String dialogBoxInputDisplayName);
}