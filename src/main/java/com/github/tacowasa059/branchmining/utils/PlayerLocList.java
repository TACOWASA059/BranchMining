package com.github.tacowasa059.branchmining.utils;

import org.bukkit.Location;

import java.util.HashMap;
import java.util.UUID;

public class PlayerLocList {
    public static HashMap<UUID, Location> locationHashMap=new HashMap<>();
    public static void resetLocationMap(){
        locationHashMap.clear();
    }
}
