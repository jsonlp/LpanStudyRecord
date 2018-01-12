package com.lpan.study.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lpan.R;
import com.lpan.study.listener.OnAvatarClickListener;
import com.lpan.study.model.UserInfo;
import com.lpan.study.utils.ViewUtils;
import com.lpan.study.view.CircleImageView;

/**
 * Created by liaopan on 2018/1/11 17:23.
 */

public class ClassroomAdapter extends RecyclerViewAdapter<UserInfo, RecyclerView.ViewHolder> {

    private OnAvatarClickListener mOnAvatarClickListener;

    public ClassroomAdapter(Context context, OnAvatarClickListener listener) {
        super(context);
        mOnAvatarClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_image_and_text, parent, false);
        return new ClassroomViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ClassroomViewHolder classroomViewHolder = (ClassroomViewHolder) holder;
        classroomViewHolder.bindView(getItem(position), position, mOnAvatarClickListener);
    }

    public float[] getTag1Position() {
        float[] floats = new float[]{};


        return floats;
    }


    class ClassroomViewHolder extends RecyclerView.ViewHolder {

        CircleImageView mImageView;

        View mView;
//        TextView mTextView;


        public ClassroomViewHolder(View itemView) {
            super(itemView);
            mImageView = (CircleImageView) itemView.findViewById(R.id.image1);
            mView = itemView.findViewById(R.id.wrapper);
//            mTextView = (TextView) itemView.findViewById(R.id.text1);
        }

        public void bindView(final UserInfo userInfo, final int position, final OnAvatarClickListener onAvatarClickListener) {
//            mTextView.setText(userInfo.getName());

            int ONE_DP = (int) ViewUtils.ONE_DP;
            int left = 0;
            int top = 0;
            int right = 0;
            int bottom = 0;
            mImageView.setImageResource(R.drawable.inspire);
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onAvatarClickListener != null) {
                        onAvatarClickListener.OnAvatarClick(userInfo,v,position);
                    }
                }
            });
            if (position % 10 == 0 || position % 10 == 9) {
                top = 0;
            } else if (position % 10 == 1 || position % 10 == 8) {
                top = ONE_DP * 5;
            } else if (position % 10 == 2 || position % 10 == 7) {
                top = ONE_DP * 10;
            } else if (position % 10 == 3 || position % 10 == 6) {
                top = ONE_DP * 15;
            } else {
                top = ONE_DP * 20;
            }


            if (position / 10 == 0) {
                bottom = ONE_DP * 20;
            } else if (position / 10 == 1) {
                bottom = ONE_DP * 1;
            } else if (position / 10 == 2) {
                bottom = ONE_DP * 20;
            } else if (position / 10 == 3) {
                bottom = ONE_DP * 1;
            }

            mView.setPadding(left, top, right, bottom);

            if(position == 5){

            }
        }
    }
}
