package com.w.plugindemo.vu;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.w.jinone.base.AnimSwitchEnum;
import com.w.jinone.base.BaseVu;
import com.w.jinone.base.VuManager;
import com.w.plugindemo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yutao on 2017/7/22.
 */

public class OneVu extends BaseVu {

    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        super.init(inflater, container);
        view = inflater.inflate(R.layout.one_vu, container, false);
        ButterKnife.bind(this, view);
        context = view.getContext();
        setAnimSwitchTypeIn(AnimSwitchEnum.BottomToUp);
    }

    @OnClick(R.id.show_btn)
    public void btnClick(){

    }

    @OnClick(R.id.back_btn)
    public void backClick(){
        VuManager.getInstance().popVu();
    }
}
