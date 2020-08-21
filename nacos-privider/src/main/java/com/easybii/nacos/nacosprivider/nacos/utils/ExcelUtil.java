package com.easybii.nacos.nacosprivider.nacos.utils;

import com.easybii.nacos.nacosprivider.nacos.VO.ExcelVO;
import com.baidu.aip.ocr.AipOcr;
import com.google.gson.internal.LinkedTreeMap;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2020,
 * Author: linyu902
 * Date: 2020/7/28 9:51
 * FileName: ExcelUtil
 */
public class ExcelUtil {

    /**
     * 把数据写入到Excel文件
     * @param fileName 自动生成的Excel文件的全路径文件名称
     * @param vos 要写入到Excel文件中的数据
     */

    public static void writeExcel(String fileName, List<ExcelVO> vos) throws IOException {


        //创建Excel文件
        File excelFile = new File(fileName.trim());

        String[] titles = {"ID","产品名称", "规格型号", "单位", "除税价链接", "含税价", "备注" , "除税价"};

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        sheet.setColumnWidth(0,20*256);
        sheet.setColumnWidth(1,20*256);
        sheet.setColumnWidth(2,18*256);
        sheet.setColumnWidth(3,18*256);
        sheet.setColumnWidth(4,18*256);
        sheet.setColumnWidth(5,18*256);
        sheet.setColumnWidth(6,18*256);
        sheet.setColumnWidth(7,18*256);
        sheet.setColumnWidth(8,18*256);
        HSSFRow row = sheet.createRow(0);
        row.setHeightInPoints(25);
        HSSFCellStyle style = wb.createCellStyle();
        HSSFCellStyle style2 = wb.createCellStyle();
        HSSFFont font = wb.createFont();

        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        style.setFont(font);
        //设置边框样式
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        // 设置边框颜色
        style.setBottomBorderColor(IndexedColors.BLUE_GREY.index);
        style.setTopBorderColor(IndexedColors.BLUE_GREY.index);
        style.setLeftBorderColor(IndexedColors.BLUE_GREY.index);
        style.setRightBorderColor(IndexedColors.BLUE_GREY.index);

        //设置边框样式
        style2.setBorderTop(BorderStyle.THIN);
        style2.setBorderBottom(BorderStyle.THIN);
        style2.setBorderLeft(BorderStyle.THIN);
        style2.setBorderRight(BorderStyle.THIN);
        // 设置边框颜色
        style2.setBottomBorderColor(IndexedColors.BLUE_GREY.index);
        style2.setTopBorderColor(IndexedColors.BLUE_GREY.index);
        style2.setLeftBorderColor(IndexedColors.BLUE_GREY.index);
        style2.setRightBorderColor(IndexedColors.BLUE_GREY.index);

        //设置表头
        for (int i = 0; i < titles.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(style);
        }
        int rowNum = 0;


        HSSFFont font2 = wb.createFont();
        font2.setFontHeightInPoints((short) 12);
        style2.setFont(font2);
        if (vos.size() != 0 && vos != null) {
            for (ExcelVO vo : vos) {
                rowNum += 1;
                row = sheet.createRow(rowNum);
                row.setHeightInPoints(28);
                // 询价标题
                if (vo.getId() != null && !"".equals(vo.getId())) {
                    HSSFCell cell = row.createCell(0);
                    cell.setCellValue(vo.getId());
                    cell.setCellStyle(style2);
                }
                // 询价公司
                if (vo.getName() != null && !"".equals(vo.getName())) {
                    HSSFCell row1 = row.createCell(1);
                    row1.setCellValue(vo.getName());
                    row1.setCellStyle(style2);


                }
                // 询价用途
                if (vo.getSep() != null && !"".equals(vo.getSep())) {
                    HSSFCell row2 = row.createCell(2);
                    row2.setCellValue(vo.getSep());
                    row2.setCellStyle(style2);
                }
                // 询价人
                if (vo.getDw() != null && !"".equals(vo.getDw())) {
                    HSSFCell row3 = row.createCell(3);
                    row3.setCellValue(vo.getDw());
                    row3.setCellStyle(style2);
                }


                // 询价状态
                if (vo.getPriceUrl() != null && !"".equals(vo.getPriceUrl())) {
                    HSSFCell row4 = row.createCell(4);
                    row4.setCellStyle(style2);


                    row4.setCellValue(vo.getPriceUrl());


                }
                // 询价时间
                HSSFCell row5 = row.createCell(5);
                if (vo.getTaxPrice() != null && !"".equals(vo.getTaxPrice())) {
                    row5.setCellStyle(style2);
                    row5.setCellValue(vo.getTaxPrice());
                    row5.setCellStyle(style2);
                }


                // 发布时间
                HSSFCell row6 = row.createCell(6);
                if (vo.getNote() != null && !"".equals(vo.getNote())) {
                    row6.setCellStyle(style2);
                    row6.setCellValue(vo.getNote());
                }
                // 扣除条数
                HSSFCell row7 = row.createCell(7);
                if (vo.getNoTaxPrice() != null && !"".equals(vo.getNoTaxPrice())) {
                    row7.setCellStyle(style2);
                    row7.setCellValue(vo.getNoTaxPrice());
                }
            }
        }
        //把Excel工作薄写入到Excel文件
        FileOutputStream os = new FileOutputStream(excelFile);
        wb.write(os);
        os.flush();
        os.close();
    }

