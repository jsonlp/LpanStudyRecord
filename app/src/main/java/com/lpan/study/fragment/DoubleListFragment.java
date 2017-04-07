package com.lpan.study.fragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.lpan.study.adapter.AbstractAdapter;
import com.lpan.study.listener.OnProvinceTabClickListener;
import com.lpan.study.model.ProvinceTabInfo;
import com.lpan.study.model.SchoolInfo;
import com.test.lpanstudyrecord.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lpan on 2017/4/5.
 */

public class DoubleListFragment extends BaseFragment implements OnProvinceTabClickListener {

    private ListView mTabsView;

    private ListView mSchoolsView;

    private List<ProvinceTabInfo> mTabInfos;

    private List<SchoolInfo> mSchools;

    private TabsAdapter mTabsAdapter;

    private SchoolsAdapter mSchoolsAdapter;

    private int mScrollStatus = -1;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_double_list;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mTabsView = (ListView) view.findViewById(R.id.listview_tabs);
        mSchoolsView = (ListView) view.findViewById(R.id.listview);
        mSchoolsView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                mScrollStatus = scrollState;
                Log.d("lp-test","mSchoolsView   onScrollStateChanged");

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.d("lp-test","mSchoolsView   onScroll");

                if (mTabsAdapter == null || mTabsView == null) {
                    return;
                }

