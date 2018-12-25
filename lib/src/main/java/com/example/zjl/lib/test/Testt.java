package com.example.zjl.lib.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Testt {
    public static void main(String[] args) {

        Father father = new Son("jack");

//        D:\StudioProgram\Dddd\lib\src\main\java\com\example\zjl\lib\test\Father.java
        File file = new File("D:\\StudioProgram\\Dddd\\lib\\src\\main\\java\\com\\example\\zjl\\lib\\test\\Father.java");
        try {
            FileInputStream inputStream = new FileInputStream(file);
            FileOutputStream outputStream = new FileOutputStream("aaaaaaa.java");
            //第一种
//            int ch = 0;
//            while ((ch = inputStream.read()) != -1) {
//                outputStream.write(ch);
//            }
            //第二种
            byte[] buff = new byte[1024];//此方法不能保证长度的有效性，比如下载的apk解析失败
            while (inputStream.read(buff) != -1) {
                outputStream.write(buff, 0, buff.length);//没有保证有效的字节长度
            }
            //第三种
//            byte[] bytes = new byte[inputStream.available()];
//            inputStream.read(bytes);
//            outputStream.write(bytes);

            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getValidLength(byte[] bytes){//获取有效的字节长度
        int i = 0;
        if (null == bytes || 0 == bytes.length)
            return i ;
        for (; i < bytes.length; i++) {
            if (bytes[i] == '\0')
                break;
        }
        return i;
    }
}
