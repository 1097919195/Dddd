package com.example.zjl.lib;

/**
 * Created by Administrator on 2018/8/30 0030.
 */

public abstract class AbstractClass {
    public abstract void sendCrashLogToServer(String s);

    void writeFile() {
        System.out.println("errorFile writeSD");
        sendCrashLogToServer("errorFile");
    }
}
