package com.example.zjl.lib.template;

/**
 * Created by Administrator on 2018/11/10 0010.
 * 策咯模式
 */

public class Context {

    private SteamRice steamRice;

    private String RiceType;

    public Context(String RiceType) {

        this.RiceType = RiceType;

    }

    public void contextInterface() {

        if (RiceType.equals("珍珠米")) {

            steamRice = new ZhenZhuMi();

        } else if (RiceType.equals("尖米")) {

            steamRice = new JianMi();

        }

        steamRice.PutRice();

        steamRice.WashRice();

        steamRice.CookRice();

    }

}
