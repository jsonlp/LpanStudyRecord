package com.lpan.study.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lpan.study.model.CardDataItem;
import com.test.lpanstudyrecord.R;

/**
 * Created by lpan on 2017/9/29.
 */

public class TestRecyclerAdapter extends RecyclerViewAdapter<CardDataItem, RecyclerView.ViewHolder> {

    public TestRecyclerAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_test, parent, false);
        return new NormalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
        normalViewHolder.bindView(mContext, position, getItem(position));
    }


    class NormalViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;

        private TextView mTextView;

        public NormalViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image1);
            mTextView = (TextView) itemView.findViewById(R.id.text1);

        }

        public void bindView(Context context, int position, CardDataItem cardDataItem) {
            mImageView.setImageResource(cardDataItem.imagePath);
            mTextView.setText(cardDataItem.userName);
        }
    }
}
