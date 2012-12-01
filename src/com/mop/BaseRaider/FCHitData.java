package com.mop.BaseRaider;

import org.bukkit.Location;

public class FCHitData {

    private Location loc;
    private int strength;
    
    public FCHitData(Location l ,int s){
	loc=l;
	strength=s;
    }
    
    public Location getLoc() {
        return loc;
    }
    public int getStrength() {
        return strength;
    }
}
