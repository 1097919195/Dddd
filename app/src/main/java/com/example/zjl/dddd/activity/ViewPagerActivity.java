package com.example.zjl.dddd.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.zjl.dddd.R;
import com.example.zjl.dddd.adapter.BannerAdapter;
import com.example.zjl.dddd.app.AppConstant;
import com.example.zjl.dddd.bean.NewsPhotoDetail;
import com.example.zjl.dddd.fragment.PhotoDetailFragment;
import com.example.zjl.dddd.utils.DrawableAndStringUtils;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.base.BaseFragmentAdapter;
import com.jaydenxiao.common.commonutils.LogUtils;
import com.jaydenxiao.common.commonutils.RegexUtils;
import com.jaydenxiao.common.commonutils.ToastUtil;
import com.jaydenxiao.common.commonwidget.ViewPagerFixed;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/7/11 0011.
 */

public class ViewPagerActivity extends BaseActivity {
    @BindView(R.id.edt)
    EditText editText;
    @BindView(R.id.btnPager)
    Button btn;
    @BindView(R.id.viewPagerFixed)
    ViewPagerFixed viewPagerFixed;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.rg)
    RadioGroup rg;

    private List<Fragment> mPhotoDetailFragmentList = new ArrayList<>();
    private NewsPhotoDetail mNewsPhotoDetail = new NewsPhotoDetail();

    boolean isAutoPlay = false;
    private Handler handler;
    private static final int SEND_MSG = 1;
    private int currentItem = 1;
    private int delay = 3000;

    @Override
    public int getLayoutId() {
        return R.layout.act_pager;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        NewsPhotoDetail.Picture picture1 = new NewsPhotoDetail.Picture();
        NewsPhotoDetail.Picture picture2 = new NewsPhotoDetail.Picture();
        NewsPhotoDetail.Picture picture3 = new NewsPhotoDetail.Picture();
        NewsPhotoDetail.Picture picture4 = new NewsPhotoDetail.Picture();

        NewsPhotoDetail.Picture picture1s = new NewsPhotoDetail.Picture();
        NewsPhotoDetail.Picture picture4s = new NewsPhotoDetail.Picture();
        //first(网络图片)
        picture1s.setImgSrc("http://cms-bucket.nosdn.127.net/2018/07/14/f6fc13e424b44ce48800bf3b90a708e0.png");
        picture4s.setImgSrc("http://cms-bucket.nosdn.127.net/2018/07/14/6b574bfcbecb42b89e699ab0ea6f04f2.jpeg");

        picture1.setImgSrc("http://cms-bucket.nosdn.127.net/2018/07/14/f6fc13e424b44ce48800bf3b90a708e0.png");
        picture2.setImgSrc("http://cms-bucket.nosdn.127.net/2018/07/14/bfed70351d7046b09c1ecf51c86915f7.png");
        picture3.setImgSrc("http://cms-bucket.nosdn.127.net/2018/07/14/77ce1bebc2b64e2cac7af71cf71ff2c1.jpeg");
        picture4.setImgSrc("http://cms-bucket.nosdn.127.net/2018/07/14/6b574bfcbecb42b89e699ab0ea6f04f2.jpeg");

        //second(本地mipmap图片)
//        picture1s.setImgSrc("1");
//        picture4s.setImgSrc("4");
//
//        picture1.setImgSrc("1");
//        picture2.setImgSrc("2");
//        picture3.setImgSrc("3");
//        picture4.setImgSrc("4");

        List<NewsPhotoDetail.Picture> pictureList = new ArrayList<>();
        pictureList.add(picture4s);
        pictureList.add(picture1);
        pictureList.add(picture2);
        pictureList.add(picture3);
        pictureList.add(picture4);
        pictureList.add(picture1s);
        mNewsPhotoDetail.setPictures(pictureList);
        createFragment(mNewsPhotoDetail);
        initListener();
        initViewPagerFixed();
        initViewPager();
        starPlay();
    }

    /**
     * 开始自动播放图片
     */
    private void starPlay() {
        // 如果少于2张就不用自动播放了
        if (mPhotoDetailFragmentList.size()-2 < 2) {
            isAutoPlay = false;
        } else {
            isAutoPlay = true;
            handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if (msg.what == SEND_MSG) {
                        viewPagerFixed.setCurrentItem(currentItem);
                    }
                }
            };
            handler.postDelayed(task, delay);//方法一