    /**
     * 从Excel文件读取数据
     * @param fileName 要读取的Excel文件的全路径文件名称
     * @return 从Excel文件中批量导入的用户数据
     */
    public static List<ExcelVO> readExcel(String fileName) throws IOException {
        Workbook workbook = null;
        Sheet sheet = null;
        Row row = null;

        //读取Excel文件
        File excelFile = new File(fileName.trim());
        InputStream is = new FileInputStream(excelFile);

        //获取Excel工作薄
        if (excelFile.getName().endsWith("xlsx")) {
            workbook = new XSSFWorkbook(is);
        } else {
            workbook = new HSSFWorkbook(is);
        }
        if (workbook == null) {
            System.err.println("Excel文件有问题,请检查！");
            return null;
        }

        //获取Excel表单
        sheet = workbook.getSheetAt(0);

        List<ExcelVO> vos = new ArrayList<>();
        for(int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            //获取一行
            row = sheet.getRow(rowNum);
            ExcelVO vo = new ExcelVO();
            vo.setId(Long.valueOf(getStringValue(row.getCell(0))));
            vo.setName(getStringValue(row.getCell(1)));
            vo.setSep(getStringValue(row.getCell(2)));
            vo.setDw(getStringValue(row.getCell(3)));
            vo.setPriceUrl(getStringValue(row.getCell(4)));
            vo.setTaxPrice(getStringValue(row.getCell(5)));
            vo.setNote(getStringValue(row.getCell(6)));
            vos.add(vo);
        }
        is.close();
        return vos;
    }


    /**
     * 设置普通单元格的边框
     * @param style 要设置的边框的样式
     * @param cellStyle 单元格样式对象
     */
    private static void setCellBorderStyle(BorderStyle style, CellStyle cellStyle) {
        cellStyle.setBorderTop(style);
        cellStyle.setBorderBottom(style);
        cellStyle.setBorderLeft(style);
        cellStyle.setBorderRight(style);
    }


    /**
     * 设置表头单元格样式
     * @param workbook 工作薄对象
     * @return 单元格样式对象
     */
    private static CellStyle getHeaderCellStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();

        //设置字体
        Font font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 20);
        font.setBold(true);
        cellStyle.setFont(font);

        //设置文字居中显示
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        //设置单元格的边框
        setCellBorderStyle(BorderStyle.THIN, cellStyle);

        return cellStyle;
    }

    /**
     * 设置表体单元格样式
     * @param workbook 工作薄对象
     * @return 单元格样式对象
     */
    private static CellStyle getBodyCellStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();

        //设置字体
        Font font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 16);
        cellStyle.setFont(font);

        //设置文字居中显示
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        //设置单元格的边框
        setCellBorderStyle(BorderStyle.THIN, cellStyle);

        return cellStyle;
    }

    /**
     * 获取单元格的值的字符串
     * @param cell 单元格对象
     * @return cell单元格的值的字符串
     */
    private static String getStringValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        CellType cellType = cell.getCellType();
        switch (cellType) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                double value = cell.getNumericCellValue();
                return String.valueOf(Math.round(value));
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return null;
        }
    }

    public static void main(String[] args) throws Exception {
        String fileName = "C:\\Users\\EDZ\\Downloads\\长春信息价718.xlsx";
        List<ExcelVO> excelVOS = ExcelUtil.readExcel(fileName);
        excelVOS.forEach( excelVO -> System.out.println("文件图片路径------------>" + excelVO.getPriceUrl()));
        // 组装写出对象
        ArrayList<ExcelVO> vos = new ArrayList<>();
        int count = 0;
        for (ExcelVO excelVO : excelVOS) {
            count += 1;
            ExcelVO vo = new ExcelVO();
            BeanUtils.copyProperties(excelVO,vo);
            if (excelVO.getPriceUrl() != null && excelVO.getPriceUrl() != "0" && !"".equals(excelVO.getPriceUrl())){
                AipOcr aipOcr = Sample.getAipOcr();
                String s = Sample.sample(aipOcr, excelVO.getPriceUrl());
                System.out.println("s = " + s);
                // 获取识别结果
                Map map = GsonUtils.fromJson(s, Map.class);
                if (map.size() != 0 &&map != null && map.get("error_msg") == null && map.get("error_code") == null){
                    List<LinkedTreeMap> words_result = (List<LinkedTreeMap>) map.get("words_result");
                    if (words_result.size() != 0 && words_result != null){
                        for (LinkedTreeMap linkedTreeMap : words_result) {
                            if (linkedTreeMap.size() != 0 && linkedTreeMap != null){
                                Object words = linkedTreeMap.get("words");
                                String s1 = words.toString();
                                vo.setNoTaxPrice(s1);

                                System.out.println("一共《" + excelVOS.size() + "》条,当前第《"+ count + "》条");
                                System.out.println("对象信息------------------>" + vo.getNoTaxPrice());
                            }
                        }
                    }
                }
                vos.add(vo);
            }
        }
        // 写出到Excel
        ExcelUtil.writeExcel("C:\\Users\\EDZ\\Desktop\\photos\\1.xlsx",vos);
    }

    /**
     * 创建写入到Excel中的数据
     * @return 用户数据集合
     */
//    private static List<ExcelVO> getExcelData() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        List<ExcelVO> users = new ArrayList<>();
//        for(int i = 1; i <= 10; i++) {
//            ExcelVO vo = new ExcelVO();
//            user.setId((long) i);
//            user.setName("user" + i);
//            user.setSex((i % 2) == 0);
//            user.setAge(i);
//            user.setBirthday(sdf.format(new Date()));
//            users.add(user);
//        }
//        return users;
//    }

    /**
     * 打印用户数据
     * @param users 要打印的用户数据集合
     */
//    private static void printList(List<User> users) {
//        if(users == null) {
//            System.out.println("用户数据为空");
//            return;
//        }
//        for(User user : users) {
//            System.out.println(user.toString());
//        }
//    }
}
