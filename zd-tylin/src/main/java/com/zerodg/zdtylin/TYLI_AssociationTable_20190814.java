package com.zerodg.zdtylin;

import java.util.Scanner;

/**
 * @program: eroot
 * @description: 关联userRole和roles
 * @author: ztz-prince
 * @create:
 * @date:2019-08-14-14:25
 **/
//
//输入可以有多个测试样例，每个测试由两行组成。
//
//        其中第一行包含一个整数P，表示商品的价格，1≤P≤10000；输入P为0时表示结束。
//
//        第二行包含若干整数，使用空格分割。其中第一个整数N（1≤N≤20）表示有多少种代金券，其后跟随M个整数，表示手中持有的代金券面额（1≤N≤1000），每种代金券数量不限。
//
//        输出
//        找到最少张数的代金券，使其面额恰好等于商品价格。输出所使用的代金券数量；
//
//        如果有多个最优解，只输出其中一种即可；
//
//        如果无解，则需输出“Impossible”。
//
//
//        样例输入
//        65
//        4 50 30 20 5
//        0
public class TYLI_AssociationTable_20190814 {
    public static void main(String[] args) {
        TYLI_AssociationTable_20190814 main=new TYLI_AssociationTable_20190814();
        main.act();
    }

    public void act(){

        Scanner sc=new Scanner(System.in);

        while(true){
            int count=sc.nextInt();
            if(count==0){
                break;
            }
            int num=sc.nextInt();
            int []array=new int[num];
            for(int i=0;i<num;i++){
                array[i]=sc.nextInt();
            }
            System.out.println(figure(count,array,0,0));

        }

    }

    /**
     *
     * @param count 剩余金钱
     * @param array 代金券数组
     * @param index 下标
     * @param result 结果
     */
    public int figure(int count,int []array,int index,int result){


        if(index+1==array.length){
            System.out.println("----------"+count);
            if(count!=0){
                return 100000;
            }

            return result;
        }
        int value=array[index];

        int rs=count-value;
        if(rs>0){

            System.out.println("=="+value);
            int result3=figure(count,array,index+1,result);
            result++;
            int result1=figure(rs,array,index,result);
            int result2=figure(rs,array,index+1,result);

            int box=result1<result2?result1:result2;
            box=box<result3?box:result3;


            return box;
        }
        else{
            return figure(count,array,index+1,result);
        }

    }



}
