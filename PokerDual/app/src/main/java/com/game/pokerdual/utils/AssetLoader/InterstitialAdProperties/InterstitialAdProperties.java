package com.game.pokerdual.utils.AssetLoader.InterstitialAdProperties;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class InterstitialAdProperties {

    public Consumer<? super Throwable> onError;
    public Consumer<? super Long> onNext;
    public Action onComplete;
    public Action onAdClose;

    public long startIntervalValue;
    public long endValueInterval;
    public long rangePeriod;
    public long rangeInitDelay;
}