                if (mScrollStatus == SCROLL_STATE_IDLE) {
                    return;
                }
                if (firstVisibleItem < 20) { //20
                    scrollTo(0);

                } else if (firstVisibleItem < 40) {//40
                    scrollTo(1);

                } else if (firstVisibleItem < 60) {//60
                    scrollTo(2);

                } else if (firstVisibleItem < 80) {//80
                    scrollTo(3);

                } else if (firstVisibleItem < 100) {//100

                    scrollTo(4);
                } else if (firstVisibleItem < 120) {//120
                    scrollTo(5);

                } else if (firstVisibleItem < 140) {//140
                    scrollTo(6);

                } else if (firstVisibleItem < 160) {//160
                    scrollTo(7);

                } else if (firstVisibleItem < 180) {//180
                    scrollTo(8);

                } else if (firstVisibleItem < 200) {
                    scrollTo(9);

                } else if (firstVisibleItem < 220) { //20
                    scrollTo(10);

                } else if (firstVisibleItem < 240) {//40
                    scrollTo(11);

                } else if (firstVisibleItem < 260) {//60
                    scrollTo(12);

                } else if (firstVisibleItem < 280) {//80
                    scrollTo(13);

                } else if (firstVisibleItem < 300) {//100
                    scrollTo(14);

                } else if (firstVisibleItem < 320) {//120
                    scrollTo(15);

                } else if (firstVisibleItem < 340) {//140
                    scrollTo(16);

                } else if (firstVisibleItem < 360) {//160
                    scrollTo(17);

                } else if (firstVisibleItem < 380) {//180
                    scrollTo(18);

                } else if (firstVisibleItem < 400) {
                    scrollTo(19);

                }
            }
        });

        mTabsView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.d("lp-test","mTabsView   onScrollStateChanged");
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.d("lp-test","mTabsView   onScroll");

            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mTabInfos = new ArrayList<>();
        mSchools = new ArrayList<>();

        for (int i = 0; i < 400; i++) {
            if (i < 20) {
                mSchools.add(new SchoolInfo("北京大学" + i, "北京"));
            } else if (i < 40) {
                mSchools.add(new SchoolInfo("上海交通大学" + i, "上海"));
            } else if (i < 60) {
                mSchools.add(new SchoolInfo("浙江大学" + i, "浙江"));
            } else if (i < 80) {
                mSchools.add(new SchoolInfo("安徽大学" + i, "安徽"));
            } else if (i < 100) {
                mSchools.add(new SchoolInfo("山东大学" + i, "山东"));
            } else if (i < 120) {
                mSchools.add(new SchoolInfo("吉林大学" + i, "吉林"));
            } else if (i < 140) {
                mSchools.add(new SchoolInfo("河南大学" + i, "河南"));
            } else if (i < 160) {
                mSchools.add(new SchoolInfo("河北大学" + i, "河北"));
            } else if (i < 180) {
                mSchools.add(new SchoolInfo("天津大学" + i, "天津"));
            } else if (i < 200) {
                mSchools.add(new SchoolInfo("重庆大学" + i, "重庆"));
            }
//
            else if (i < 220) {
                mSchools.add(new SchoolInfo("江苏大学" + i, "江苏"));
            } else if (i < 240) {
                mSchools.add(new SchoolInfo("福建大学" + i, "福建"));
            } else if (i < 260) {
                mSchools.add(new SchoolInfo("江西大学" + i, "江西"));
            } else if (i < 280) {
                mSchools.add(new SchoolInfo("青海大学" + i, "青海"));
            } else if (i < 300) {
                mSchools.add(new SchoolInfo("湖北大学" + i, "湖北"));
            } else if (i < 320) {
                mSchools.add(new SchoolInfo("湖南大学" + i, "湖南"));
            } else if (i < 340) {
                mSchools.add(new SchoolInfo("广东大学" + i, "广东"));
            } else if (i < 360) {
                mSchools.add(new SchoolInfo("海南大学" + i, "海南"));
            } else if (i < 380) {
                mSchools.add(new SchoolInfo("四川大学" + i, "四川"));
            } else {
                mSchools.add(new SchoolInfo("贵州大学" + i, "贵州"));
            }
        }

        mTabInfos.add(new ProvinceTabInfo("北京", 20, 0));
        mTabInfos.add(new ProvinceTabInfo("上海", 20, 20));
        mTabInfos.add(new ProvinceTabInfo("浙江", 20, 40));
        mTabInfos.add(new ProvinceTabInfo("安徽", 20, 60));
        mTabInfos.add(new ProvinceTabInfo("山东", 20, 80));
        mTabInfos.add(new ProvinceTabInfo("吉林", 20, 100));
        mTabInfos.add(new ProvinceTabInfo("河南", 20, 120));
        mTabInfos.add(new ProvinceTabInfo("河北", 20, 140));
        mTabInfos.add(new ProvinceTabInfo("天津", 20, 160));
        mTabInfos.add(new ProvinceTabInfo("重庆", 20, 180));

        mTabInfos.add(new ProvinceTabInfo("江苏", 20, 200));
        mTabInfos.add(new ProvinceTabInfo("福建", 20, 220));
        mTabInfos.add(new ProvinceTabInfo("江西", 20, 240));
        mTabInfos.add(new ProvinceTabInfo("青海", 20, 260));
        mTabInfos.add(new ProvinceTabInfo("湖北", 20, 280));
        mTabInfos.add(new ProvinceTabInfo("湖南", 20, 300));
        mTabInfos.add(new ProvinceTabInfo("广东", 20, 320));
        mTabInfos.add(new ProvinceTabInfo("海南", 20, 340));
        mTabInfos.add(new ProvinceTabInfo("四川", 20, 360));
        mTabInfos.add(new ProvinceTabInfo("贵州", 20, 380));

        mTabsAdapter = new TabsAdapter(getActivity(), this);
        mTabsAdapter.addItem(mTabInfos);
        mTabsView.setAdapter(mTabsAdapter);
        mTabsAdapter.selectProvinceTab(0);

        mSchoolsAdapter = new SchoolsAdapter(getActivity());
        mSchoolsAdapter.addItem(mSchools);
        mSchoolsView.setAdapter(mSchoolsAdapter);

    }

    @Override
    public void onProvinceClick(int position, ProvinceTabInfo provinceTabInfo, View divider) {
        mTabsAdapter.cancelSelected();
        mTabsAdapter.selectProvinceTab(position);
        mSchoolsView.setSelection(provinceTabInfo.getFirstIndex());
        divider.setBackgroundResource(R.color.white);
        provinceTabInfo.setSelected(true);

    }


    private void scrollTo(int position) {
        mTabsAdapter.selectProvinceTab(position);
        mTabsAdapter.cancelSelected();
        mTabsAdapter.getItem(position).setSelected(true);
        if (getViewByPosition(position, mTabsView) != null) {
            getViewByPosition(position, mTabsView).setBackgroundResource(R.color.white);

        }
        mTabsView.setSelection(position);
    }

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }


    class TabsAdapter extends AbstractAdapter<ProvinceTabInfo> {

        private OnProvinceTabClickListener mListener;

        private Map<Integer, View> mViewMap = new HashMap<Integer, View>();

        public TabsAdapter(Context context, OnProvinceTabClickListener listener) {
            this.mContext = context;
            this.mListener = listener;
            mList = new ArrayList<>();
        }

        @Override
        public void clearItem() {

        }

        @Override
        public void addItem(ProvinceTabInfo provinceTabInfo) {

        }

        @Override
        public ProvinceTabInfo getItem(int position) {
            return mList.get(position);
        }

        @Override
        public void addItem(List<ProvinceTabInfo> list) {
            mList.addAll(list);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder1 holder1 = null;
            if (convertView == null) {
                holder1 = new ViewHolder1();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_tabs, null);
                holder1.mTextView = (TextView) convertView.findViewById(R.id.text1);
                convertView.setTag(holder1);

            } else {
                holder1 = (ViewHolder1) convertView.getTag();
            }

            mViewMap.put(position, convertView);
            final ProvinceTabInfo provinceTabInfo = mList.get(position);
            if (provinceTabInfo.isSelected()) {
                convertView.setBackgroundResource(R.color.white);
            } else {
                convertView.setBackgroundResource(R.color.gray);
            }
            holder1.mTextView.setText(provinceTabInfo.getProvinceName());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onProvinceClick(position, provinceTabInfo, v);
                    }
                }
            });
            return convertView;

        }


        public void selectProvinceTab(int position) {
            for (Map.Entry<Integer, View> entry : mViewMap.entrySet()) {
                if (entry.getKey() == position) {
                    entry.getValue().setBackgroundResource(R.color.white);
                } else {
                    entry.getValue().setBackgroundResource(R.color.gray);
                }
            }
        }

        public void cancelSelected() {
            for (ProvinceTabInfo provinceTabInfo : mList) {
                provinceTabInfo.setSelected(false);
            }
        }
    }

    static class ViewHolder1 {
        TextView mTextView;
    }


    class SchoolsAdapter extends AbstractAdapter<SchoolInfo> {
        public SchoolsAdapter(Context context) {
            this.mContext = context;
            mList = new ArrayList<>();

        }

        @Override
        public void clearItem() {

        }

        @Override
        public void addItem(SchoolInfo schoolInfo) {

        }

        @Override
        public void addItem(List<SchoolInfo> list) {
            mList.addAll(list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder2 holder2 = null;
            if (convertView == null) {
                holder2 = new ViewHolder2();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_schools, null);
                holder2.mTextView = (TextView) convertView.findViewById(R.id.text1);
                convertView.setTag(holder2);

            } else {
                holder2 = (ViewHolder2) convertView.getTag();
            }
            holder2.mTextView.setText(mList.get(position).getName());
            return convertView;
        }
    }

    static class ViewHolder2 {
        TextView mTextView;
    }
}
