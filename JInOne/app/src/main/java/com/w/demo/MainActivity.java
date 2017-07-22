package com.w.demo;

import android.view.KeyEvent;

import com.w.demo.vu.MainVu;
import com.w.jinone.base.BasePresenterActivity;
import com.w.jinone.base.VuManager;

/**
 * Created by yutao on 2017/7/22.
 */

public class MainActivity extends BasePresenterActivity<MainVu> {


    @Override
    protected Class<MainVu> getVuClass() {
        return MainVu.class;
    }

    protected void onBindVu() {

    }


    protected void afterResume() {
        vu.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        vu.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void onDestroyVu() {
        vu.onDestroy();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return VuManager.getInstance().backClick();
        }
        return super.onKeyUp(keyCode, event);
    }

}
