package com.w.jinone.base;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yutao on 2016/3/4 0004.
 */
public class BaseVu<T> implements Vu<T> ,View.OnTouchListener {

    protected Context context;
    protected View view;
    protected AnimSwitchEnum animTypeIn=AnimSwitchEnum.RightToLift;
    protected AnimSwitchEnum animTypeOut=AnimSwitchEnum.LeftToRight;
    protected int depth=0;
    protected int delayInitTime=300;
    protected boolean isNeedMask=true;       //dialog形式的时候默认显示mask
    protected String TAG=getClass().getSimpleName();
    protected boolean isSaveVu=true;    //切换的时候是否保存上一个Vu，默认缓存上一个Vu

    public BaseVu(){
    }

    public BaseVu(Context context){
        init(LayoutInflater.from(context),null);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                delayInitData();
            }
        },delayInitTime);

    }

    public BaseVu(Context context, ViewGroup container){
        init(LayoutInflater.from(context), container);

    }

    public boolean isNeedMask() {
        return isNeedMask;
    }

    public void setNeedMask(boolean needMask) {
        isNeedMask = needMask;
    }

    public void init(Context context){
        init(LayoutInflater.from(context),null);

    }

    @Override
    public void delayInitData() {

    }

    public AnimSwitchEnum getAnimTypeIn() {
        return animTypeIn;
    }

    public void setAnimTypeIn(AnimSwitchEnum animTypeIn) {
        this.animTypeIn = animTypeIn;
    }

    public AnimSwitchEnum getAnimTypeOut() {
        return animTypeOut;
    }

    public void setAnimTypeOut(AnimSwitchEnum animTypeOut) {
        this.animTypeOut = animTypeOut;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getDelayInitTime() {
        return delayInitTime;
    }

    public void setDelayInitTime(int delayInitTime) {
        this.delayInitTime = delayInitTime;
    }

    @Override
    public int depth() {
        return depth;
    }

    @Override
    public void seDepth(int depth) {
        this.depth=depth;
    }

    @Override
    public boolean callBackStatus() {
        return true;
    }

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {

    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void onDestroy() {
        context=null;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume(){

    }

    @Override
    public void animStart() {

    }

    @Override
    public void animEnd() {

    }

    @Override
    public void customAnim(View currentView, View lastView) {

    }


    public boolean isCache() {
        return isSaveVu;
    }

    @Override
    public boolean isAllowMany() {
        return false;
    }

    public void setSaveVu(boolean saveVu) {
        isSaveVu = saveVu;
    }

    @Override
    public void setAnimSwitchTypeIn(AnimSwitchEnum animType) {
        if(animType!=null){
            this.animTypeIn =animType;
            setAnimSwitchTypeOut(animType);   //兼容以前没有out动画的情况
        }
    }

    @Override
    public AnimSwitchEnum getAnimSwitchTypeIn() {
        return animTypeIn;
    }

    @Override
    public void setAnimSwitchTypeOut(AnimSwitchEnum animType) {
        if(animType!=null){
            animTypeOut=animType;
        }
    }

    @Override
    public AnimSwitchEnum getAnimSwitchTypeOut() {
        return animTypeOut;
    }

    @Override
    public void bindData(T t) {

    }

    public void show(){
        view.setVisibility(View.VISIBLE);
    }
    public void hide(){
        view.setVisibility(view.GONE);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }


}
