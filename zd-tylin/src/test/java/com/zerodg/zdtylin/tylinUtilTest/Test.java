package com.zerodg.zdtylin.tylinUtilTest;

/**
 * @program: eroot
 * @description:
 * @author: ztz-prince
 * @create:
 * @date:2019-08-28-11:36
 **/
public class Test {

    public static void main(String[] args) {

        try {
            SJUR_ToExcel sjur_toExcel=new SJUR_ToExcel();
            sjur_toExcel.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
