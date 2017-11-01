package com.game.pokerdual.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by ron on 7/8/2018.
 */


@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerService {
}
