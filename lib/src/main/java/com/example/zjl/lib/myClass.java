package com.example.zjl.lib;

public class myClass {
    //声明：对异常不处理，收到异常我也向外抛
    public static void main(String[] args) throws Exception {
        DivDemo dd = new DivDemo();

        int resultD = dd.div(10, 0);
        System.out.println("resultD' value is :" + resultD);
        System.out.println("over!");
    }
}

class DivDemo {
    //在功能上通过throws的关键字--
    //--声明：调用该方法有可能会出现问题，可能向外抛异常
    public int div(int a, int b) throws Exception {

        return a / b;
    }
}
