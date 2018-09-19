package com.example.zjl.dddd.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.zjl.dddd.R;
import com.example.zjl.dddd.app.AppConstant;
import com.example.zjl.dddd.utils.ThemeUtil;
import com.jaydenxiao.common.commonutils.IpUtils;
import com.jaydenxiao.common.commonutils.LogUtils;

public class MainActivity extends AppCompatActivity {

    private ViewFlipper vflp_help;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置对应的主题 ，在ui创建好之后设置主题无效，所以要放到setContentView（）方法前面setTheme()
        ThemeUtil.onActivityCreatedSetTheme(this);
        setContentView(R.layout.act_main);
        Button button = (Button) findViewById(R.id.btn);
        Button btnToast = (Button) findViewById(R.id.btn_toast);
        vflp_help = (ViewFlipper) findViewById(R.id.vflp_help);
        vflp_help.startFlipping();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.loge(IpUtils.GetHostIp());
                Intent intent = new Intent(MainActivity.this, ViewPagerActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.toTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });

        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "小武", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                LinearLayout toastView = (LinearLayout) toast.getView();
                ImageView imageCodeProject = new ImageView(getApplicationContext());
                imageCodeProject.setImageResource(R.drawable.ic_launcher_background);
                toastView.addView(imageCodeProject, 0);
                toast.show();
            }
        });

    }
    public void onClick(View view){
        //切换日夜间模式
        ThemeUtil.ChangeCurrentTheme(this);

    }
}
