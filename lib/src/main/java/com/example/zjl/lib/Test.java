package com.example.zjl.lib;

public class Test extends AA{
    public static void main(String[] args) {

        AA aa = new Test();
        aa.a();
        aa.b();

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