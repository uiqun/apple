package com.uiqun.utils;


import com.uiqun.model.Pn;
import com.uiqun.service.PnService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
public class ExcelUtil {
    private final static String excel2003L = ".xls";    //2003- 版本的excel
    private final static String excel2007U = ".xlsx";   //2007+ 版本的excel

    /**
     * Excel导入
     */
    public static List<List<Object>> getUploadListByExcel(InputStream in, String fileName) throws Exception {
        List<List<Object>> list = null;
        //创建Excel工作薄
        Workbook work = getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        list = new ArrayList<List<Object>>();
        //遍历Excel中所有的sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }
            //遍历当前sheet中的所有行
            //包涵头部，所以要小于等于最后一列数,这里也可以在初始值加上头部行数，以便跳过头部
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                //读取一行
                row = sheet.getRow(j);
                //去掉空行和表头
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }
                //遍历所有的列
                List<Object> li = new ArrayList<Object>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    if("".equals(row.getCell(y))||row.getCell(y)==null){
                        row.createCell(y).setCellValue(" ");
                    }
                    cell = row.getCell(y);
                    li.add(getCellValue(cell));
                }
                list.add(li);
            }
        }
        return list;
    }

    /**
     * excel导入bomlist信息
     * @param in
     * @param fileName
     * @param bomlistPrimaryKey
     * @return
     * @throws Exception
     */
    public static List<List<Object>> getBomListByExcel(InputStream in, String fileName,
                                                       Integer bomlistPrimaryKey, PnService pnService) throws Exception {
        List<List<Object>> list = null;
        //创建Excel工作薄
        Workbook work = getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        list = new ArrayList<List<Object>>();
        //遍历Excel中所有的sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }
            //遍历当前sheet中的所有行
            //包涵头部，所以要小于等于最后一列数,这里也可以在初始值加上头部行数，以便跳过头部
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                //读取一行
                row = sheet.getRow(j);
                //去掉空行和表头
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }
                //遍历所有的列
                List<Object> li = new ArrayList<Object>();
                for (int y = row.getFirstCellNum()-1; y < row.getLastCellNum()+1; y++) {
                    if(y==row.getFirstCellNum()-1){
                        li.add(bomlistPrimaryKey);
                        continue;
                    }else if(y == row.getLastCellNum()){
                        Pn pn = new Pn();
                        pn.setPn(
                                row.getCell(row.getFirstCellNum()).getRichStringCellValue().getString()   );
                        pn.setMfg(
                                row.getCell(row.getFirstCellNum()+2).getRichStringCellValue().getString()  );
                        Pn pn1 = pnService.selectStkByMinPrice(pn);
                        li.add(pn1!=null?pn1.getMpq():0);
                        li.add(pn1!=null?pn1.getPrice():0f);
                        li.add(pn1!=null?pn1.getDtime():"无记录");
                        continue;
                    }
                    if("".equals(row.getCell(y))||row.getCell(y)==null){
                        row.getCell(y).setCellValue(" ");
                    }
                    cell = row.getCell(y);
                    li.add(getCellValue(cell));
                }
                list.add(li);
            }
        }
        return list;
    }



    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     */
    public static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (excel2003L.equals(fileType)) {
            wb = new HSSFWorkbook(inStr);  //2003-
        } else if (excel2007U.equals(fileType)) {
            wb = new XSSFWorkbook(inStr);  //2007+
        } else {
            throw new CustomErrorException(ErrorCodes.EXCEL_PARSE_ERROR);
        }
        return wb;
    }

    /**
     * 描述：对表格中数值进行格式化
     */
    public static Object getCellValue(Cell cell) {
        Object value = null;
        DecimalFormat df = new DecimalFormat("0");  //格式化字符类型的数字
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    value = df.format(cell.getNumericCellValue());
                } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                    value = sdf.format(cell.getDateCellValue());
                } else {
                    value = df2.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;
    }

    /**
     * 型号的模板文件下载
     * @param data
     * @param pnType
     * @throws IOException
     */
    public static void downExclePnTypeTemplate(List<List<String>> data, String pnType) throws IOException {
        Workbook wk=new HSSFWorkbook();//创建一个工作薄
        Sheet sh=wk.createSheet(pnType);//创建一个sheet页
        Row row=sh.createRow(0);//创建第一行
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("分类");
        row.createCell(2).setCellValue("型号");
        row.createCell(3).setCellValue("描述");
        row.createCell(4).setCellValue("厂牌");
        row.createCell(5).setCellValue("包装方式");
        row.createCell(6).setCellValue("最小包装");
        row.createCell(7).setCellValue("单价");
        row.createCell(8).setCellValue("规格");
        row.createCell(9).setCellValue("PDF链接");
        for(int i =0;i<data.size();i++){
            Row row1 = sh.createRow(i + 1);
            for(int x =0;x<data.get(i).size();x++){
                row1.createCell(x).setCellValue(data.get(i).get(x));
            }
        }
        //怎么储存到updalopi
        FileOutputStream out= new FileOutputStream("D:\\用Poi搞出来的cells和sheet页.xls");
        wk.write(out);
        out.close();
    }
    public static void downExcelData(HttpServletResponse response, Workbook workbook, String downName){
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + downName);
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            workbook.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




//
//    public static void main(String[] args) throws IOException {
//        List<List<String>> data = new ArrayList<List<String>>();
//        for(int i =0;i<5;i++){
//            data.add(new ArrayList<String>());
//            for(int x =0;x<5;x++){
//                data.get(i).add("\""+i+"------"+x+"\"");
//            }
//        }
//        downExclePnTypeTemplate(data,"111");
//    }
}
