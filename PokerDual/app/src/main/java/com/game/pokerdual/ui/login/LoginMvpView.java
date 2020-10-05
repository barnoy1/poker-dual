package com.game.pokerdual.ui.login;

import com.firebase.authwrapper.providers.common.callbacks.AuthenticationListener;
import com.game.pokerdual.ui.base.MvpView;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by ron on 9/30/2018.
 */

public interface LoginMvpView extends MvpView , AuthenticationListener {

    void openMainActivity();

    void CreateDisplayNameDialogBox(FirebaseUser firebaseUser);

    void greetUser(FirebaseUser firebaseUser);
}
