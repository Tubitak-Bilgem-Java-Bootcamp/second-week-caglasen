package com.company;

public abstract class NormalLoc extends Location {

    public NormalLoc(Player player) {
        super(player);
    }

    @Override
    public boolean onLocation() {
        return true;
    }
}