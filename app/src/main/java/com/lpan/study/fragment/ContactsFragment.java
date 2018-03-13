package com.lpan.study.fragment;

import android.view.View;
import android.widget.TextView;

import com.lpan.R;
import com.lpan.study.adapter.ContactsAdapter;
import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.model.UserInfo;
import com.lpan.study.view.SectionIndexerScrollerBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by lpan on 2018/3/12.
 */

public class ContactsFragment extends ButterKnifeFragment {

    @BindView(R.id.listview)
    StickyListHeadersListView mListView;

    @BindView(R.id.scroller_bar)
    SectionIndexerScrollerBar mScrollerBar;

    @BindView(R.id.guide_text)
    TextView mTextDialog;


    private ContactsAdapter mAdapter;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_contacts;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mScrollerBar.setTextView(mTextDialog);
        mScrollerBar.setOnTouchingLetterChangedListener(new SectionIndexerScrollerBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                int positionForSection = getAdapter().getPositionForSection(s.charAt(0));
                if (positionForSection != -1) {
                    mListView.setSelection(positionForSection);
                }
            }
        });

        mListView.setAdapter(getAdapter());
    }

    @Override
    protected void initData() {
        super.initData();
        List<UserInfo> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new UserInfo("张三" + i));
        }

        for (int i = 0; i < 20; i++) {
            list.add(new UserInfo("李四" + i));
        }

        for (int i = 0; i < 20; i++) {
            list.add(new UserInfo("丁一" + i));
        }

        for (int i = 0; i < 20; i++) {
            list.add(new UserInfo("赵周" + i));
        }

        for (int i = 0; i < 20; i++) {
            list.add(new UserInfo("安以轩" + i));
            list.add(new UserInfo("艾派德" + i));

        }

        for (int i = 0; i < 20; i++) {
            list.add(new UserInfo("胡歌" + i));
            list.add(new UserInfo("崔季" + i));
            list.add(new UserInfo("董小姐" + i));
            list.add(new UserInfo("高空" + i));
            list.add(new UserInfo("黄河" + i));
            list.add(new UserInfo("季藏" + i));
            list.add(new UserInfo("木子" + i));
            list.add(new UserInfo("彭砰" + i));

            list.add(new UserInfo("&$!@" + i));

        }
        Collections.sort(list);

        getAdapter().addItem(list);
        getAdapter().notifyDataSetChanged();

    }

    private ContactsAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new ContactsAdapter(getActivity());
        }
        return mAdapter;
    }
}
