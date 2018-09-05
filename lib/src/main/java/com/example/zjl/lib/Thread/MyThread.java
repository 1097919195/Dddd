package com.example.zjl.lib.Thread;

/**
 * Created by Administrator on 2018/9/5 0005.
 */

class MyThread extends Thread{

    private int ticket=10;
    @Override
    public void run(){
        for(int i=0;i<20;i++){
            if(this.ticket>0){
                System.out.println("卖票：ticket"+this.ticket--);
                System.out.println("Main : Id "+this.getId());
                System.out.println(Thread.currentThread().getName());
            }
        }
    }
};
