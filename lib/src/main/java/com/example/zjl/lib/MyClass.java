package com.example.zjl.lib;

import java.lang.ref.WeakReference;

public class MyClass {
    //声明：对异常不处理，收到异常我也向外抛
    public static void main(String[] args) throws Exception {
        DivDemo dd = new DivDemo();

        int a = 10;
        int b = 0;
        try {
//            if (b == 0) {
//                throw new IllegalArgumentException("argument error");
//            }
            int resultD = dd.div(a, b);
            System.out.println("resultD' value is :" + resultD);
            System.out.println("over!");
        } catch (Exception e) {
            System.out.println("exception: " + e.getMessage());
        } finally {
            System.out.println("finally");
        }


        //自定义的异常类型
        try {
            checkBag(-1);
        } catch (DangerException e) {
            System.out.println(e.toString());
        }


        WeakReference<String> sr = new WeakReference<String>(new String("hello"));
        System.out.println(sr.get());
        System.gc();                //通知JVM的gc进行垃圾回收
        System.out.println(sr.get());

    }


    public static void checkBag(int positive) throws DangerException {//不能忘记加
        //throws DangerException，否则下面的throw new 会报错！
        if (positive < 0 ){//0不是正数也不是负数
            throw new DangerException("是一个负数");
            //System.out.println(goods.getName()+",被禁止！");// Error:Unreachable code
        } else {
            System.out.println("检查通过");
        }
    }
}

class DivDemo {
    //在功能上通过throws的关键字--
    //--声明：调用该方法有可能会出现问题，可能向外抛异常
    public int div(int a, int b) throws Exception {

        return a / b;
    }


}
