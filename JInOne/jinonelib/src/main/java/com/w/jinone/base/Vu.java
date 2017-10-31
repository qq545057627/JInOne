package com.w.jinone.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;


/**
 * 
* @Description: 
* @author yutao  
* @date 2015年6月25日 下午3:09:39 
* @version V1.0
 */
public interface Vu<T> extends Serializable {
    void init(LayoutInflater inflater, ViewGroup container);
    void init(Context context);
    void delayInitData();      //初始化数据
    int depth();               //add view层次关系
    void seDepth(int depth);   //设置depth
    boolean callBackStatus();  //回调返回键状态
    boolean isCache();         //视图切换时候是否缓存Vu
    boolean isAllowMany();     //是否允许打开多个相同的Vu
    View getView();
    void onDestroy();
    void onPause();
    void onResume();
    void animStart();          //动画开始回调
    void animEnd();            //动画结束回调
    void customAnim(View currentView, View lastView);     //自定义动画回调重写实现
    //设置进场切换动画
    void setAnimSwitchTypeIn(AnimSwitchEnum animType);   //设置进场动画
    AnimSwitchEnum getAnimSwitchTypeIn();

    //设置退场切换动画
    void setAnimSwitchTypeOut(AnimSwitchEnum animType);  //设置出场动画
    AnimSwitchEnum getAnimSwitchTypeOut();
    boolean isNeedMask();     //是否需要遮罩
    void bindData(T data);    //绑定数据
}