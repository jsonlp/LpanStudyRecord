package com.lpan.study.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lpan.R;
import com.lpan.study.model.BlockInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpan on 2018/10/9.
 */
public class DoubleRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<BlockInfo> mList = new ArrayList<>();
    public Context mContext;

    public DoubleRecyclerAdapter(Context context) {
        mContext = context;
    }

    public List<BlockInfo> getList() {
        return mList;
    }

    public void setList(List<BlockInfo> list) {
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_normal_list, null);
        NormalViewHolder normalViewHolder = new NormalViewHolder(view);
        switch (viewType) {
            case 1:
                return normalViewHolder;
            case 2:
                return normalViewHolder;
            case 3:
                return normalViewHolder;
            case 4:
                return normalViewHolder;
            case 5:
                View view2 = LayoutInflater.from(mContext).inflate(R.layout.item_inner_recycler, null);
                return new InnerRecyclerViewHolder(view2);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:
                InnerRecyclerViewHolder innerRecyclerViewHolder = (InnerRecyclerViewHolder) holder;
                innerRecyclerViewHolder.bindInner(mList.get(position),position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        BlockInfo blockInfo = mList.get(position);
        return blockInfo.getType();
    }

    class NormalViewHolder extends RecyclerView.ViewHolder {

        public NormalViewHolder(View itemView) {
            super(itemView);
        }
    }

    class InnerRecyclerViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView mRecyclerView;
        InnerRecyclerAdapter mInnerRecyclerAdapter;

        public InnerRecyclerViewHolder(View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.inner_recycler_view);

            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
            mInnerRecyclerAdapter = new InnerRecyclerAdapter(mContext);
            mRecyclerView.setAdapter(mInnerRecyclerAdapter);
        }

        public void bindInner(BlockInfo blockInfo,int position){
            mInnerRecyclerAdapter.setList(blockInfo.getContent());
            mInnerRecyclerAdapter.notifyDataSetChanged();
        }
    }
}
