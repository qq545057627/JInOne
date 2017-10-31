package com.w.jinone.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by yutao on 2016/3/10 0010.
 */
public interface ViewHolderDelegate<T,V> {

    public int getViewType(T t);

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    public void onBindViewHolder(V holder, T t, int position);

}
