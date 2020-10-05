package com.game.pokerdual.utils.AssetLoader.ReferenceCounter;

public class AppReferenceCounterImp implements ReferenceCounter {

    private int mRefCounter;

    public void DecreaseRefCounter()
    {
        if (mRefCounter > 0)
            mRefCounter--;
    }

    public void IncreaseRefCounter(){
        mRefCounter++;
    }

    public boolean IsRefCounterZero()
    {
        return (mRefCounter == 0);
    }
    
}
