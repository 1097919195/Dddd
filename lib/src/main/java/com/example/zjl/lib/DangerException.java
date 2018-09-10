package com.example.zjl.lib;

/**
 * Created by Administrator on 2018/9/10 0010.
 */

public class DangerException extends Exception {
    String s;

    /*该异常类有构造方法，该构造方法使用super调用父类构造方法，
     *使用字符串：“属于危险品！”，对父类变量message进行初始化。
     */
    DangerException(String s) {
        super("属于危险品！");
        //message=s; Error:message cannot be resolved to a variable
        //this.message=s; Error:message cannot be resolved or is not a field
        //那如何对父类变量message进行初始化呢？
        //额，是不是：super("属于危险品！")就已经完成了对message的初始化？
        this.s = s;
    }

    @Override
    public String toString() {
        return s + ',' + this.getMessage();
    }
}
