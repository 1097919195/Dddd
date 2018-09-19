package com.example.zjl.dddd.activity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.zjl.dddd.R;
import com.example.zjl.dddd.bean.SortModel;
import com.jaydenxiao.common.commonutils.LogUtils;
import com.jaydenxiao.common.commonutils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private ViewFlipper vflp_help;
    List<SortModel> mAllContactsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_test);

        ContentResolver resolver = getApplicationContext().getContentResolver();
        Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[] { ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, "sort_key" }, null, null, "sort_key COLLATE LOCALIZED ASC");
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

    }

}
