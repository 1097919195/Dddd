package com.example.zjl.dddd.fragment;

import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ProgressBar;


import com.example.zjl.dddd.R;
import com.example.zjl.dddd.app.AppConstant;
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.baserx.RxSchedulers;
import com.jaydenxiao.common.commonutils.ImageLoaderUtils;
import com.jaydenxiao.common.commonutils.ToastUtil;


import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;


import butterknife.BindView;


import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * des:图文新闻详情
 * Created by xsf
 * on 2016.09.9:57
 */
public class PhotoDetailFragment extends BaseFragment {
    @BindView(R.id.photo_view)
    PhotoView photoView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private String mImgSrc;

    @Override
    protected int getLayoutResource() {
        return R.layout.fra_news_photo_detail;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        if (getArguments() != null) {
            mImgSrc = getArguments().getString(AppConstant.PHOTO_DETAIL_IMGSRC);
        }
        initPhotoView(mImgSrc);
        setPhotoViewClickEvent();

    }

    //显示加载图片
    private void initPhotoView(String mImgSrc) {
        //first(加载本地的图片)
        if (mImgSrc == "1") {
            photoView.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.home1));
        }
        if (mImgSrc == "2") {
            photoView.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.home2));
        }
        if (mImgSrc == "3") {
            photoView.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.home3));
        }
        if (mImgSrc == "4") {
            photoView.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.home4));
        }



        //second
        ImageLoaderUtils.displayBigPhoto(getContext(),photoView,mImgSrc);
        //此处使用观察者模式会报错，所以直接使用glide加载
//        Observable.timer(100, TimeUnit.MILLISECONDS) // 直接使用glide加载的话，activity切换动画时背景短暂为默认背景色
//                .compose(RxSchedulers.<Long>io_main())
//                .subscribe(new DisposableObserver<Long>() {
//                    @Override
//                    public void onComplete() {
//                        progressBar.setVisibility(View.GONE);//当不会再有新的onNext() 发出后,隐藏加载动画
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        progressBar.setVisibility(View.GONE);
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//                        ImageLoaderUtils.displayBigPhoto(getContext(),photoView,mImgSrc);
//                    }
//                });
    }

    //图片点击
    private void setPhotoViewClickEvent() {
        photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float v, float v1) {
                ToastUtil.showShort("点击了图片");
            }
        });
    }

}
