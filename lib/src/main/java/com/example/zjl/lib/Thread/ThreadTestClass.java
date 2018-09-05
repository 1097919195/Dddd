package com.example.zjl.lib.Thread;

import java.lang.management.ManagementFactory;

/**
 * Created by Administrator on 2018/9/5 0005.
 */

public class ThreadTestClass {

    public static void main(String[] args){
        //runnable
        MyRunnable runnable = new MyRunnable();
        new Thread(runnable).start();//同一个mt，但是在Thread中就不可以，如果用同一
        new Thread(runnable).start();//个实例化对象mt，就会出现异常
        new Thread(runnable).start();


        //thread
//        MyThread thread = new MyThread();
//        MyThread thread2 = new MyThread();
//        MyThread thread3 = new MyThread();
//        thread.start();
//        thread2.start();
//        thread3.start();

        //PID
        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(name);
        String pid = name.split("@")[0];
        System.out.println("Pid is:" + pid);


    }
}
