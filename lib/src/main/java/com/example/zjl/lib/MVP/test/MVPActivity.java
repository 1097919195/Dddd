package com.example.zjl.lib.MVP.test;


import com.example.zjl.lib.MVP.base.BaseMVPActivity;

import java.util.logging.Logger;

/**
 * Created by Administrator on 2018/11/9 0009.
 */

public class MVPActivity extends BaseMVPActivity<MVPPresenter> implements MVPContract.View {

    //也可以不重写，反正继承了，实例化也能调用父类的方法
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initPresenter() {
        // presenter 关联 view
        if (presenter == null) {
            presenter = new MVPPresenter();
        }
        presenter.attachView(MVPActivity.this);
    }

    @Override
    protected void initView() {
        presenter.getData();
    }


    @Override
    public void returnData(String data) {
        System.err.println("MVPTest----" + data);
    }
}
