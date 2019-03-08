package com.example.zjl.lib.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class TestTTTTT {
    /**
     * 因为用的是字节流所以
     * 控制台 中文 字符串 数字都可以输入输出
     * @param args
     */
    public static void main(String[] args) {
        // 标准的输入流对象 --读取操作
        InputStream is = System.in;
        // 标准的输出流对象---写的操作
        OutputStream os = System.out;

        try {
            // System.in是一个很原始、很简陋的输入流对象，通常不直接使用它来读取用户的输入。
            // 一般会在外面封装过滤流：
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            // 然后调用br.readLine()方法进行读取。
            String inputStr = br.readLine();

            // 把数据写出去，写出的类型是字节型
            os.write(inputStr.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("asdff");

    }

}
