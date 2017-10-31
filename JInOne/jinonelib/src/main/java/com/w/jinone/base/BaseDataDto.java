package com.w.jinone.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;

public class BaseDataDto<T> extends JsonData {

    private int error;
    private String msg;
    private JsonElement data;
    private boolean isShow=false;
    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public T getDataBean(Class<T> cls){
        if(cls==getClass()){
            return (T) this;
        }
        if(data!=null){
            Gson gson = new GsonBuilder().serializeNulls().create();
            try {
                return gson.fromJson(data, cls);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public T getDataBean(Type type){
        if(data!=null){
            Gson gson = new GsonBuilder().serializeNulls().create();
            try {
                return gson.fromJson(data, type);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
