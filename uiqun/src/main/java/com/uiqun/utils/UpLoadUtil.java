package com.uiqun.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
                    Method method = tClass.getMethod(sb.toString(), declaredFields[j].getType());
                    //判断是不是字符串是不是Date日期格式且是不是日期类型
                    if(Date.class.getTypeName().equalsIgnoreCase(declaredFields[j].getType().getTypeName() ) ){
                        method.invoke(t,dateDispose(list.get(i).get(j)));
                    }else {
                        method.invoke(t,
                                (list.get(i).get(j) != null && !"".equals(list.get(i).get(j)))
                                        ? UpLoadUtil.class.getClassLoader().
                                        loadClass(declaredFields[j].getType().getName()).getConstructor(String.class).newInstance(list.get(i).get(j))
                                        : null
                        );
                    }
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

    /**
     * 字符串转换成日期类型
     * @param o
     * @return
     */
    private static Date dateDispose(Object o){
        try {
            String format ="yyyy/MM/dd HH:mm:ss";
            if(o!=null&&!"".equals(o)){
                String dateStr = (String) o;
                if( dateStr.indexOf("/")>0 &&!(dateStr.indexOf(":")>0) ){
                    format ="yyyy/MM/dd";
                }else if( dateStr.indexOf("-")>0 &&(dateStr.indexOf(":")>0) ){
                    format ="yyyy-MM-dd HH:mm:ss";
                }else if( dateStr.indexOf("-")>0 &&!(dateStr.indexOf(":")>0) ){
                    format ="yyyy-MM-dd";
                }
                return new SimpleDateFormat(format).parse((String)o);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
