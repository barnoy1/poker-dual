package com.game.pokerdual.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.game.pokerdual.di.ApplicationContext;
import com.game.pokerdual.di.PreferenceInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ron on 8/14/2018.
 */


@Singleton
public class PreferencesHelperImp implements PreferencesHelper {

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";
    private static final String PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID";
    private static final String PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME";
    private static final String PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL";
    private static final String PREF_KEY_CURRENT_USER_PROFILE_PIC_URL
            = "PREF_KEY_CURRENT_USER_PROFILE_PIC_URL";
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";

    private final SharedPreferences mPrefs;

    @Inject
    public PreferencesHelperImp(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public void put(String key, String value) {
        mPrefs.edit().putString(key, value).apply();
    }

    @Override
    public void put(String key, int value) {
        mPrefs.edit().putInt(key, value).apply();
    }

    @Override
    public void put(String key, float value) {
        mPrefs.edit().putFloat(key, value).apply();
    }

    @Override
    public void put(String key, boolean value) {
        mPrefs.edit().putBoolean(key, value).apply();
    }

    @Override
    public String get(String key, String defaultValue) {
        return mPrefs.getString(key, defaultValue);
    }

    @Override
    public Integer get(String key, int defaultValue) {
        return mPrefs.getInt(key, defaultValue);
    }

    @Override
    public Float get(String key, float defaultValue) {
        return mPrefs.getFloat(key, defaultValue);
    }

    @Override
    public Boolean get(String key, boolean defaultValue) {
        return mPrefs.getBoolean(key, defaultValue);
    }

    @Override
    public void deleteSavedData(String key) {
        mPrefs.edit().remove(key).apply();
    }
}
