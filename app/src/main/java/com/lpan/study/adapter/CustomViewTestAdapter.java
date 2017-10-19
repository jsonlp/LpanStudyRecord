package com.lpan.study.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lpan.study.model.RecyclerItemInfo;
import com.lpan.study.utils.FragmentUtils;
import com.test.lpanstudyrecord.R;

/**
 * Created by lpan on 2017/10/18.
 */

public class CustomViewTestAdapter extends RecyclerViewAdapter<RecyclerItemInfo, RecyclerView.ViewHolder> {

    public CustomViewTestAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_holder_list, parent, false);
        return new NormalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
        normalViewHolder.bindView(mList.get(position),position);
    }


    class NormalViewHolder extends RecyclerView.ViewHolder {
        TextView index;

        TextView title;

        public NormalViewHolder(View itemView) {
            super(itemView);
            index = (TextView) itemView.findViewById(R.id.index);
            title = (TextView) itemView.findViewById(R.id.title);
        }

        public void bindView(final RecyclerItemInfo recyclerItemInfo, int position) {
            index.setText((position + 1) + "");
            title.setText(recyclerItemInfo.getTitle());

            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentUtils.navigateToInNewActivity(mContext, recyclerItemInfo.getFragment(), null);
                }
            });
        }
    }
}
