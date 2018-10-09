package com.example.zjl.lib.Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by Administrator on 2018/10/9 0009.
 */

public class FutureDemo {
    public static void main(String[] args) throws Exception {

        Callable<Integer> call = new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                System.out.println("正在计算结果...");
                Thread.sleep(3000);
                return 1;
            }
        };

        FutureTask<Integer> task = new FutureTask<>(call);
        Thread thread = new Thread(task);
        thread.start();

        // do something
        System.out.println(" 干点别的...");

        Integer result = task.get();//获取任务执行结果，任务结束之前会阻塞。

        System.out.println("拿到的结果为：" + result);

    }

}
