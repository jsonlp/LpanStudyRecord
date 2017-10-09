package com.lpan.study.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.view.dragsort.DragSortListView;
import com.test.lpanstudyrecord.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpan on 2017/3/20.
 */

public class DragListFragment extends BaseFragment {

    private DragSortListView mListView;

    private DragAdapter mAdapter;

    private List<String> mList = new ArrayList<>();


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_drag_list;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);

        mListView = (DragSortListView) view.findViewById(R.id.dslvList);
        mListView.setDragEnabled(true);

        mList.add("111");
        mList.add("222");
        mList.add("333");
        mList.add("444");
        mList.add("555");
        mList.add("666");
        mList.add("777");

        mAdapter = new DragAdapter(getActivity(), mList);
        mListView.setAdapter(mAdapter);

    }


    class DragAdapter extends BaseAdapter {

        private Context context;
        List<String> items;//适配器的数据源


        public DragAdapter(Context context, List<String> list) {
            this.context = context;
            this.items = list;
        }


        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int arg0) {
            return items.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        public void remove(int arg0) {//删除指定位置的item
            items.remove(arg0);
            this.notifyDataSetChanged();//不要忘记更改适配器对象的数据源
        }

        public void insert(String item, int arg0) {//在指定位置插入item
            items.add(arg0, item);
            this.notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            String item = (String) getItem(position);
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_drag, null);
                viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.text1);
                viewHolder.ivDragHandle = (ImageView) convertView.findViewById(R.id.drag);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.tvTitle.setText(item);
            viewHolder.ivDragHandle.setImageResource(R.drawable.ic_launcher);
            return convertView;
        }

        class ViewHolder {
            TextView tvTitle;
            ImageView ivDragHandle;
        }
    }


}
