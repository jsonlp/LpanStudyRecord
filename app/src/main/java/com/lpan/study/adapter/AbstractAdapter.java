package com.lpan.study.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import com.lpan.study.utils.CollectionUtils;

import java.util.List;

public abstract class AbstractAdapter<T> extends BaseAdapter {

    protected Context mContext;

    protected LayoutInflater mInflater;

    protected List<T> mList;

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    public abstract void clearItem();

    public abstract void addItem(T t);

    public abstract void addItem(List<T> list);

    public Object removeItem(int position) {
        return null;
    }

    public void addItem(int postion, T t) {
    }

    public List<T> getList() {
        return mList;
    }

    public void removeAll(List<T> list) {
        mList.removeAll(list);
    }

    public int getCount() {
        return CollectionUtils.isEmpty(mList) ? 0 : mList.size();
    }

    public boolean isEmpty() {
        return CollectionUtils.isEmpty(mList);
    }

    public boolean removeItem(T t) {
        return mList.remove(t);
    }

}
