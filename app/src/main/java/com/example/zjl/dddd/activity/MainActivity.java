package com.example.zjl.dddd.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zjl.dddd.R;
import com.example.zjl.dddd.app.AppConstant;
import com.example.zjl.dddd.utils.ThemeUtil;
import com.jaydenxiao.common.commonutils.IpUtils;
import com.jaydenxiao.common.commonutils.LogUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置对应的主题 ，在ui创建好之后设置主题无效，所以要放到setContentView（）方法前面setTheme()
        ThemeUtil.onActivityCreatedSetTheme(this);
        setContentView(R.layout.act_main);
        Button button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.loge(IpUtils.GetHostIp());
                Intent intent = new Intent(MainActivity.this, ViewPagerActivity.class);
                startActivity(intent);
            }
        });

    }
    public void onClick(View view){
        //切换日夜间模式
        ThemeUtil.ChangeCurrentTheme(this);

    }
}
