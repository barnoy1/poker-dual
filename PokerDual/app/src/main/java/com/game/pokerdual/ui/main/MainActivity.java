package com.game.pokerdual.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.EditText;

import com.game.pokerdual.R;
import com.game.pokerdual.ui.base.BaseActivity;
import com.game.pokerdual.ui.store.StoreMvpPresenter;
import com.game.pokerdual.ui.store.StoreMvpView;
import com.game.pokerdual.utils.AppLogger;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by ron on 10/1/2018.
 */

public class MainActivity extends BaseActivity implements MainMvpView {


    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    @BindView(R.id.et_email)
    EditText mEmailEditText;

    @BindView(R.id.et_password)
    EditText mPasswordEditText;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //getActivityComponent().inject(this);

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
