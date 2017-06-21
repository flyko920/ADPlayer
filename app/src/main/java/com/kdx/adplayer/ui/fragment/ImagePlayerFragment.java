package com.kdx.adplayer.ui.fragment;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kdx.adplayer.R;
import com.kdx.adplayer.beans.ADBean;
import com.kdx.adplayer.ui.ADFinishListener;
import com.kdx.adplayer.utils.L;

import java.io.File;

/**
 * Created by kdx on 2017/6/14.
 * Email:zhangpengfei@kdxfilm.com
 */

public class ImagePlayerFragment extends Fragment {

    private View view;
    private TextView image_ad_textview, image_filename_textview;
    private ImageView imageView;
    private CountDownTimer countDownTimer;
    private ADFinishListener mADFinishListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), getLayout(), null);
        initView();
        initListener();
        initData();
        return view;
    }

    private void initData() {

    }

    private void initListener() {

    }

    @Override
    public void onStop() {
        super.onStop();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public void setADInfo(ADBean adInfo, long time, ADFinishListener mADFinishListener) {
        this.mADFinishListener = mADFinishListener;
        String filePath = Environment.getExternalStorageDirectory() + "/Download/pic/" + adInfo.getFileName();
        L.d("  == 文件地址  ==  " + filePath);
        File file = new File(filePath);
        if (file.exists()) {
//            Bitmap bm = BitmapFactory.decodeFile(filePath);
//            L.d("  == 文件大小  ==  " + bm.getHeight() + " X " + bm.getWidth());
//            imageView.setImageBitmap(bm);
//            bm.recycle();
            image_filename_textview.setText(adInfo.getFileName());

            Glide.with(getContext()).load(file).into(imageView);
        }
        countDownTimer = startCountDownTime(time);
        countDownTimer.start();

    }

    private void initView() {
        image_ad_textview = view.findViewById(R.id.image_ad_text_view);
        imageView = view.findViewById(R.id.imageview);
        image_filename_textview = view.findViewById(R.id.image_filename_textview);
    }

    private int getLayout() {
        return R.layout.image_player_fragment;
    }

    private CountDownTimer startCountDownTime(long time) {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        /**
         * 最简单的倒计时类，实现了官方的CountDownTimer类（没有特殊要求的话可以使用）
         * 即使退出activity，倒计时还能进行，因为是创建了后台的线程。
         * 有onTick，onFinsh、cancel和start方法
         */
        CountDownTimer timer = new CountDownTimer(time * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (image_ad_textview != null) {
                    L.d("onTick  " + millisUntilFinished / 1000);
                    image_ad_textview.setText("广告 " + millisUntilFinished / 1000 + " 秒");
                }
            }

            @Override
            public void onFinish() {
                L.d("onFinish -- 倒计时结束");
                mADFinishListener.adFinish();
            }
        };
//        timer.start();// 开始计时
        //timer.cancel(); // 取消
        return timer;
    }
}
