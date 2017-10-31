package com.w.jinone.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;


public class ItemHolder<T> extends RecyclerView.ViewHolder{

	private Vu vu;

    public ItemHolder(View itemView) {
		super(itemView);
	}
	public ItemHolder(Vu vu){
		super(vu.getView());
		this.vu =vu;
	}
	public void updata(T o){
		vu.bindData(o);
	}
}