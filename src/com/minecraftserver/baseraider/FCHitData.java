package com.minecraftserver.baseraider;

import org.bukkit.Location;

public class FCHitData {

    private final Location loc;
    private final int      strength;

    public FCHitData(Location l, int s) {
        this.loc = l;
        this.strength = s;
    }

    public Location getLoc() {
        return this.loc;
    }

    public int getStrength() {
        return this.strength;
    }
}
