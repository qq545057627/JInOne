package com.w.jinone.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


/**
 * @author yutao
 * @version V1.0
 * @Description:
 * @date 2015年6月25日 下午3:11:34
 */
public abstract class BasePresenterActivity<V extends Vu> extends Activity {

    protected V vu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        try {
            vu = getVuClass().newInstance();
            beforeCreate();
            vu.init(getLayoutInflater(), null);
            setContentView(vu.getView());
            onBindVu();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }


    public void beforeCreate() {

    }

    @Override
    protected void onPause() {
        beforePause();
        super.onPause();
        //MobclickAgent.onPause(this);
    }

    protected void beforePause() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        //MobclickAgent.onResume(this);
        afterResume();

    }

    protected void afterResume() {
    }

    @Override
    protected final void onDestroy() {
        onDestroyVu();
        super.onDestroy();
        vu = null;
    }

    @Override
    public final void onBackPressed() {
        if (!handleBackPressed()) {
            super.onBackPressed();
        }
    }
    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        onBefromIntent(intent);

    }
    public void onBefromIntent(Intent intent) {

    }
    public boolean handleBackPressed() {
        return false;
    }

    protected abstract Class<V> getVuClass();

    protected void onBindVu() {
    }

    protected void onDestroyVu() {
    }

}