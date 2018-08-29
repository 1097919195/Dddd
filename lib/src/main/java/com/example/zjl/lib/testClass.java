package com.example.zjl.lib;

/**
 * Created by Administrator on 2018/7/23 0023.
 */

public class testClass {
    /**
     * printf主要是继承了C语言的printf的一些特性，可以进行格式化输出
     * print就是一般的标准输出，但是不换行
     * println和print基本没什么差别，就是最后会换行
     * @param args
     */
    public static void main(String[] args){

        System.out.println("asdf");
        Presenter haha = new Presenter();
        haha.Dothing("call ...");

        testString();
    }

    //打印字符串测试
    private static void testString() {
        String str = "a.b.c.d.e.f";
//取代所有相应字符
        System.out.println(str.replace('.', '_'));
//取代所有相应字符串
        System.out.println(str.replace(".", "_"));
//target用regular expression
        System.out.println(str.replaceAll("\\.[b-d]{1}", "_"));
//只取代首个
        System.out.println(str.replaceFirst("\\.", "#"));
//两个字符串相应区域匹配
        System.out.println("regionMatches:"+str.regionMatches(true, 1, ".b.c", 0, 3));

        //数组拷贝
        int[] arr = new int[]{1,2,3,4,5};
        int[] arr2 = new int[]{6,7,8,9,10};
        System.arraycopy(arr, 0, arr2, 2, 3);
        for(int j:arr2){
            System.out.print(j+",");
        }

        String string = "You son of bitch!";
        string = string.concat("-").concat("_").concat("123");
        System.out.println(string);
        char[] chars = new char[string.length()];
        string.getChars(0, string.length(), chars, 0);//将字符串内容赋值到字符数组
        String newString = new String(chars, 0, chars.length);//将字符数组转化为字符串
        System.out.println("newString-"+newString);
    }


    public static class Presenter extends TestOtherInterface.BaseDothing implements TestInterface, TestOtherInterface{

        @Override
        public void doSomething() {

        }

        @Override
        public void doOtherSomething() {

        }

        @Override
        public void Dothing(String content) {
            System.out.println(content);
        }
    }

}
