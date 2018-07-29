package ru.georgewl.epam.it.persistence;

import com.alibaba.fastjson.JSON;

/**
 *
 * @author Yury Belozyorov, PTS
 */
public class JSONHelper {
    
    public static String jsonFor(Persistable persistable){
        String result= JSON.toJSONString(persistable);
        return result;
    }
    
    public static Persistable persistableFor(String json, Class<? extends Persistable> clazz) {
        Persistable p= JSON.parseObject(json, clazz);
        return p;
    }
    
}
