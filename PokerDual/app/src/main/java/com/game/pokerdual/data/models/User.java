package com.game.pokerdual.data.models;

import com.game.pokerdual.data.json.structs.Item;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class User {

    public String mName;
    public String mEmail;
    public String mToken;


    public int mScores;
    public int mCurrency;
    public long mExperience;
    public int mWins;
    public int mMatches;

    public ArrayList<MatchResult> mMatchResults = new ArrayList<>();
    public ArrayList<Group> mGroups = new ArrayList<>();
    public ArrayList<Item> mItems = new ArrayList<>();

    public User(UserBuilder userBuilder) {

        this.mName = userBuilder.name;
        this.mToken = userBuilder.token;
        this.mEmail = userBuilder.email;

        mWins = 0;
        mMatches = 0;
        mScores = 0;
        mCurrency = 0;
        mExperience = 1;

        mMatchResults.clear();
        mGroups.clear();

    }

    public static class UserBuilder
    {
        private String token;
        private String name;
        private String email;

        public UserBuilder Token(String token) {
            this.token = token;
            return  this;
        }

        public UserBuilder Name(String name) {
            this.name = name;
            return  this;
        }

        public UserBuilder Email(String email) {
            this.email = email;
            return  this;
        }

        public User build(){
            return new User(this);

        }
    }



}
