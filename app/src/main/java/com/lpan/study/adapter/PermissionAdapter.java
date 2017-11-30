package com.lpan.study.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lpan.R;
import com.lpan.study.model.ContactInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liaopan on 2017/11/30 10:24.
 */

public class PermissionAdapter extends RecyclerViewAdapter<ContactInfo, RecyclerView.ViewHolder> {

    public PermissionAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_normal_list, parent, false);
        return new PermissionViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PermissionViewHolder permissionViewHolder = (PermissionViewHolder) holder;
        permissionViewHolder.bindView(getItem(position));
    }

    class PermissionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text1)
        TextView name;

        @BindView(R.id.text2)
        TextView number;

        public PermissionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(ContactInfo contactInfo) {
            name.setText(contactInfo.getName());
            number.setText(contactInfo.getNumber());
        }
    }
}
