package com.game.pokerdual.data.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.game.pokerdual.MvpApp;
import com.game.pokerdual.data.DataManager;
import com.game.pokerdual.di.component.DaggerServiceComponent;
import com.game.pokerdual.di.component.ServiceComponent;
import com.game.pokerdual.utils.Logger;

import javax.inject.Inject;

/**
 * Created by ron on 10/1/2018.
 */


public class SyncService extends Service {

    @Inject
    DataManager mDataManager;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, com.game.pokerdual.data.service.SyncService.class);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, com.game.pokerdual.data.service.SyncService.class);
        context.startService(starter);
    }

    public static void stop(Context context) {
        context.stopService(new Intent(context, com.game.pokerdual.data.service.SyncService.class));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ServiceComponent component = DaggerServiceComponent.builder()
                .applicationComponent(((MvpApp) getApplication()).getComponent())
                .build();
        component.inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.d("SyncService started");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Logger.d("SyncService stopped");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

