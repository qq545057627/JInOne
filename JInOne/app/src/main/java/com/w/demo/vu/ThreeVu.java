package com.w.demo.vu;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.w.demo.R;
import com.w.jinone.base.BaseVu;
import com.w.jinone.base.VuManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yutao on 2017/7/22.
 */

public class ThreeVu extends BaseVu {

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        super.init(inflater, container);
        view = inflater.inflate(R.layout.three_vu, container, false);
        ButterKnife.bind(this, view);
        context = view.getContext();
    }

    @OnClick(R.id.show_btn)
    public void tvClick(){
        OneVu oneVu=new OneVu();
        oneVu.init(context);
        VuManager.getInstance().pushVu(oneVu);
    }

    @OnClick(R.id.back_btn)
    public void backClick(){
        VuManager.getInstance().popVu();
    }

}
