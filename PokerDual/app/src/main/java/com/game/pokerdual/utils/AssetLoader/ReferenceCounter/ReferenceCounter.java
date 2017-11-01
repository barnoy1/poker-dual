package com.game.pokerdual.utils.AssetLoader.ReferenceCounter;

public interface ReferenceCounter {

    void DecreaseRefCounter();

    void IncreaseRefCounter();

    boolean IsRefCounterZero();

}
