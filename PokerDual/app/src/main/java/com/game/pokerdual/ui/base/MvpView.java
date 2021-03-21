package com.game.pokerdual.ui.base;

import androidx.annotation.StringRes;

/**
 * Created by ron on 8/18/2018.
 */

public interface MvpView {

    String getStringByID(@StringRes int resId);

    void showLoading();

    void hideLoading();

    void openActivityOnTokenExpire();

    void onError(@StringRes int resId);

    void onError(String message);

    void showToastMessage(String message);

    void showToastMessage(@StringRes int resId);

    void showSnackBarMessage(String message);

    void showSnackBarMessage(@StringRes int resId);

    boolean isNetworkConnected();

    void hideKeyboard();
}
