package com.game.pokerdual.ui.store;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.EditText;

import com.game.pokerdual.R;
import com.game.pokerdual.ui.base.BaseActivity;
import com.game.pokerdual.utils.Logger;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by ron on 10/1/2018.
 */

public class StoreActivity extends BaseActivity implements StoreMvpView {

    private static final String TAG = StoreActivity.class.getSimpleName();

    @Inject
    StoreMvpPresenter<StoreMvpView> mPresenter;

    @BindView(R.id.et_email)
    EditText mEmailEditText;

    @BindView(R.id.et_password)
    EditText mPasswordEditText;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, StoreActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(StoreActivity.this);

        FirebaseStorage storage = FirebaseStorage.getInstance();

        downloadFile();

    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    private void downloadFile() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://pokerdual-77546.appspot.com/store_items");
        StorageReference  islandRef = storageRef.child("Store.json");

        File rootPath = new File(Environment.getExternalStorageDirectory(), "Store.json");
        if(!rootPath.exists()) {
            rootPath.mkdirs();
        }

        final File localFile = new File(rootPath,"Store.json");

        islandRef.getFile(localFile)

                .addOnSuccessListener(taskSnapshot -> {
                    Logger.e("local tem file created: " + localFile.toString());
                })

                .addOnFailureListener(exception -> {
                    Logger.e("failed to create file: " + exception.toString());
                });
    }

    @Override
    protected void setUp() {

    }

}
