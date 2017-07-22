package com.w.jinone.net;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.w.jinone.base.CallBackObject;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yutao on 2016/4/18 0018.
 */
public class NetManager<T> {

    private static NetManager instance;

    private Context context;

    private RequestQueue mQueue = null;

    private final int DEFAULT_MAX_RETRIES=3;

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

    public void postRequest(Request<?> request){
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
                        Log.e("TAG", "response -> "+url+"  ->"+ response.toString());
                        if(callBack!=null&&response!=null){
                            Gson gson = new Gson();
                            try {
                                Class < T >  entityClass  =  (Class < T > ) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[ 0 ];
                                callBack.CallBackData(gson.fromJson(response.toString(),entityClass));
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.e("TAG", ""+error.networkResponse.statusCode+new String(error.networkResponse.data));
                if(callBack!=null&&error.networkResponse!=null){
                    Gson gson = new Gson();
                    try {
                        Class < T >  entityClass  =  (Class < T > ) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[ 0 ];
                        callBack.CallBackData(gson.fromJson(new String(error.networkResponse.data),entityClass));
                    }catch (Exception e){
                        e.printStackTrace();
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
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Accept", "application/json");
                //headers.put("Content-Type", "application/json; charset=UTF-8");

                //headers.put("X-Requested-With", "XMLHttpRequest");
                return headers;
            }
        };
        mQueue.add(jsonRequest);
    }


    public void postJsonRequest(String url, JSONObject jsonObject, final CallBackObject<T> callBack){
        postJsonRequest(url, Request.Method.POST, jsonObject, callBack);
    }


    public void postRequest(String url, final HashMap<String,String> params , final CallBackObject callBack){

        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        //Log.e("TAG", "response -> " + response);
                        if(callBack!=null&&response!=null){
                            Gson gson = new Gson();
                            try {
                                Class < T >  entityClass  =  (Class < T > ) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[ 0 ];
                                callBack.CallBackData(gson.fromJson(response.toString(),entityClass));
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
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
        mQueue.add(stringRequest);
    }

}
