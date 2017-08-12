package com.w.jinone.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private List<T> mListItems=new ArrayList<T>();

	private ViewHolderDelegate viewHolderDelegate;


	public ViewHolderDelegate getViewHolderDelegate() {
		return viewHolderDelegate;
	}

	public void setViewHolderDelegate(ViewHolderDelegate viewHolderDelegate) {
		this.viewHolderDelegate = viewHolderDelegate;
	}

	public List<T> getmListItems() {
		return mListItems;
	}
	public void setmListItems(List<T> listItems) {
		this.mListItems = listItems;
		if(mListItems==null){
			mListItems=new ArrayList<T>();
		}
	}
	public void deleteAll(){
		mListItems.clear();
		notifyDataSetChanged();
	}

	@Override
	public int getItemCount() {
		return mListItems.size();
	}

	@Override
	public int getItemViewType(int position) {
		T o=mListItems.get(position);
		return viewHolderDelegate.getViewType(o);
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return viewHolderDelegate.onCreateViewHolder(parent, viewType);
	}

	@Override
	public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
		viewHolderDelegate.onBindViewHolder(holder,mListItems.get(position),position);
		if (mOnItemClickListener != null) {//如果设置了监听那么它就不为空，然后回调相应的方法
			holder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int postion = holder.getLayoutPosition();//得到当前点击item的位置postion
					mOnItemClickListener.onItemClick(holder.itemView, postion);
				}
			});
		}
	}

	private int itemHeight;
	public int getItemHeight(){ return itemHeight;}

	private OnItemClickListener mOnItemClickListener;

	public interface OnItemClickListener {
		void onItemClick(View view, int position);
	}

	public void setOnItemClickListener(OnItemClickListener listener) {//对外提供的一个监听方法
		this.mOnItemClickListener = listener;
	}
}