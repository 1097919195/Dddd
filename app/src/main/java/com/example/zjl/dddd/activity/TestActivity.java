package com.example.zjl.dddd.activity;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.zjl.dddd.R;
import com.example.zjl.dddd.bean.SortModel;
import com.jaydenxiao.common.commonutils.LogUtils;
import com.jaydenxiao.common.commonutils.ToastUtil;
import com.vector.update_app.UpdateAppManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import util.UpdateAppUtils;

public class TestActivity extends AppCompatActivity {

    private ViewFlipper vflp_help;
    List<SortModel> mAllContactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_test);

        ContentResolver resolver = getApplicationContext().getContentResolver();
        Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, "sort_key"}, null, null, "sort_key COLLATE LOCALIZED ASC");
        if (phoneCursor == null || phoneCursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "未获得读取联系人权限 或 未获得联系人数据", Toast.LENGTH_SHORT).show();
            return;
        }
        int PHONES_NUMBER_INDEX = phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        int PHONES_DISPLAY_NAME_INDEX = phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int SORT_KEY_INDEX = phoneCursor.getColumnIndex("sort_key");
        if (phoneCursor.getCount() > 0) {
            while (phoneCursor.moveToNext()) {
                mAllContactsList = new ArrayList<SortModel>();
                String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX);
                if (TextUtils.isEmpty(phoneNumber))
                    continue;
                String contactName = phoneCursor.getString(PHONES_DISPLAY_NAME_INDEX);
                String sortKey = phoneCursor.getString(SORT_KEY_INDEX);
                SortModel sortModel = new SortModel(contactName, phoneNumber, sortKey);

                mAllContactsList.add(sortModel);
            }
        }
        phoneCursor.close();

        ToastUtil.showShort(mAllContactsList.get(0).number);
        LogUtils.loge(mAllContactsList.get(0).toString());


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "chat";
            String channelName = "聊天消息";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(channelId, channelName, importance);
            channelId = "subscribe";
            channelName = "订阅消息";
            importance = NotificationManager.IMPORTANCE_DEFAULT;
            createNotificationChannel(channelId, channelName, importance);
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    public void sendChatMsg(View view) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //这样写不用写两套，即8.0以上 8.0以下
        Notification notification = new NotificationCompat.Builder(this, "chat")
                .setContentTitle("收到一条聊天消息")
                .setContentText("今天中午吃什么？")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background))
                .setAutoCancel(true)
                .build();
        manager.notify(1, notification);
    }

    public void sendSubscribeMsg(View view) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this, "subscribe")
                .setContentTitle("收到一条订阅消息")
                .setContentText("地铁沿线30万商铺抢购中！")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background))
                .setAutoCancel(true)
                .build();
        manager.notify(2, notification);
    }

    public void OkHttp(View view) {
        getHttp();
    }

    public void Retrofit(View view) {
        getRetrofit();
    }

    //APP更新
    public void updateApp(View view) {
        UpdateAppUtils.from(this)
                .checkBy(UpdateAppUtils.CHECK_BY_VERSION_NAME) //更新检测方式，默认为VersionCode
                .serverVersionCode(2)
                .serverVersionName("2.0")
                .apkPath("https://www.npclo.com/dlo")
                .showNotification(true) //是否显示下载进度到通知栏，默认为true
                .updateInfo("更新更新")  //更新日志信息 String
                .downloadBy(UpdateAppUtils.DOWNLOAD_BY_APP) //下载方式：app下载、手机浏览器下载。默认app下载
                .isForce(false) //是否强制更新，默认false 强制更新情况下用户不同意更新则不能使用app
                .update();
    }

    public void getHttp() {
        OkHttpClient mClient = new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder()
                .url("http://192.168.199.163:80/json.txt");//直接开启 phpstudy 放一个json文本测试访问即可
        final Request request = requestBuilder.build();
        Call mcall = mClient.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("log", "Error" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonStr = response.body().string();
                Log.e("log", jsonStr);
//                JSONArray jsonArray= null;
//                try {
//                    jsonArray = new JSONArray(jsonStr);
//                    for (int i = 0; i <jsonArray.length(); i++) {
//                        JSONObject jsonObject=jsonArray.getJSONObject(i);
//                        String license1=jsonObject.getString("license1");
//                        String name=jsonObject.getString("name");
//                        String balance=jsonObject.getString("balance");
//                        Bean bean=new Bean(license1,name,balance);
//                        mdata.add(bean);
//                        System.out.println("license1" + license1 + ";name" + name + ";balance" + balance);
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

            }
        });
    }

    public void getRetrofit() {
        //还需要些ApiService、LoginBean
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://rj.zzx1983.com:30034")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        ApiService apiService = retrofit.create(ApiService.class);
//        Call<LoginBean> call = apiService.getlogin("pad01","123456");
//        call.enqueue(new Callback<LoginBean>() {
//            @Override
//            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
//                LoginBean loginBean= response.body();
//                Log.e("log",loginBean.message);
//            }
//
//            @Override
//            public void onFailure(Call<LoginBean> call, Throwable t) {
//
//                Log.e("log", "Error" + t.toString());
//            }
//        });

    }


}
