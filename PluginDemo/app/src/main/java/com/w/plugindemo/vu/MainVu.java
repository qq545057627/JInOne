package com.w.plugindemo.vu;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.w.jinone.base.BaseVu;
import com.w.jinone.base.VuManager;
import com.w.plugindemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainVu extends BaseVu {

    @Bind(R.id.viewContainer)
    public RelativeLayout viewContainer;
    @Bind(R.id.mainBody)
    public RelativeLayout mainBody;
    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.main_vu, container, false);
        ButterKnife.bind(this, view);
        context = view.getContext();
        //初始化视图管理器
        VuManager.getInstance().initContainerView(viewContainer);
        VuManager.getInstance().setMainBody(mainBody);

    }

    @OnClick(R.id.oneBtn)
    public void oneBtnClick(){
        OneVu oneVu=new OneVu();
        oneVu.init(context);
        VuManager.getInstance().pushVu(oneVu);
    }

}