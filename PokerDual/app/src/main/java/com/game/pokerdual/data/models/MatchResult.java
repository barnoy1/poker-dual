package com.game.pokerdual.data.models;

import com.google.firebase.database.IgnoreExtraProperties;



@IgnoreExtraProperties
public class MatchResult {

    public boolean mIsWon;
    public String mOpponentToken;
    public int mScore;
    public long mTimeStamp;

    public MatchResult()
    {

    }
}