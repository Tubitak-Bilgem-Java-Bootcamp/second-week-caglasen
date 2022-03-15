package com.company;

public abstract class Location {
    private Player player;
    private String name;

    public Location(Player player) {
        this.player = player;
    }


    abstract boolean onLocation();  // a frame containing all things that happen in this location


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
