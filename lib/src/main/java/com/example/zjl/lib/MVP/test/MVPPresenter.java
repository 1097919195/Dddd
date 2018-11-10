package com.example.zjl.lib.MVP.test;


import com.example.zjl.lib.MVP.base.BasePresenter;

import java.util.Observable;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import sun.rmi.runtime.Log;


/**
 * Created by Administrator on 2018/11/9 0009.
 */
//继承了BasePresenter<MVPContract.View>的话就可以获取到之后关联的view，不一定实现attachView和detachView(需要用的话再重写),重写后在baseMVPActivity中执行连接和断开view的关联情况
public class MVPPresenter extends BasePresenter<MVPContract.View> implements MVPContract.Presenter {

    @Override
    public void getData() {
        getView().returnData("简单的MVP回调成功了！");
        getView().showError("回调失败了！");
    }

    //和view关联成功的时候会执行该方法（一般都用不到所以不重写）
    @Override
    public void attachView(MVPContract.View view) {
        super.attachView(view);
    }

    //取消订阅及断开了和view的关联操作
    @Override
    public void detachView() {
        super.detachView();
    }
}
