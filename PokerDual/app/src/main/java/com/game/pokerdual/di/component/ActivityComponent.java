package com.game.pokerdual.di.component;

import com.game.pokerdual.di.PerActivity;
import com.game.pokerdual.di.module.ActivityModule;
import com.game.pokerdual.ui.login.LoginActivity;
import com.game.pokerdual.ui.main.MainActivity;
import com.game.pokerdual.ui.splash.SplashActivity;
import com.game.pokerdual.ui.store.StoreActivity;

import dagger.Component;

/**
 * Created by ron on 8/18/2018.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(LoginActivity activity);

    void inject(SplashActivity activity);

    void inject(StoreActivity activity);

    void inject(MainActivity activity);
}
