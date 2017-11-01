package com.game.pokerdual.data;

import android.content.Context;

import com.game.pokerdual.data.prefs.PreferencesHelper;
import com.game.pokerdual.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ron on 8/14/2018.
 */

@Singleton
public class AppDataManager implements DataManager {


    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          PreferencesHelper preferencesHelper) {
        mContext = context;
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

}
