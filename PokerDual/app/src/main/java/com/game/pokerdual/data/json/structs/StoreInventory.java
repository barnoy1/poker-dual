package com.game.pokerdual.data.json.structs;

import java.util.List;

public class StoreInventory {

    List<Item> items;
    List<Elixir> elixirs;

    public List<Item> getItems() {
        return items;
    }

    public List<Elixir> getElixirs() {
        return elixirs;
    }
}