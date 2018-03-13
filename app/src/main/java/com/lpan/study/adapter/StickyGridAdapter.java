package com.lpan.study.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codewaves.stickyheadergrid.StickyHeaderGridAdapter;
import com.lpan.R;
import com.lpan.study.model.PostInfo;
import com.lpan.study.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpan on 2018/3/13.
 */

public class StickyGridAdapter extends StickyHeaderGridAdapter {

    private List<List<PostInfo>> mList;

    private Context mContext;

    public StickyGridAdapter(Context context) {
        mList = new ArrayList<>();
        mContext = context;
    }

    public void addItem(List<List<PostInfo>> list) {
        mList.addAll(list);
    }

    @Override
    public int getSectionCount() {
        return mList.size();
    }

    @Override
    public int getSectionItemCount(int section) {
        return mList.get(section).size();
    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int headerType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sticky_grid_header, parent, false);
        return new MyHeaderViewHolder(view);
    }

    @Override
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int itemType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sticky_grid, parent, false);
        return new MyItemViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderViewHolder viewHolder, int section) {
        final MyHeaderViewHolder holder = (MyHeaderViewHolder) viewHolder;
        final String label = "Header " + section;
        holder.labelView.setText(label);
    }

    @Override
    public void onBindItemViewHolder(ItemViewHolder viewHolder, final int section, final int position) {
        final MyItemViewHolder holder = (MyItemViewHolder) viewHolder;
        holder.cover.getLayoutParams().width = ViewUtils.getScreenWidth(mContext) / 3;
        holder.cover.getLayoutParams().height = ViewUtils.getScreenWidth(mContext) / 3;
        final String label = mList.get(section).get(position).getBookInfo().getName();
        holder.labelView.setText(label);
        Glide.with(mContext)
                .load(mList.get(section).get(position).getBookInfo().getCover())
                .into(holder.cover);

        holder.labelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int section = getAdapterPositionSection(holder.getAdapterPosition());
                final int offset = getItemSectionOffset(section, holder.getAdapterPosition());

                mList.get(section).remove(offset);
                notifySectionItemRemoved(section, offset);
                Toast.makeText(holder.labelView.getContext(), label, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static class MyHeaderViewHolder extends HeaderViewHolder {
        TextView labelView;

        MyHeaderViewHolder(View itemView) {
            super(itemView);
            labelView = (TextView) itemView.findViewById(R.id.label);
        }
    }

    public static class MyItemViewHolder extends ItemViewHolder {
        TextView labelView;
        ImageView cover;

        MyItemViewHolder(View itemView) {
            super(itemView);
            cover = (ImageView) itemView.findViewById(R.id.image1);
            labelView = (TextView) itemView.findViewById(R.id.label);
        }
    }


}
