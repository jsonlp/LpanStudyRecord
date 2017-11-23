package com.lpan.study.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lpan.study.listener.OnRowAdapterClickListener;
import com.lpan.study.model.ImageInfo;
import com.lpan.study.utils.BitmapUtils;
import com.lpan.study.utils.FileUtils;
import com.lpan.study.utils.ViewUtils;
import com.lpan.R;
import com.lpan.study.view.BaseImageView;

/**
 * Created by lpan on 2017/9/1.
 */

public class GalleryAdapter extends RecyclerViewAdapter<ImageInfo, RecyclerView.ViewHolder> {

    private OnRowAdapterClickListener<ImageInfo> mListener;

    public GalleryAdapter(Context context, OnRowAdapterClickListener<ImageInfo> listener) {
        super(context);
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_gallery, parent, false);
        return new GalleryViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GalleryViewHolder galleryViewHolder = (GalleryViewHolder) holder;
        galleryViewHolder.bindView(position, mList.get(position), mListener);
    }

    class GalleryViewHolder extends RecyclerView.ViewHolder {

        private BaseImageView mImageView;


        public GalleryViewHolder(View itemView) {
            super(itemView);
            mImageView = (BaseImageView) itemView.findViewById(R.id.image1);
            mImageView.getLayoutParams().width = ViewUtils.getScreenWidth(mContext) / 3;
            mImageView.getLayoutParams().height = ViewUtils.getScreenWidth(mContext) / 3;
        }

        public void bindView(final int position, final ImageInfo imageInfo, final OnRowAdapterClickListener<ImageInfo> listener) {
            if (imageInfo == null) {
                return;
            }
            mImageView.setUrl(mContext,imageInfo.getUrl());

            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(v, imageInfo, position);
                    }
                }
            });
        }
    }
}
