package com.w.jinone.base;

/**
 * Created by yutao on 2016/6/13 0013.
 */
public enum AnimSwitchEnum {


    None(0),           //无动画
    RightToLift(1),    //右到左
    LeftToRight(2),    //左到右
    BottomToUp(3),     //底部到顶部
    TopToDown(4),      //顶部到底部
    Custom(5);         //自定义动画
    public int  value = 0;
    AnimSwitchEnum(int value) {
        this.value = value;
    }
}
