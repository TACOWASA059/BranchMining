package com.github.tacowasa059.branchmining.utils;

import java.util.HashMap;
import java.util.UUID;

public class DataList {
    static public HashMap<UUID,Double> time_count=new HashMap<>();
    static public HashMap<UUID,Integer> sum=new HashMap<>();
    static public HashMap<UUID,Integer> coal=new HashMap<>();
    static public HashMap<UUID,Integer> iron=new HashMap<>();
    static public HashMap<UUID,Integer> laps =new HashMap<>();
    static public HashMap<UUID,Integer> diamond=new HashMap<>();
    static public HashMap<UUID,Integer> red_stone =new HashMap<>();
    static public HashMap<UUID,Integer> emerald=new HashMap<>();
    static public HashMap<UUID,Integer> gold=new HashMap<>();
    static public HashMap<UUID,Integer> ancient_debris=new HashMap<>();

    static public void resetMap(){
        time_count.clear();
        sum.clear();
        coal.clear();
        iron.clear();
        laps.clear();
        diamond.clear();
        red_stone.clear();
        emerald.clear();
        gold.clear();
        ancient_debris.clear();
    }
}
