package com.zerodg.zdutil.util.JsonToExcel;

/**
 * @program: qfsx_root
 * @description: 测试
 * @author: ztz-prince
 * @create:
 * @date:2019-08-07-11:18
 **/
public class Test {

    public static void main(String[] args) {
        ExportExcel  exportExcel=new ExportExcel("C:/Users/ztz-prince/Desktop/userRoles.json");

        exportExcel.executeExport();


    }
}
