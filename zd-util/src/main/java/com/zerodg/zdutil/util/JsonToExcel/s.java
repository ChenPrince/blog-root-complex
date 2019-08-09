package com.zerodg.zdutil.util.JsonToExcel;

/**
 * @program: qfsx_root
 * @description:
 * @author: ztz-prince
 * @create:
 * @date:2019-08-07-14:49
 **/


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class s {
    public static void main(String[] args) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        try {
            FileOutputStream out = new FileOutputStream("C:/Users/ztz-prince/Desktop/moreStyleWrokNook.xlsx");
            XSSFSheet sheet = workbook.createSheet("stylesheet");

            /**
             * 简单设置行高
             */
            //第一行
            Row row0 = sheet.createRow(0);
            row0.setHeight((short) (500));
            //第6列
            Cell cell = row0.createCell(5);
            cell.setCellValue("height=500");


            /**
             * 测试合并单元格之后的各个位置
             */
            //合并单元格                                参数1：第一行  /     参数2：最后一行 /   参数3：第一列 /  参数4：最后一列 [在此范围之内]
            sheet.addMergedRegion(new CellRangeAddress(1,3,1,4));
            Row  row1 = sheet.createRow(1);
            Cell cell1 = row1.createCell(0);
            cell1.setCellValue("第二行 第一列");
            Cell cell2 = row1.createCell(1);
            cell2.setCellValue("第二行，第二列。应该是合并单元格");
            //既然合并了单元格，查看一下1.2在什么位置  【证明并没有出现】
            Cell cell3 = row1.createCell(2);
            cell3.setCellValue("第二行，第三列");

            Cell cell4 = row1.createCell(5);
            cell4.setCellValue("第二行，第五列，也就是合并单元格之后的第三列");

            /**
             * 测试cellstyle的设置--单元格居中设置以及单元格内文字换行设置
             */
            row0 = sheet.createRow(4);
            row0.setHeight((short)1000);
            cell1 = row0.createCell(0);
            cell1.setCellValue("第五行 height=1000");
            //设置某一列的宽度
            sheet.setColumnWidth(0, 9000);
            XSSFCellStyle style1 = workbook.createCellStyle();
            //设置style---cell中水平的对齐方式
            style1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            //设置style---cell中垂直方向的对齐方式
            style1.setVerticalAlignment(XSSFCellStyle.VERTICAL_TOP);
            //给某个确定的cell设置样式
            cell1.setCellStyle(style1);
            //为cell单元格追加内容
            //获取到cell内的值【getRichStringCellValue获取富文本类型的值，除了小数类型的使用这个方法获取getNumericCellValue，其余类型均可以使用此方法获取toString之后就可以转化为其他的数据类型】
            String cell1Value = cell1.getRichStringCellValue().toString();
            //cell1.getNumericCellValue();
            //加上\n之后无法自动换行
            cell1.setCellValue(cell1Value+"\n"+"水平居中"+"\n"+"垂直居上");
            //设置style自动换行    这样就可以自动换行了
            style1.setWrapText(true);
            cell1.setCellValue(cell1Value+"\n"+"水平居中"+"\n"+"垂直居上"+"\n"+"自动换行");
            System.out.println(cell1Value);

            /**
             * style样式设置--设置border的边框样式以及颜色
             */
            row0 = sheet.createRow(5);
            row0.setHeight((short)1000);
            cell1 = row0.createCell(5);
            cell1.setCellValue(6.6);
            XSSFCellStyle style2 = workbook.createCellStyle();
            style2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            style2.setBorderLeft(XSSFCellStyle.BORDER_HAIR);
            style2.setBorderRight(XSSFCellStyle.BORDER_DOTTED);
            style2.setBorderTop(XSSFCellStyle.BORDER_NONE);
            //颜色三种方式 给出   方式1：
            style2.setBottomBorderColor(IndexedColors.BLUE.getIndex());
            //方式2
            XSSFColor color = new XSSFColor();
            byte[] a = {127,0,13};
            //color.setRGB(a);
            color.setARGBHex("FF2906");
            style2.setLeftBorderColor(color);
            //方式3
            style2.setRightBorderColor(HSSFColor.BLACK.index);
            cell1.setCellStyle(style2);

            /**
             * style设置---设置单元格的背景色与填充效果
             */
            row0 = sheet.createRow(6);
            row0.setHeight((short) 1200);
            cell1 = row0.createCell(6);
            cell1.setCellValue(7.7);
            XSSFCellStyle style3 = workbook.createCellStyle();
            style3.setFillBackgroundColor(HSSFColor.RED.index);
            //设置单元格的填充效果
            style3.setFillPattern(XSSFCellStyle.LEAST_DOTS);
            cell1.setCellStyle(style3);


            /**
             * style设置--设置单元格的前置填充颜色
             */
            row0 = sheet.createRow(7);
            row0.setHeight((short) 1200);
            cell1 = row0.createCell(7);
            cell1.setCellValue(8.8);
            XSSFCellStyle style4 = workbook.createCellStyle();
            style4.setFillForegroundColor(IndexedColors.GREEN.index);
            style4.setFillPattern(XSSFCellStyle.ALIGN_FILL);
            cell1.setCellStyle(style4);


            workbook.write(out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
