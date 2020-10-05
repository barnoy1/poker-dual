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

package com.game.pokerdual.di.module;

import android.app.Application;
import android.content.Context;

import com.game.pokerdual.BuildConfig;
import com.game.pokerdual.R;
import com.game.pokerdual.data.DataManagerImp;
import com.game.pokerdual.data.DataManager;
import com.game.pokerdual.data.prefs.PreferencesHelperImp;
import com.game.pokerdual.data.prefs.PreferencesHelper;
import com.game.pokerdual.di.ApiInfo;
import com.game.pokerdual.di.ApplicationContext;
import com.game.pokerdual.di.DatabaseInfo;
import com.game.pokerdual.di.PreferenceInfo;
import com.game.pokerdual.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by janisharali on 27/01/17.
 */

/**
 * Created by janisharali on 27/01/17.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(DataManagerImp appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(PreferencesHelperImp appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                //.setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setDefaultFontPath("fonts/Boogaloo-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}