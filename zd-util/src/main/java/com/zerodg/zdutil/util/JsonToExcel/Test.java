package com.zerodg.zdutil.util.JsonToExcel;



import java.io.IOException;

/**
 * @program: qfsx_root
 * @description: 测试
 * @author: ztz-prince
 * @create:
 * @date:2019-08-07-11:18
 **/
public class Test {

    public static void main(String[] args) {
        ExportExcel  exportExcel=new ExportExcel("C:/Users/ztz-prince/Desktop/userRoles_.json");

        exportExcel.executeExport();


    }


    public static String null2String(String var0) {
        return var0 == null ? "" : var0;
    }
}
