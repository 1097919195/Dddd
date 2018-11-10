package com.example.zjl.lib.template;

/**
 * Created by Administrator on 2018/11/10 0010.
 */

public class Test {
    public static void main(String[] args) {
        Context context = new Context("珍珠米");
        context.contextInterface();

        WindowMenu win = new WindowMenu("俄罗斯方块",200,30,900,1000);
    }
}
