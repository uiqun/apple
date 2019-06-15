package com.uiqun.utils;

import com.uiqun.model.OnlineDataLog;
import com.uiqun.model.QuoteDataLog;
import com.uiqun.model.RfqDataLog;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.lang.reflect.Field;

public class DateLogUtil {
    private DateUtil dateUtil = new DateUtil();

    /**
     * 将日志写出为excel格式
     * @param sfp 模板文件路径
     * @param dfp 目标文件路径
     * @param data 需要写入的数据
     * @param <T> 传入的数据类型
     */
    public <T> void setDateLog(String sfp,String dfp,T data){
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        //如果不存在则复制模板文件
        if(!isExistsLog(dfp)){
            copyLogFile(sfp,dfp);
        }
        try {
            File file = new File(dfp);
            inputStream = new FileInputStream(file);
            Workbook workbook = ExcelUtil.getWorkbook(inputStream, file.getName());
            //修改数据
            writeData(workbook,data);
            fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
            fileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(fileOutputStream!=null){
                try {
                    fileOutputStream.close();
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
    }

    /**
     * 是否存在数据统计表
     * @param src 模板文件路径
     * @param target 目标文件路径
     * @return
     */
    public boolean copyLogFile(String src,String target){
        File file = new File(src);
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream =null;
        if(!isExistsLog(target)){
            try {
                file.createNewFile();
                //读取模板
                fileInputStream = new FileInputStream(new File(target));
                byte[] dataArr = new byte[fileInputStream.available()];
                fileInputStream.read(dataArr);
                //写出模板
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(dataArr);
                fileOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }finally {
                if(fileInputStream!=null){
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(fileOutputStream!=null){
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return true;
    }

    /**
     * 判断是否存在
     * @param target
     * @return
     */
    public boolean isExistsLog(String target){
        File file = new File(target);
        return file.exists();
    }

    /**
     * 写出数据
     * @param work 工作簿
     * @param data 写入的数据
     * @param <T> 写入数据的类型
     * @return
     */
    public <T>void writeData(Workbook work,T data){
        //获取存储当前月份数据的sheet
        Sheet sheet = sheet = work.getSheetAt(new DateUtil().getCurrentMonth());
        Row row = null;
        Cell cell = null;
        StringBuilder sb =null;
        //读取一行
            if(!"暂无数据".equalsIgnoreCase(sheet.getRow(1).getCell(0).getStringCellValue())) {
                //在线统计
                if (data instanceof OnlineDataLog) {
                    row = sheet.getRow(1);
                    //询价/报价统计
                } else if (data instanceof QuoteDataLog || data instanceof RfqDataLog) {
                    //查找是否存在询价型号或者报价型号
                    Object value = getValue(data, 0);
                    Integer existsPn = isExistsPn(sheet, value != null ? value.toString() : null);
                    if (existsPn > -1) {
                        row = sheet.getRow(existsPn);

                    } else {
                        row = sheet.createRow(sheet.getLastRowNum() + 1);
                    }
                }
            }else{
                row=sheet.getRow(1);
            }
            Field[] declaredFields = data.getClass().getDeclaredFields();
            for (int y = row.getFirstCellNum(),i = 0; y < row.getLastCellNum(); y++) {
                //设置值
                row.getCell(y).setCellValue(
                        //判断是否为日期类型  是日期类型则输出格式为yyyy-MM-dd HH:mm:ss 否则则将数据格式化为String 类型
                        new DateUtil().getPrettyDate( getValue(data,i++) )
                );
            }
    }

    private<T> Object getValue(T data,Integer i){
        Field[] declaredFields = data.getClass().getDeclaredFields();
        Field declaredField = declaredFields[i];
        String name = declaredField.getName();
        StringBuilder sb= new StringBuilder(name);
        sb.setCharAt(0,Character.toUpperCase(name.charAt(0)));
        sb.insert(0,"get");
        try {
            return data.getClass().getMethod(sb.toString(),declaredField.getType()).invoke(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查看型号是否存在
     * @param sheet
     * @param pn
     * @return
     */
    private Integer isExistsPn(Sheet sheet,String pn){
        if(pn!=null) {
            Row row = null;
            for (int i = sheet.getFirstRowNum(); i < sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                if (pn.equalsIgnoreCase(row.getCell(0).getStringCellValue())) {
                    return i;
                }
            }
        }
        return -1;
    }
}