//            initTimer();//方法二

        }
    }

    private void initTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask()
        {
            @Override
            public void run() {
                if (isAutoPlay) {
                    currentItem++;
//                //当currentItem等于图片大小的时候记得将 currentItem重置为1
                    if (currentItem > mPhotoDetailFragmentList.size()) {
                        currentItem = 1;
                    }
                    // 正常每隔3秒播放一张图片

                    Message message = new Message();
                    message.what = SEND_MSG;
                    handler.sendMessage(message);
                } else {
                    // 如果处于拖拽状态停止自动播放，会每隔5秒检查一次是否可以正常自动播放。
//                    timer.cancel();
                }
            }
        }, delay, delay);


    }

    Runnable task = new Runnable() {
        @Override
        public void run() {
            if (isAutoPlay) {
                currentItem++;
//                //当currentItem等于图片大小的时候记得将 currentItem重置为1
                if (currentItem > mPhotoDetailFragmentList.size()) {
                    currentItem = 1;
                }
                // 正常每隔3秒播放一张图片
                Message message = new Message();
                message.what = SEND_MSG;
                handler.sendMessage(message);

                handler.postDelayed(task, delay);
            } else {
                // 如果处于拖拽状态停止自动播放，会每隔5秒检查一次是否可以正常自动播放。
                handler.postDelayed(task, 5000);
            }
        }
    };

    private void initViewPager() {
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.home1);
        list.add(R.mipmap.home2);
        list.add(R.mipmap.home3);
        list.add(R.mipmap.home4);
        BannerAdapter bannerAdapter = new BannerAdapter(this,viewPager,list);
        viewPager.setAdapter(bannerAdapter);
    }

    private void createFragment(NewsPhotoDetail newsPhotoDetail) {
        mPhotoDetailFragmentList.clear();
        for (NewsPhotoDetail.Picture picture : newsPhotoDetail.getPictures()) {
            PhotoDetailFragment fragment = new PhotoDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString(AppConstant.PHOTO_DETAIL_IMGSRC, picture.getImgSrc());
            fragment.setArguments(bundle);
            mPhotoDetailFragmentList.add(fragment);
        }
    }

    private void initViewPagerFixed() {
        rg.check(R.id.rb0);
        BaseFragmentAdapter photoPagerAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), mPhotoDetailFragmentList);
        viewPagerFixed.setAdapter(photoPagerAdapter);
        viewPagerFixed.setCurrentItem(1);
        viewPagerFixed.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //https://www.jianshu.com/p/3e712a26bc30 viewpager无限循环
                if (positionOffset == 0.0){
                    if (position == 0){
                        //当滑到第一张图时显示最后一张图并将postion跳至"D"位置
                        viewPagerFixed.setCurrentItem(mPhotoDetailFragmentList.size()-2,false);

                    }
                    //当滑到最后一张图时显示第一张图并将position跳至"A"位置
                    else if (position == mPhotoDetailFragmentList.size()-1)
                    {
                        viewPagerFixed.setCurrentItem(1,false);
                    }
                }

            }

            @Override
            public void onPageSelected(int position) {
//                rg.getChildAt(position).performClick();
                switch (position) {
                    case 1:
                        rg.check(R.id.rb0);
                        break;
                    case 2:
                        rg.check(R.id.rb1);
                        break;
                    case 3:
                        rg.check(R.id.rb2);
                        break;
                    case 4:
                        rg.check(R.id.rb3);
                        break;
                    default:
                        break;
                }
                LogUtils.loge("viewPagerFixed-position == "+position);
                setDetailTitle(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
//                    // 闲置中
                    case ViewPager.SCROLL_STATE_IDLE:
                        currentItem = viewPagerFixed.getCurrentItem();
                        isAutoPlay = true;
                        break;
                    // 拖动中
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        isAutoPlay = false;
                        break;
                    // 设置pager中
                    case ViewPager.SCROLL_STATE_SETTLING:
                        isAutoPlay = true;
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void setDetailTitle(int position) {
    }

    private void initListener() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb0:
                        viewPagerFixed.setCurrentItem(1);
                        break;
                    case R.id.rb1:
                        viewPagerFixed.setCurrentItem(2);
                        break;
                    case R.id.rb2:
                        viewPagerFixed.setCurrentItem(3);
                        break;
                    case R.id.rb3:
                        viewPagerFixed.setCurrentItem(4);
                        break;
                    default:
                        break;

                }

            }
        });

        btn.setOnClickListener(v -> {
            String s = editText.getText().toString();
            if (editText.getEditableText().length() > 0) {
                if (RegexUtils.isIDCard18(s)) {
                    ToastUtil.showShort("格式正确");
                }else {
                    ToastUtil.showShort("格式不正确");
                }

            }else {
                ToastUtil.showShort("请先输入需要检验的内容");
            }

        });
    }
}
