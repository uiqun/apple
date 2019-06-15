package com.uiqun.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public abstract class UpLoadUtil {
    public static <T> List<T> tranceObject(List<List<Object>> list , Class<T> tClass){
        List<T> tList = new ArrayList<>();
        StringBuilder sb = null;
        try {
            for (int i = 0; i < list.size(); i++) {
                    T t = tClass.newInstance();
                Field[] declaredFields = tClass.getDeclaredFields();
                for (int j = 0; j < list.get(i).size(); j++) {
                    String name = declaredFields[j].getName();
                    sb=new StringBuilder(name);
                    sb.setCharAt(0,Character.toUpperCase(name.charAt(0)));
                    sb.insert(0,"set");
                    tClass.getMethod(sb.toString(),declaredFields[j].getType()).invoke(t,
                            (list.get(i).get(j)!=null&&!"".equals(list.get(i).get(j)))?UpLoadUtil.class.getClassLoader().loadClass(declaredFields[j].getType().getName()).getConstructor(String.class).newInstance(list.get(i).get(j)):null
                    );
                }
                tList.add(t);
            }


        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return tList;
    }

}
