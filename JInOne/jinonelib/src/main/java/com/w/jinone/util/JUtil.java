package com.w.jinone.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yutao on 2017/2/21.
 */

public class JUtil {

    public static <T> T fromJson(String json, Class<T> classOfT){
        if(json!=null){
            Gson gson = new Gson();
            try {
                return gson.fromJson(json, classOfT);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String getVideoData(String bodyHTML) {
        String head = "<head><style>img{max-width: 100%; width:auto; height: auto;}</style></head>";
        return "<html>" + head + "<video width='360' height='270' controls='controls' autoplay='autoplay'>" +
                "<source src='"+bodyHTML+"' type='video/mp4' /> </video></body></html>";
    }

    public static String getHtmlData(String bodyHTML) {
        String head = "<head><style>img{max-width: 100%; width:auto; height: auto;}</style></head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }

    public static int getVersionCode(Context context){
        PackageManager packageManager=context.getPackageManager();
        PackageInfo packageInfo;
        int versionCode=0;
        try {
            packageInfo=packageManager.getPackageInfo(context.getPackageName(),0);
            versionCode=packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * get App versionName
     * @param context
     * @return
     */
    public static String getVersionName(Context context){
        PackageManager packageManager=context.getPackageManager();
        PackageInfo packageInfo;
        String versionName="";
        try {
            packageInfo=packageManager.getPackageInfo(context.getPackageName(),0);
            versionName=packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }


    public static <T> T fromJsonElement(JsonElement json, Type type){
        if(json!=null){
            Gson gson = new Gson();
            try {
                return gson.fromJson(json, type);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public final static String MD5(String str) {
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update((str).getBytes("UTF-8"));
            byte b[] = md5.digest();

            int i;
            StringBuffer buf = new StringBuffer("");

            for(int offset=0; offset<b.length; offset++){
                i = b[offset];
                if(i<0){
                    i+=256;
                }
                if(i<16){
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            Log.e("TAG", "MD5: "+buf.toString());
            return buf.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String dateToStr(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    public static String dateToStr(String str) {
        if(str==null){
            return "";
        }
        long lSysTime1= Long.parseLong(str);
        Date dt = new Date(lSysTime1 * 1000);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dt);
        return dateString;
    }

    public static String dateToStr(long time) {
        Date dt = new Date(time * 1000);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd mm:ss");
        String dateString = formatter.format(dt);
        return dateString;
    }


    public static String fromTodayTime(long  time) {
        return fromToday(new Date(time*1000));
    }

    public static String fromToday(Date date) {
        if (date == null)
            return "";
        long delta = (new Date().getTime() - date.getTime()) / 1000;
        //if(delta<=0)return date.toLocaleString();
        if (delta / (60 * 60 * 24 * 365) > 0 || delta < 0) {
            SimpleDateFormat time = new SimpleDateFormat("yyyy年MM月dd日");
            Log.e("tag", "fromToday: " + time.format(date));
            return time.format(date);
        }
        if (delta / (60 * 60 * 24 * 30) > 0) {
            SimpleDateFormat time = new SimpleDateFormat("MM月dd日");
            return time.format(date);
        }
//		if(delta/(60*60*24*7) > 0)return delta/(60*60*24*7) +"周前";
        if (delta / (60 * 60 * 24) > 0) {
            if ((delta / (60 * 60 * 24)) > 1 && (delta / (60 * 60 * 24)) <= 2)
                return "前天";

            return delta / (60 * 60 * 24) + "天前";
        }
        if (delta / (60 * 60) > 0) {
            int nday = new Date().getDate();
            int bday = date.getDate();
            if (nday != bday) {
                return "昨天";
            }
            return delta / (60 * 60) + "小时前";
        }
        if (delta / (60) > 0) return delta / (60) + "分钟前";
        return "刚刚";
    }

    public static String fromToday(long time) {
        return fromToday(new Date(time));
    }


}
