package com.kdx.adplayer.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.kdx.adplayer.R;
import com.kdx.adplayer.beans.ADBean;
import com.kdx.adplayer.ui.fragment.ImagePlayerFragment;
import com.kdx.adplayer.ui.fragment.VideoPlayerFragment;
import com.kdx.adplayer.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kdx on 2017/6/14.
 * Email:zhangpengfei@kdxfilm.com
 */

public class ADPlayerActivity extends FragmentActivity {

    private ViewPager adplayer_viewpager;
    private List<Fragment> fragments;
    private ADPlayerAdapter adapter;
    public ArrayList<ADBean> ADBeen;
    public int position = 0;
    private ArrayList<ADBean> testData;
    private VideoPlayerFragment mVideoPlayerFragment;
    private ImagePlayerFragment mImagePlayerFragment;

    private class ADPlayerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> fragments;

        public ADPlayerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adplayer_activity);
        initView();
        initListener();
        initData();
    }

    private void initData() {
        mVideoPlayerFragment = new VideoPlayerFragment();
        mImagePlayerFragment = new ImagePlayerFragment();
        fragments.add(mImagePlayerFragment);
        fragments.add(mVideoPlayerFragment);
        adapter.notifyDataSetChanged();
//        playAD();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                playAD();
            }
        }, 5000);
    }

    private void playAD() {
        testData = getTestData();
        if (position >= testData.size()) {
            position = 0;
        }
        switch (StringUtil.getFileType(testData.get(position).getFileName())) {
            case "gif":
            case "jpg":                     //显示图片
                showImage();
                break;
            case "mp4":                     //显示视频
            case "flv":
                showVideo();
                break;
        }

        position++;
    }

    private void showVideo() {
        adplayer_viewpager.setCurrentItem(1);
        mVideoPlayerFragment.setADInfo(testData.get(position), new ADFinishListener() {
            @Override
            public void adFinish() {
                playAD();
            }
        });
    }

    private void showImage() {
        adplayer_viewpager.setCurrentItem(0);
        mImagePlayerFragment.setADInfo(testData.get(position), 3, new ADFinishListener() {
            @Override
            public void adFinish() {
                playAD();
            }
        });


    }

    private void initListener() {
        fragments = new ArrayList<Fragment>();
        adapter = new ADPlayerAdapter(getSupportFragmentManager(), fragments);
        adplayer_viewpager.setAdapter(adapter);
    }


    private void initView() {
        adplayer_viewpager = (ViewPager) findViewById(R.id.adplayer_viewpager);
    }

    public ArrayList<ADBean> getTestData() {
        ADBeen = new ArrayList<>();

        ADBeen.add(new ADBean("timg5.jpg"));
        ADBeen.add(new ADBean("4_test.mp4"));
        ADBeen.add(new ADBean("timg6.jpg"));
        ADBeen.add(new ADBean("2_oppo.mp4"));
        ADBeen.add(new ADBean("timg7.jpg"));
        ADBeen.add(new ADBean("5_test.mp4"));
        ADBeen.add(new ADBean("timg8.jpg"));

        ADBeen.add(new ADBean("1_oppo.mp4"));
        ADBeen.add(new ADBean("3_oppo.mp4"));


        return ADBeen;
    }

}
