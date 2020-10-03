package com.game.pokerdual.ui.login;

import com.firebase.authwrapper.providers.common.callbacks.AuthenticationListener;
import com.game.pokerdual.ui.base.MvpView;

/**
 * Created by ron on 9/30/2018.
 */

public interface LoginMvpView extends MvpView , AuthenticationListener {

    void openMainActivity();
}
