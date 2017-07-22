package com.w.jinone.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonData{

    public JsonData(){}

    public JsonData(int showStyle){
        this.showStyle=showStyle;
    }

    protected int showStyle;

    public int getShowStyle() {
        return showStyle;
    }

    public void setShowStyle(int showStyle) {
        this.showStyle = showStyle;
    }

    @Override
    public String toString() {
        Gson gson=new GsonBuilder().create();
        String json = gson.toJson(this);
        return json;
    }
}
