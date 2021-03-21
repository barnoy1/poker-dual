/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.game.pokerdual;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import androidx.multidex.MultiDexApplication;
import android.util.Base64;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor.Level;
import com.game.pokerdual.data.DataManager;
import com.game.pokerdual.di.component.ApplicationComponent;
import com.game.pokerdual.di.component.DaggerApplicationComponent;
import com.game.pokerdual.di.module.ApplicationModule;
import com.game.pokerdual.utils.Logger;
import com.google.android.gms.ads.MobileAds;

import java.security.MessageDigest;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * Created by janisharali on 27/01/17.
 */

public class MvpApp extends MultiDexApplication {

    private final static String TAG = MvpApp.class.getSimpleName();

    @Inject
    DataManager mDataManager;

    @Inject
    CalligraphyConfig mCalligraphyConfig;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();


        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

        Logger.init();


        MobileAds.initialize(this, getString(R.string
                .application_id));

        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(Level.BODY);
        }

        CalligraphyConfig.initDefault(mCalligraphyConfig);

        if (BuildConfig.DEBUG){

            try {

                // facebook hash key
                PackageInfo info = getPackageManager().getPackageInfo(
                        getString(R.string.package_name),
                        PackageManager.GET_SIGNATURES);
                for (Signature signature : info.signatures) {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());

                    String hashKey = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.FROYO) {
                        hashKey = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                    }
                    Logger.i(hashKey);
                }

            }
            catch (Exception e){
                Logger.i(e.getMessage());
            }

        }

    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

}
