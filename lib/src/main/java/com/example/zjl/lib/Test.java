package com.example.zjl.lib;

public class Test extends AA{
    public static void main(String[] args) {

        AA aa = new Test();
        aa.a();
        aa.b();

        //数组再初始化的时候长度就决定了，所以如果需要添加元素的话需要把数组变成arrayList
        String[] strings = new String[10];

    }

    @Override
    public void a() {
        super.a();
        System.err.println("a2");
    }

    @Override
    public void b() {
        System.err.println("b");
    }
}
abstract class AA{
    public void a(){//实现的方法a
        System.err.println("a1");
    }
    public abstract void b();//抽象方法
}