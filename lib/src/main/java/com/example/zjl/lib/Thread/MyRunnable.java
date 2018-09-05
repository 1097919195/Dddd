package com.example.zjl.lib.Thread;

import java.io.PrintWriter;

/**
 * Created by Administrator on 2018/9/5 0005.
 */

public class MyRunnable implements Runnable {
    private int ticket=10;
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (this.ticket > 0) {
                System.out.println("卖票：ticket" + this.ticket--);
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

//    private void MyRunnableInfo(Thread thread, Thread.State state){
//        System.out.println("Main : Id "+thread.getId()+" - "+thread.getName()+"\n");
//        System.out.println("Main : Priority: "+thread.getPriority()+"\n");
//        System.out.println("Main : Old State: "+state+"\n");
//        System.out.println("Main : New State: "+thread.getState()+"\n");
//        System.out.println("Main : ***************************\n");
//    }

}
