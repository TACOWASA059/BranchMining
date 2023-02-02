package com.github.tacowasa059.branchmining.utils;

import java.util.UUID;

import static com.github.tacowasa059.branchmining.utils.DataList.time_count;
//時間カウントの更新
public class TimeCount {
    static public void TimeUpdate(UUID uuid,Double value){
        if(time_count.containsKey(uuid)){
            time_count.put(uuid,time_count.get(uuid)+value);
        }
        else{
            time_count.put(uuid,0.0);
        }
    }
    static public void addTime(UUID uuid){
        time_count.put(uuid,0.0);
    }
    static public void ResetValue(UUID uuid){
        time_count.put(uuid,0.0);
    }
}
