//package com.lpan.study.adapter;
//
//import android.content.Context;
//
//
//import com.lpan.study.utils.CollectionUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by haizhu on 15/5/6.
// */
//public abstract class RecyclerViewAdapter<T, V extends ViewHolder> extends Adapter<V> {
//
//    protected final Context mContext;
//
//    protected final ArrayList<T> mList;
//
//    public RecyclerViewAdapter(Context context) {
//
//        this.mContext = context;
//        this.mList = new ArrayList<T>();
//    }
//
//    public T getItem(int position) {
//        if(CollectionUtils.isEmpty(mList)){
//            return null;
//        }else if(position >= mList.size()){
//            return null;
//        } else if (position < 0) {
//            return null;
//        } else {
//            return mList.get(position);
//        }
//    }
//
//
//    public Object AddFriendgetItem(int position) {
//        return mList.get(position);
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return CollectionUtils.isEmpty(mList) ? 0 : mList.size();
//    }
//
//    public void addItem(List<T> items) {
//        mList.addAll(items);
//    }
//
//    public void addItem(int index,List<T> items) {
//        mList.addAll(index,items);
//    }
//
//    public void addItem(T item, int position){
//        mList.add(position, item);
//    }
//
//    public void addItem(T item){
//        mList.add(item);
//    }
//
//    public void removeItem(int position){
//        if(CollectionUtils.isEmpty(mList)){
//            return;
//        }
//        mList.remove(position);
//    }
//
//    public void removeItem(T item) {
//        if(CollectionUtils.isEmpty(mList)) {
//            return;
//        }
//        mList.remove(item);
//    }
//
//    public List<T> getList(){
//        return mList;
//    }
//
//    public void clearItem() {
//        mList.clear();
//
//    }
//}
