package com.example.zjl.lib.asdfasdf;

public class adapter {

    public static void main(String[] args) {

        zhangSan zhangSan = new zhangSan();

        zhangSan.play();
    }


}

interface love{
    void eat();
    void play();
}

class lover implements love{

    @Override
    public void eat() {
        System.err.println("eat...");
    }

    @Override
    public void play() {
        System.err.println("play...");
    }
}

class zhangSan extends lover{
    @Override
    public void play() {
//        super.play();
        System.err.println("play agin...");
    }
}