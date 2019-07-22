package com.uiqun.utils;

import org.aspectj.weaver.ast.Var;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class UpLoadUtil {
    /**
     * object集合转换为指定对象集合
     * @param list
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> List<T> tranceObject(List<List<Object>> list , Class<T> tClass) throws NoSuchMethodException,
            IllegalAccessException, InstantiationException, InvocationTargetException, ClassNotFoundException {
        List<T> tList = new ArrayList<>();
        StringBuilder sb = null;
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
                        if(declaredFields[j].getType().getTypeName().equalsIgnoreCase("String") ){
                            method.invoke(t,
                                    (StringUtils.isEmpty(list.get(i).get(j) )
                                            ? UpLoadUtil.class.getClassLoader().
                                            loadClass(declaredFields[j].getType().getName()).getConstructor(String.class).newInstance(list.get(i).get(j))
                                            : null
                            ));
                        }else{
                            String trim = ((String) list.get(i).get(j)).trim();
                            method.invoke(t,StringUtils.isEmpty(trim)?null:UpLoadUtil.class.getClassLoader().
                                    loadClass(declaredFields[j].getType().getName()).getConstructor(String.class).newInstance(trim));
                        }
                    }
                }
                tList.add(t);
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

    /**
     * 指定文件名保存图片(路径为static/images)
     * @param multipartFile 上传文件
     * @param name  保存文件的文件名
     * @return
     */
    public static boolean uploadImages(MultipartFile multipartFile, String name){
        InputStream inputStream =null;
        OutputStream outputStream = null;
        try{
            inputStream = multipartFile.getInputStream();
            String path = ResourceUtils.getURL("classpath:static/images").getPath();
            String name1 = multipartFile.getOriginalFilename();
            String suffix = name1.substring(name1.lastIndexOf("."));
            File file = new File(path+"/"+name+suffix);
            if(file.exists()){
                file.delete();
            }
            outputStream = new FileOutputStream(file);
            int a=-1;
            while ((a=inputStream.read()) >-1){
                outputStream.write(a);
            }
            outputStream.flush();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
