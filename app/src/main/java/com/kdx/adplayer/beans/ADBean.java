package com.kdx.adplayer.beans;

/**
 * Created by kdx on 2017/6/20.
 * Email:zhangpengfei@kdxfilm.com
 */

public class ADBean {

    private String fileName;

    public ADBean(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "ADttttttt{" +
                "fileName='" + fileName + '\'' +
                '}';
    }
}
