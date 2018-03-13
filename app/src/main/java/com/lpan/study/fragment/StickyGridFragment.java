package com.lpan.study.fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.codewaves.stickyheadergrid.StickyHeaderGridLayoutManager;
import com.lpan.R;
import com.lpan.study.adapter.StickyGridAdapter;
import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.model.BookInfo;
import com.lpan.study.model.PostInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lpan on 2018/3/13.
 */

public class StickyGridFragment extends ButterKnifeFragment {

    private static final int SPAN_SIZE = 3;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private StickyGridAdapter mAdapter;

    private StickyHeaderGridLayoutManager mLayoutManager;

    private List<List<PostInfo>> mList = new ArrayList<>();

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_sticky_grid;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mLayoutManager = new StickyHeaderGridLayoutManager(SPAN_SIZE);
        mLayoutManager.setHeaderBottomOverlapMargin(getResources().getDimensionPixelSize(R.dimen.header_shadow_size));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator() {
            @Override
            public boolean animateRemove(RecyclerView.ViewHolder holder) {
                dispatchRemoveFinished(holder);
                return false;
            }
        });
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(getAdapter());
    }

    @Override
    protected void initData() {
        super.initData();
        mList.add(createPosts(5, "2018年3月12日", "老人与海"));
        mList.add(createPosts(1, "2018年3月11日", "捕蛇者说"));
        mList.add(createPosts(10, "2018年3月10日", "三国演义"));
        mList.add(createPosts(7, "2018年3月9日", "西游记"));
        mList.add(createPosts(22, "2018年3月8日", "红楼梦"));
        mList.add(createPosts(3, "2018年3月8日", "水浒传"));

        getAdapter().addItem(mList);
        getAdapter().notifyAllSectionsDataSetChanged();

    }

    private List<PostInfo> createPosts(int count, String createTime, String bookName) {
        List<PostInfo> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            PostInfo postInfo = new PostInfo();
            postInfo.setCreateTime(createTime);
            BookInfo bookInfo = new BookInfo();
            bookInfo.setName(bookName + i);
            bookInfo.setCover("http://p-test.jiemosrc.com/qVHYYSNRiZbo7csz1RiiBg.jpeg?x-oss-process=image/resize,m_fill,h_160,w_160/quality,q_90/format,webp");
            postInfo.setBookInfo(bookInfo);
            list.add(postInfo);
        }
        return list;
    }

    private StickyGridAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new StickyGridAdapter(getActivity());
        }
        return mAdapter;
    }
}
