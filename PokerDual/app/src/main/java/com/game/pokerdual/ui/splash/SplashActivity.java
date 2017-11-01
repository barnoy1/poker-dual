package com.game.pokerdual.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.game.pokerdual.R;
import com.game.pokerdual.data.service.SyncService;
import com.game.pokerdual.ui.base.BaseActivity;
import com.game.pokerdual.ui.login.LoginActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ron on 9/27/2018.
 */

public class SplashActivity  extends BaseActivity implements SplashMvpView {

    @Inject
    SplashMvpPresenter<SplashMvpView> mPresenter;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @BindView(R.id.textViewPercent)
    TextView mTextViewPercent;

    @BindView(R.id.textViewTextProgress)
    TextView mTextViewTextProgress;


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(SplashActivity.this);

        // left to right
        mProgressBar.setRotation(180);
    }



    /**
     * Making the screen wait so that the  branding can be shown
     */
    @Override
    public void openLoginActivity() {

        Intent intent = LoginActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        finish();

    }

    @Override
    public void openMainActivity() {
        /*
        Intent intent = MainActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        finish();
        */
    }

    @Override
    public void updateTextViewProgressBar(String value) {
        mTextViewTextProgress.setText(value);
    }

    @Override
    public void updateProgressBar(long value) {
        
        mProgressBar.setProgress((int)value);
        String progressData = String.valueOf(value) + "%";
        mTextViewPercent.setText(progressData);
    }

    @Override
    public void startSyncService() {
        SyncService.start(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }
}
