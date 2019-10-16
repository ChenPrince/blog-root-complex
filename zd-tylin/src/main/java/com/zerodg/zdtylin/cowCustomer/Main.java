package com.zerodg.zdtylin.cowCustomer;


import java.util.Scanner;

/**
 * @program: eroot
 * @description:
 * @author: ztz-prince
 * @create:
 * @date:2019-08-20-20:03
 **/
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
//            int a = in.nextInt();
//            int b = in.nextInt();
//            System.out.println(a + b);
//        }
        //exp
        //5
        //1 3 4 6 8
        //10
        int len=in.nextInt();
        int[] array=new int[len];
        for(int i=0;i<len;i++){
            array[i]=in.nextInt();
        }
        int sum=in.nextInt();


        //从两头开始同时比较
        int res1=0,res2=0;
//        int headIndex = 0;
        int toilIndex=len-1;

        for(int i=0;i<=toilIndex;i++){

            if(toilIndex<=1){
                break;
            }
            if(sum==(array[i]+array[toilIndex])){
                res1=array[i];
                res2=array[toilIndex];
                break;
            }
            else if(sum<(array[i]+array[toilIndex])){
                toilIndex--;
                i=-1;
            }
        }

        if(res1==0&&res2==0){
            System.out.print("notfound");
        }else{
            System.out.print(res1+" "+res2);
        }


    }
}