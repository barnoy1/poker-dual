package com.game.pokerdual.utils.AssetLoader.InterstitialAdProperties;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class InterstitialAdPropertiesBuilder {

    private Action onAdClose;
    private Action onComplete;
    private Consumer<? super Long> onNext;
    private Consumer<? super Throwable> onError;

    private long startIntervalValue = 1;
    private long rangeInitDelay = 700;
    private long rangePeriod = 75;
    private long endValueInterval = 100;

    public InterstitialAdPropertiesBuilder EndValueInterval(long endValueInterval ) {
        this.endValueInterval = endValueInterval;
        return this;
    }

    public InterstitialAdPropertiesBuilder StartIntervalValue(long startIntervalValue ) {
        this.startIntervalValue = startIntervalValue;
        return this;
    }

    public InterstitialAdPropertiesBuilder RangeInitDelay(long rangeInitDelay) {
        this.rangeInitDelay = rangeInitDelay;
        return this;
    }

    public InterstitialAdPropertiesBuilder RangePeriod(long rangePeriod) {
        this.rangePeriod = rangePeriod;
        return this;
    }

    public InterstitialAdPropertiesBuilder OnAdClose(Action onAdClose) {
        this.onAdClose = onAdClose;
        return this;
    }

    public InterstitialAdPropertiesBuilder OnNext(Consumer<? super Long> onNext) {
        this.onNext = onNext;
        return this;
    }

    public InterstitialAdPropertiesBuilder OnError(Consumer<? super Throwable> onError) {
        this.onError = onError;
        return this;
    }

    public InterstitialAdPropertiesBuilder OnComplete(Action onComplete) {
        this.onComplete = onComplete;
        return this;
    }

    public InterstitialAdProperties build () {
        InterstitialAdProperties interstitialAdProperties = new InterstitialAdProperties();
        interstitialAdProperties.onAdClose = this.onAdClose;
        interstitialAdProperties.onNext = this.onNext;
        interstitialAdProperties.onError = this.onError;
        interstitialAdProperties.onComplete = this.onComplete;

        interstitialAdProperties.startIntervalValue = this.startIntervalValue;
        interstitialAdProperties.rangeInitDelay = this.rangeInitDelay;
        interstitialAdProperties.rangePeriod = this.rangePeriod;
        interstitialAdProperties.endValueInterval = this.endValueInterval;

        return interstitialAdProperties;
    }
}