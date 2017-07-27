package com.w.plugindemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class MainVuActivity extends BasePresenterActivity<MainVu> {


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

