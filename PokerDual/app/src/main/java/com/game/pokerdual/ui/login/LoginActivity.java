package com.game.pokerdual.ui.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.game.pokerdual.R;
import com.game.pokerdual.ui.base.BaseActivity;
import com.game.pokerdual.ui.main.MainActivity;
import com.game.pokerdual.utils.Logger;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import org.w3c.dom.Notation;
import org.w3c.dom.Text;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by ron on 10/1/2018.
 */

public class LoginActivity extends BaseActivity implements LoginMvpView {

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

        Intent intent = MainActivity.getStartIntent(LoginActivity.this);
        startActivity(intent);
        finish();

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
    public void CreateDisplayNameDialogBox(FirebaseUser firebaseUser) {

        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.prompts, null);
        promptsView.setAlpha(0.6f);


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this, R.style.CustomAlertDialog);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        Objects.requireNonNull(alertDialog.getWindow())
                .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setView(promptsView);


        final TextView title = (TextView) promptsView
                 .findViewById(R.id.titleTextDialogUserInput);
        title.setText(R.string.dialog_display_name_title);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        final Button btnOk = (Button) promptsView
                .findViewById(R.id.dialogButtonOK);

        // set the custom dialog components - text, image and button
        ImageView image = (ImageView) promptsView.findViewById(R.id.DialogBoxImage);
        image.setImageResource(R.drawable.user);

        // Set up the buttons
        btnOk.setOnClickListener(view -> {
            mPresenter.UpdateUserDisplayName(firebaseUser, userInput.getText().toString());
            greetUser(firebaseUser);
            openMainActivity();
        });

        alertDialogBuilder.show();
    }

    @Override
    public void greetUser(FirebaseUser firebaseUser) {

        String message = "hello, " + firebaseUser.getDisplayName();
        showSnackBarMessage(message);
        Logger.i(message);
    }

    @Override
    public void OnAuthenticationComplete(FirebaseUser firebaseUser) {

        //TODO
        // this callback fire twice (on for signout all providers.
        // need to add in the library that it will not be triggered
        // if user is null.
        if (firebaseUser == null)
            return;


       boolean hasValidDisplayName = mPresenter.verifyUserDisplayName(firebaseUser);
       if (!hasValidDisplayName)
       {
           CreateDisplayNameDialogBox(firebaseUser);
           return;
       }

        greetUser(firebaseUser);
        openMainActivity();


    }

    @Override
    public void OnAuthenticationFailed(Exception e) {
        showSnackBarMessage(this.getString(R.string.user_auth_mail_failed) +
                e.getMessage());
    }


}
