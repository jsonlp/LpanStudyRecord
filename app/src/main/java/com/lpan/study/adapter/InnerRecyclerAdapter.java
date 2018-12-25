package com.lpan.study.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lpan.R;
import com.lpan.study.model.ContentInfo;

import java.util.List;

/**
 * Created by lpan on 2018/10/9.
 */
public class InnerRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<ContentInfo> mList;
    private Context mContext;


    public InnerRecyclerAdapter(Context context) {
        mContext = context;
    }

    public List<ContentInfo> getList() {
        return mList;
    }

    public void setList(List<ContentInfo> list) {
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_inner_recycler_item,null);
        Log.d("InnerRecyclerAdapter","onCreateViewHolder--------");
        return new NormalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        Log.d("InnerRecyclerAdapter","onBindViewHolder--------");
        NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
        TextView textView = normalViewHolder.itemView.findViewById(R.id.text);
        textView.setText(mList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    
    class NormalViewHolder extends RecyclerView.ViewHolder{

        public NormalViewHolder(View itemView) {
            super(itemView);
        }
    }
}
