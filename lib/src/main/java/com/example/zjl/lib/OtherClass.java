package com.example.zjl.lib;


/**
 * 多态指的是对象的多种形态。多态有两种：引用多态和方法多态。继承是多态的实现基础。
 * https://www.cnblogs.com/shouming/p/6752496.html
 */
public class OtherClass {
    public static void main(String[] args) {
        //父类的引用可以指向本类的对象
        Animal ani1 = new Animal();
        //父类的引用可以指向子类的对象
        Animal ani2 = new Dog();
        //输出动物具有吃的本领
        ani1.eat();
        //输出狗是吃肉的
        ani2.eat();
    }
}
