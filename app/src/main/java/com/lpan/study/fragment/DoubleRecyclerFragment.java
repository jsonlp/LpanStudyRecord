package com.lpan.study.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.lpan.R;
import com.lpan.study.adapter.DoubleRecyclerAdapter;
import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.model.BlockInfo;
import com.lpan.study.model.ContentInfo;
import com.lpan.study.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;

/**
 * Created by lpan on 2018/10/9.
 */
public class DoubleRecyclerFragment extends ButterKnifeFragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private DoubleRecyclerAdapter mAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_double_recycler;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(getAdapter());
    }

    private DoubleRecyclerAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new DoubleRecyclerAdapter(getContext());
        }
        return mAdapter;
    }

    @Override
    protected void initData() {
        super.initData();
        List<BlockInfo> list = new ArrayList<>();
        List<ContentInfo> contents = new ArrayList<>();
        contents.add(new ContentInfo("类型5下item1"));
        contents.add(new ContentInfo("类型5下item2"));
        contents.add(new ContentInfo("类型5下item3"));
        contents.add(new ContentInfo("类型5下item4"));
        contents.add(new ContentInfo("类型5下item5"));
        contents.add(new ContentInfo("类型5下item6"));
        contents.add(new ContentInfo("类型5下item7"));
        contents.add(new ContentInfo("类型5下item8"));
        contents.add(new ContentInfo("类型5下item9"));
        contents.add(new ContentInfo("类型5下item10"));


        BlockInfo blockInfo1 = new BlockInfo(1, "类型1", null);
        BlockInfo blockInfo2 = new BlockInfo(2, "类型2", null);
        BlockInfo blockInfo3 = new BlockInfo(3, "类型3", null);
        BlockInfo blockInfo4 = new BlockInfo(4, "类型4", null);
        BlockInfo blockInfo5 = new BlockInfo(5, "类型5", contents);

        list.add(blockInfo1);
        list.add(blockInfo2);
        list.add(blockInfo5);
        list.add(blockInfo3);
        list.add(blockInfo4);

        filterData(list);
        splitVideoBlock(list);
        getAdapter().setList(list);
        getAdapter().notifyDataSetChanged();

    }

    private void filterData(List<BlockInfo> list) {
        Map<Integer, List<BlockInfo>> map = new HashMap<>();
        List<BlockInfo> filterList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            BlockInfo blockInfo = list.get(i);
            if (blockInfo.getType() == 5) {
//                List<BlockInfo> list1 = splitVideoBlock(blockInfo);
//                int index = list.indexOf(blockInfo);
//                map.put(index, list1);
//                filterList.add(blockInfo);
            }
        }
        Set<Map.Entry<Integer, List<BlockInfo>>> entries = map.entrySet();
        Iterator<Map.Entry<Integer, List<BlockInfo>>> iterator = entries.iterator();
        list.removeAll(filterList);
        while (iterator.hasNext()) {
            Map.Entry<Integer, List<BlockInfo>> next = iterator.next();
            int index = next.getKey();
            List<BlockInfo> value = next.getValue();
            list.addAll(index, value);
        }
        Log.d("DoubleRecyclerFragment", "filterData--------" + list.size());
    }

    protected void splitVideoBlock(List<BlockInfo> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            BlockInfo block = list.get(i);
            if (block.getType() == 5) {
                List<ContentInfo> contents = block.getContent();
                if (CollectionUtils.isEmpty(contents) || contents.size() == 1) {
                    return;
                }
                List<BlockInfo> newList = new ArrayList<>();
                for (int j = 0; j < contents.size(); j++) {
                    BlockInfo newBlock = new BlockInfo();
                    List<ContentInfo> newContents = new ArrayList<>();
                    newContents.add(contents.get(j));

                    newBlock.setType(block.getType());
                    newBlock.setTitle(block.getTitle());
                    newBlock.setContent(newContents);

                    newList.add(newBlock);
                }
                list.remove(i);
                list.addAll(newList);
                i += newList.size() - 1;
            }
        }
    }
}
