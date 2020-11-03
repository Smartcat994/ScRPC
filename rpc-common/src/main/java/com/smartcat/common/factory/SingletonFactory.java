package com.smartcat.common.factory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * description: SingletonFactory
 * date: 2020/11/3 14:56
 *
 * @author: SmartCat
 * version: 1.0.0
 */
public class SingletonFactory {
    //使用Map集合来存储单例对象
    private static final Map<String, Object> OBJECT_MAP = new HashMap<>();

    private SingletonFactory(){}


    public static <T> T  getInstance(Class<T> clazz){
        String key = clazz.toString();
        Object instance = null;
        if (instance ==null){
            //懒汉模式,锁住类对象
            synchronized (SingletonFactory.class){
                instance = OBJECT_MAP.get(key);
                if (instance == null){
                    try{
                        instance = clazz.getDeclaredConstructor().newInstance();
                        OBJECT_MAP.put(key,instance);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            
        }
        return clazz.cast(instance);
    }
}
