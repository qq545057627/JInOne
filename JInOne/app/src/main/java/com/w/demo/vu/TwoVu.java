package com.w.demo.vu;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qihoo360.replugin.RePlugin;
import com.w.demo.R;
import com.w.jinone.base.BaseVu;
import com.w.jinone.base.Vu;
import com.w.jinone.base.VuManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yutao on 2017/7/22.
 */

public class TwoVu extends BaseVu {

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        super.init(inflater, container);
        view = inflater.inflate(R.layout.two_vu, container, false);
        ButterKnife.bind(this, view);
        context = view.getContext();
    }

    @OnClick(R.id.show_btn)
    public void btnClick(){
        ClassLoader d1ClassLoader = RePlugin.fetchClassLoader("demo3");//获取插件的ClassLoader
        try {
            Log.e(TAG, "btnClick: 11=="+d1ClassLoader);
            Class cls = d1ClassLoader.loadClass("com.w.xg.vu.LoginVu").getClass();
            Log.e(TAG, "btnClick: "+cls );
            Vu vu = d1ClassLoader.loadClass("com.w.xg.vu.LoginVu").asSubclass(Vu.class).newInstance();//使用插件的Classloader获取指定Fragment实例
            vu.init(context);
            VuManager.getInstance().pushVu(vu);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*ThreeVu threeVu=new ThreeVu();
        threeVu.init(context);
        VuManager.getInstance().pushVu(threeVu);*/
    }
    @OnClick(R.id.back_btn)
    public void backClick(){
        VuManager.getInstance().popVu();
    }
}
