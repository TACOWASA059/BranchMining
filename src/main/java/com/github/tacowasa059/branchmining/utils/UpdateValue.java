package com.github.tacowasa059.branchmining.utils;

import java.util.HashMap;
import java.util.UUID;

//鉱石カウントの更新
public class UpdateValue {
    static public void update(HashMap<UUID,Integer> sum1, UUID uuid){
        if(sum1.containsKey(uuid)){
            sum1.put(uuid,sum1.get(uuid)+1);
        }
        else{
            sum1.put(uuid,1);
        }
    }

}
