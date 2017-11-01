package com.game.pokerdual.data.json.structs;


import com.google.gson.annotations.SerializedName;

public enum ItemType {
    @SerializedName("0") COMMON,
    @SerializedName("1") RARE,
    @SerializedName("2") EPIC,
    @SerializedName("3") LEGENDARY
}
