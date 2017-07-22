package com.w.jinone.base;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * 
* @Description: 
* @author yutao  
* @date 2015年6月25日 下午3:09:23 
* @version V1.0
 */
public abstract class BasePresenterFragment<V extends Vu> extends Fragment {

    protected V vu;
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        try {
            vu = getVuClass().newInstance();
            vu.init(inflater, container);
            onBindVu();
            view = vu.getView();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public final void onDestroyView() {
        onDestroyVu();
        vu.onDestroy();
        vu = null;
        super.onDestroyView();
    }

    protected void onDestroyVu() {};

    @Override
    public final void onPause() {
        beforePause();
        super.onPause();
    }

    protected void beforePause(){}

    @Override
    public final void onResume() {
        super.onResume();
        afterResume();
    }

    protected void afterResume(){}

    protected void onBindVu(){}

    protected abstract Class<V> getVuClass();

}