package com.example.zjl.lib.MVP.base;


import java.util.logging.Logger;

import sun.rmi.runtime.Log;

/**
 * Created by Administrator on 2018/11/9 0009.
 */

public abstract class BaseMVPActivity<P extends BaseContract.Presenter> implements BaseContract.View{

    protected P presenter;

    public void onCreate() {
        getLayoutId();
        initPresenter();
        initView();
    }

    protected abstract int getLayoutId();

    protected abstract void initPresenter();//MVP模式的时候需要在这个抽象实现里获取view -> presenter.attachView(this);

    protected abstract void initView();

    public static boolean Exists(String strSql)
    {

        if (strSql == "")
        {
            System.err.println("false");
            return false;
        }
        else
        {
            System.err.println("true");
            return true;
        }
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void showError(String message) {
        System.err.println("BaseMVPActivity----"+ message);
    }

    public void onDestroy() {
        if (presenter != null) {
            System.err.println("BaseMVPActivity----" + "presenter解除关联View");
            presenter.detachView();
        }
    }
}
