package com.kdx.adplayer.utils;

import android.text.TextUtils;

/**
 * Created by kdx on 2017/6/20.
 * Email:zhangpengfei@kdxfilm.com
 */

public class StringUtil {
    public static String getFileType(String fileName){
        return TextUtils.isEmpty(fileName)?null:fileName.substring(fileName.lastIndexOf(".")+1);
    }
}
