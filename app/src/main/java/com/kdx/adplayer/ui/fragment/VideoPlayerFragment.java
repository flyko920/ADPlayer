package com.kdx.adplayer.ui.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.kdx.adplayer.R;
import com.kdx.adplayer.beans.ADBean;
import com.kdx.adplayer.ui.ADFinishListener;
import com.kdx.adplayer.utils.L;

import java.io.File;

/**
 * Created by kdx on 2017/6/14.
 * Email:zhangpengfei@kdxfilm.com
 */
public class VideoPlayerFragment extends Fragment {
    private View view;
    private VideoView video_player_videoview;
    private TextView video_ad_text_view,video_filename_textview;
    private CountDownTimer countDownTimer;
    private ADFinishListener mADFinishListener;

    private class OnVideoPreparedListener implements MediaPlayer.OnPreparedListener {
        @Override
        public void onPrepared(MediaPlayer mp) {
            video_player_videoview.start();

            countDownTimer = startCountDownTime(mp.getDuration() / 1000);
            countDownTimer.start();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), getLayout(), null);
        initView();
        initListener();
        initData();
        return view;
    }

    private void initView() {
        video_player_videoview = view.findViewById(R.id.video_player_videoview);
        video_ad_text_view = view.findViewById(R.id.video_ad_text_view);
        video_filename_textview = view.findViewById(R.id.video_filename_textview);
    }

    private int getLayout() {
        return R.layout.video_player_fragment;
    }

    public void initData() {

    }

    public void setADInfo(ADBean ADBean, ADFinishListener adFinishListener) {
        this.mADFinishListener = adFinishListener;
        String filePath = Environment.getExternalStorageDirectory() + "/Download/vid/" + ADBean.getFileName();
        L.d("  == 文件地址  ==  " + filePath);
        File file = new File(filePath);
        if (file.exists()) {
            L.d("  == 文件大小  ==  " + file.getTotalSpace());
            video_player_videoview.setVideoPath(file.getPath());
            video_filename_textview.setText(ADBean.getFileName());
        }
    }

    public void initListener() {
        video_player_videoview.setOnPreparedListener(new OnVideoPreparedListener());
    }

    @Override
    public void onStop() {
        super.onStop();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
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
                L.d("onTick  " + millisUntilFinished / 1000);
                video_ad_text_view.setText("广告 " + millisUntilFinished / 1000 + " 秒");
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
