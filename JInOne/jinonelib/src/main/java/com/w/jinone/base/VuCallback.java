package com.w.jinone.base;

import java.io.Serializable;

/**
 * 
* @Description: 
* @author yutao  
* @date 2015年6月25日 下午3:11:05 
* @version V1.0
 */
public interface VuCallback<T> extends Serializable{
    void execute(T result);
}