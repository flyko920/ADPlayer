package com.kdx.adplayer.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by kdx on 2017/6/23.
 * Email:zhangpengfei@kdxfilm.com
 */
public class T {

    public static final int L = 0;
    public static final int S = 1;

    public static void show(Context context, String msg, int type) {
        switch (type) {
            case L:
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                break;
            case S:
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(context, "  type 类型错误  ", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public static void show(Context context, String msg) {
        show(context, msg, S);
    }
}
