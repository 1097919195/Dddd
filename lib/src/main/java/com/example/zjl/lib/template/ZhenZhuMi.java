package com.example.zjl.lib.template;

/**
 * Created by Administrator on 2018/11/10 0010.
 */

class ZhenZhuMi implements SteamRice{
    @Override
    public void PutRice() {
        System.err.println("放珍珠");

    }

    @Override
    public void WashRice() {
        System.err.println("洗珍珠");
    }

    @Override
    public void CookRice() {
        System.err.println("烧珍珠");
    }
}
