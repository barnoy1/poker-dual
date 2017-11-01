package com.game.pokerdual.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.firebase.authwrapper.providers.common.callbacks.AuthenticationListener;
import com.game.pokerdual.R;
import com.game.pokerdual.ui.base.BaseActivity;
import com.game.pokerdual.utils.AppLogger;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by ron on 10/1/2018.
 */

public class LoginActivity extends BaseActivity implements LoginMvpView,
        AuthenticationListener {

    private static final String TAG = LoginActivity.class.getSimpleName();

    @Inject
    LoginMvpPresenter<LoginMvpView> mPresenter;

    @BindView(R.id.et_email)
    EditText mEmailEditText;

    @BindView(R.id.et_password)
    EditText mPasswordEditText;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(LoginActivity.this);


    }

    @OnClick(R.id.btn_server_login)
    void onServerLoginClick(View v) {
        mPresenter.onServerLoginClick(mEmailEditText.getText().toString(),
                mPasswordEditText.getText().toString());
    }

    @OnClick(R.id.ib_google_login)
    void onGoogleLoginClick(View v) {
        mPresenter.onGoogleLoginClick();
    }

    @OnClick(R.id.ib_fb_login)
    void onFbLoginClick(View v) {
        mPresenter.onFacebookLoginClick();
    }

    @Override
    public void openMainActivity() {
        /*
        Intent intent = MainActivity.getStartIntent(LoginActivity.this);
        startActivity(intent);
        finish();
        */
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void OnAuthenticationComplete(FirebaseUser firebaseUser) {



        //TODO
        // this callback fire twice (on for signout all providers.
        // need to add in the library that it will not be triggered
        // if user is null.

        if (firebaseUser != null)
        {

            String message = String.format(
                    "name=%s,\temail=%s,\tuid=%s",
                    firebaseUser.getDisplayName(),
                    firebaseUser.getEmail(),
                    firebaseUser.getUid());

            showSnackBarMessage(message);

            AppLogger.i(message);

            mEmailEditText.setText(firebaseUser.getEmail());
            mPasswordEditText.setText(firebaseUser.getUid());
        }
    }

    @Override
    public void OnAuthenticationFailed(Exception e) {

        showSnackBarMessage(this.getString(R.string.user_auth_mail_failed) +
                e.getMessage());

    }
}
