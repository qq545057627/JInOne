package com.w.jinone.net;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.w.jinone.base.CallBackObject;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yutao on 2016/4/18 0018.
 */
public class NetManager<T> {

    private static NetManager instance;

    private Context context;

    private RequestQueue mQueue = null;

    private boolean isDebug=false;


    private final int DEFAULT_MAX_RETRIES=3;

    private HashMap<String, String> headers = new HashMap<String, String>();

    public static void init(Context context){
        getInstance(context);
    }

    public static NetManager getInstance(Context context){
        if(instance==null){
            instance=new NetManager(context);
            return instance;
        }
        return instance;
    }


    public NetManager(Context context){
        this.context=context;
        mQueue = Volley.newRequestQueue(context);
    }

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public void addHeaderValue(String key,String value){
        headers.put(key,value);
    }

    public void clearHeader(){
        headers.clear();
    }

    public void postRequest(Request<?> request){
        request.setRetryPolicy(new DefaultRetryPolicy(10000,
                DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mQueue.add(request);

    }

    //登录获取cookie
    public void postJsonRequest(final String url, int method, JSONObject jsonObject, final CallBackObject<T> callBack){
        if(jsonObject==null){
            jsonObject=new JSONObject();
        }
        //Log.e("TAG", "postRequest: "+jsonObject );
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(method,url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(isDebug){
                            Log.e("TAG", "response -> "+url+"  ->"+ response.toString());
                        }
                        if(callBack!=null&&response!=null){
                            Gson gson = new Gson();
                            try {
                                Type[] types=callBack.getClass().getGenericInterfaces();
                                ParameterizedType paraType=(ParameterizedType)types[0];
                                T data=gson.fromJson(response.toString(),paraType.getActualTypeArguments()[0]);
                                callBack.CallBackData(data);
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if(callBack!=null){
                    if(error.networkResponse!=null){
                        Log.e("TAG", ""+error.networkResponse.statusCode+new String(error.networkResponse.data));
                        callBack.CallBackData(null);
                    }else{
                        callBack.CallBackData(null);
                    }

                }
            }
        })
        {
            //注意此处override的getParams()方法,在此处设置post需要提交的参数根本不起作用
            //必须象上面那样,构成JSONObject当做实参传入JsonObjectRequest对象里
            //所以这个方法在此处是不需要的
//    @Override
//    protected Map<String, String> getParams() {
//          Map<String, String> map = new HashMap<String, String>();
//            map.put("name1", "value1");
//            map.put("name2", "value2");

//        return params;
//    }

            @Override
            public Map<String, String> getHeaders() {
                //HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Accept", "application/json");
                //headers.put("Content-Type", "application/json; charset=UTF-8");

                //headers.put("X-Requested-With", "XMLHttpRequest");
                return headers;
            }
        };
        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(3000,
                DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mQueue.add(jsonRequest);
    }


    public void postJsonRequest(String url, JSONObject jsonObject, final CallBackObject<T> callBack){
        postJsonRequest(url, Request.Method.POST, jsonObject, callBack);
    }


    public void postRequest(String url, final HashMap<String,String> params , final CallBackObject<T> callBack){

        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Log.e("TAG", "response -> " + response);
                        if(callBack!=null&&response!=null){
                            Gson gson = new GsonBuilder().serializeNulls().create();
                            try {
                                Type[] types=callBack.getClass().getGenericInterfaces();
                                ParameterizedType paraType=(ParameterizedType)types[0];
                                Class <T>  entityClass  =  (Class ) paraType.getActualTypeArguments()[0];
                                callBack.CallBackData(gson.fromJson(response.toString(),entityClass));
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(callBack!=null&&error.networkResponse!=null){
                    Gson gson = new GsonBuilder().serializeNulls().create();
                    try {
                        Type[] types=callBack.getClass().getGenericInterfaces();
                        ParameterizedType paraType=(ParameterizedType)types[0];
                        Class < T >  entityClass  =  (Class < T > ) paraType.getActualTypeArguments()[0];
                        callBack.CallBackData(gson.fromJson(new String(error.networkResponse.data),entityClass));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }) {
            @Override
            protected Map getParams() {
                if(params==null){
                    return new HashMap<String,String>();
                }
                //Log.e("TAG", "getParams: "+params.toString());
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
                DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mQueue.add(stringRequest);
    }

}
