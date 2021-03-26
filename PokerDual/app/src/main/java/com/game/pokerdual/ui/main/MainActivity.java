package com.game.pokerdual.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.game.pokerdual.R;
import com.game.pokerdual.data.service.SyncService;
import com.game.pokerdual.ui.base.BaseActivity;
import com.game.pokerdual.ui.login.LoginActivity;
import com.game.pokerdual.ui.splash.SplashMvpPresenter;
import com.game.pokerdual.ui.splash.SplashMvpView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ron on 9/27/2018.
 */

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(MainActivity.this);

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
