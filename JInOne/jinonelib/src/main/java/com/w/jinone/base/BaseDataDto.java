package com.w.jinone.base;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;

public class BaseDataDto<T> extends JsonData{

    private int error;
    private String msg;
    private JsonElement data;
    private int offset;
    private int show;   //是否显示状态   1显示   0显示
    private int has_more;//为0没有下一页

    public int getHasmore() {
        return has_more;
    }

    public void setHasmore(int hasmore) {
        this.has_more = hasmore;
    }

    public int getShow() {
        return show;
    }

    public void setShow(int show) {
        this.show = show;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

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

    public T getDataBean(Class<T> cls){
        if(cls==getClass()){
            return (T) this;
        }
        if(data!=null){
            Gson gson = new Gson();
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
            Gson gson = new Gson();
            try {
                return gson.fromJson(data, type);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
