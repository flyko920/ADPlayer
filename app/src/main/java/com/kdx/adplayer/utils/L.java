package com.kdx.adplayer.utils;

import android.util.Log;

/**
 * 
* @Description: 自定义Log日志输出类，可以配置默认TAG，可以控制日志输出或者关闭
* @author 张鹏飞
*
 */
public class L {

    private static final String AD_LOG = "KDX_AD_LOG";
    private static final boolean isLogEnabled = true;
	  
    private static final String TAG_CONTENT_PRINT = "%s:%s.%s:%d";  
  
    private static StackTraceElement getCurrentStackTraceElement() {  
        return Thread.currentThread().getStackTrace()[4];  
  
    }  
  
    public static void trace() {  
        if (isLogEnabled) {
            Log.d(AD_LOG,
                    getContent(getCurrentStackTraceElement()));  
        }  
    }  
  
    private static String getContent(StackTraceElement trace) {  
        return String.format(TAG_CONTENT_PRINT, AD_LOG,
                trace.getClassName(), trace.getMethodName(),  
                trace.getLineNumber());  
    }  
    public static void traceStack() {  
        if (isLogEnabled) {
            traceStack(AD_LOG, Log.ERROR);
        }  
    }  
  
    public static void traceStack(String tag, int priority) {  
  
        if (isLogEnabled) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();  
            Log.println(priority, tag, stackTrace[4].toString());
            StringBuilder str = new StringBuilder();  
            String prevClass = null;  
            for (int i = 5; i < stackTrace.length; i++) {  
                String className = stackTrace[i].getFileName();  
                int idx = className.indexOf(".java");  
                if (idx >= 0) {  
                    className = className.substring(0, idx);  
                }  
                if (prevClass == null || !prevClass.equals(className)) {  
  
                    str.append(className.substring(0, idx));  
  
                }  
                prevClass = className;  
                str.append(".").append(stackTrace[i].getMethodName())  
                        .append(":").append(stackTrace[i].getLineNumber())  
                        .append("->");  
            }  
            Log.println(priority, tag, str.toString());
        }  
    }  
    public static void d(String tag, String msg) {  
        if (isLogEnabled) {
            Log.d(tag, getContent(getCurrentStackTraceElement())+">"+msg);  
        }  
    }  
    public static void d(String msg) {  
        if (isLogEnabled) {
            Log.d(AD_LOG, getContent(getCurrentStackTraceElement())+">"+msg);
        }  
    }  
    public static void i(String tag,String msg){  
        if (isLogEnabled) {
            Log.i(tag, getContent(getCurrentStackTraceElement())+">"+msg);  
        }  
    }  
    
    public static void i(String msg) {  
        if (isLogEnabled) {
            Log.i(AD_LOG, getContent(getCurrentStackTraceElement())+">"+msg);
        }  
    }  
    public static void w(String tag,String msg){  
        if (isLogEnabled) {
            Log.w(tag, getContent(getCurrentStackTraceElement())+">"+msg);  
        }  
    } 
    public static void w(String msg) {  
        if (isLogEnabled) {
            Log.w(AD_LOG, getContent(getCurrentStackTraceElement())+">"+msg);
        }  
    } 
    public static void e(String tag,String msg){  
        if (isLogEnabled) {
            Log.e(tag, getContent(getCurrentStackTraceElement())+">"+msg);  
        }  
    }  
    public static void e(String msg) {  
        if (isLogEnabled) {
            Log.e(AD_LOG, getContent(getCurrentStackTraceElement())+">"+msg);
        }  
    } 
} 