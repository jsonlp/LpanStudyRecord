package com.lpan.study.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lpan.study.listener.OnRowAdapterClickListener;
import com.lpan.study.utils.CollectionUtils;
import com.lpan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpan on 2016/12/19.
 */

public class NavegationAdapter extends BaseAdapter {

    private List<String> mList;

    private OnRowAdapterClickListener<String> mListener;

    private Context mContext;

    public NavegationAdapter(OnRowAdapterClickListener listener, Context context) {
        mList= new ArrayList<>();
        mListener = listener;
        mContext = context;

    }

    public void addItems(List<String> list) {
        mList.clear();
        mList.addAll(list);
    }

    public void addItem(String string) {
        mList.add(string);
    }

    @Override
    public int getCount() {
        return CollectionUtils.isEmpty(mList) ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Viewholder viewholder = null;
        if (convertView == null) {
            viewholder = new Viewholder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_home_holder_list, null);
            viewholder.index = (TextView) convertView.findViewById(R.id.index);
            viewholder.title = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(viewholder);
        } else {
            viewholder = (Viewholder) convertView.getTag();
        }

        viewholder.index.setText((position + 1) + "");
        viewholder.title.setText(mList.get(position));


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(v, mList.get(position), position);
                }
            }
        });

        return convertView;
    }

    class Viewholder {

        TextView index;

        TextView title;
    }
}
