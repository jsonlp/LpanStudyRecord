package com.lpan.study.fragment;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.lpan.study.adapter.AbstractAdapter;
import com.test.lpanstudyrecord.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpan on 2017/5/2.
 */

public class VisibilityUtilsFrament extends BaseFragment {
    public static final String TAG = VisibilityUtilsFrament.class.getSimpleName();

    private static final boolean SHOW_LOGS = true;

    private ListView mListView;

    private VisibilityAdapter mAdapter;

    private final Rect mCurrentViewRect = new Rect();

    private int mScrollState = AbsListView.OnScrollListener.SCROLL_STATE_IDLE;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_visibility_utils;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mListView = (ListView) view.findViewById(R.id.listview);
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (mScrollState == SCROLL_STATE_IDLE) {
                    int childCount = view.getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        View childAt = mListView.getChildAt(i);
                        int visibilityPercents = getVisibilityPercents(childAt);

                        if (childAt.getTag() instanceof ViewHolder) {
                            ViewHolder viewholder = (ViewHolder) childAt.getTag();

                            int imagePercent = getVisibilityPercents(viewholder.mImage);
                            viewholder.mItemPercent.setText("item percent: " + visibilityPercents + " %");
                            viewholder.mImagePercent.setText("red percent: " + imagePercent + " %");

                        }
                        Log.d(TAG, "onScrollStateChanged: childCount=" + childCount + "   i =" + i + "    visibilityPercents=" + visibilityPercents);
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("第" + i + "个");
        }
        getAdapter().addItem(list);
        mListView.setAdapter(getAdapter());
    }

    public int getVisibilityPercents(View currentView) {
        if (currentView == null) {
            return 0;
        }
        int percents = 100;

        currentView.getLocalVisibleRect(mCurrentViewRect);

        int height = currentView.getHeight();

        if (viewIsPartiallyHiddenTop()) {
            // view is partially hidden behind the top edge
            percents = (height - mCurrentViewRect.top) * 100 / height;
        } else if (viewIsPartiallyHiddenBottom(height)) {
            percents = mCurrentViewRect.bottom * 100 / height;
        }


        return percents;
    }

    private boolean viewIsPartiallyHiddenBottom(int height) {
        return mCurrentViewRect.bottom > 0 && mCurrentViewRect.bottom < height;
    }

    private boolean viewIsPartiallyHiddenTop() {
        return mCurrentViewRect.top > 0;
    }


    private VisibilityAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new VisibilityAdapter(getActivity());
        }
        return mAdapter;
    }


    class VisibilityAdapter extends AbstractAdapter<String> {

        private Context mContext;

        public VisibilityAdapter(Context context) {
            mContext = context;
            mList = new ArrayList<>();
        }

        @Override
        public void clearItem() {
            mList.clear();
        }

        @Override
        public void addItem(String s) {
            mList.add(s);
        }

        @Override
        public void addItem(List<String> list) {
            mList.addAll(list);
        }

        @Override
        public int getCount() {
            return super.getCount();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewholder = null;
            if (convertView == null) {
                viewholder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_visibility_utils, parent, false);
                viewholder.mTitle = (TextView) convertView.findViewById(R.id.text1);
                viewholder.mItemPercent = (TextView) convertView.findViewById(R.id.text2);
                viewholder.mImagePercent = (TextView) convertView.findViewById(R.id.text3);

                viewholder.mImage = convertView.findViewById(R.id.image1);
                convertView.setTag(viewholder);
            } else {
                viewholder = (ViewHolder) convertView.getTag();
            }

            viewholder.mTitle.setText(mList.get(position));

            return convertView;
        }

    }

    class ViewHolder {

        TextView mTitle;

        TextView mItemPercent;

        TextView mImagePercent;

        View mImage;
    }
}
