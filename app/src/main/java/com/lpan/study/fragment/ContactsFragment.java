package com.lpan.study.fragment;

import android.view.View;
import android.widget.TextView;

import com.lpan.R;
import com.lpan.study.adapter.ContactsAdapter;
import com.lpan.study.db.FriendDaoManager;
import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.model.FriendInfo;
import com.lpan.study.model.UserInfo;
import com.lpan.study.utils.Log;
import com.lpan.study.view.SectionIndexerScrollerBar;

import java.io.IOException;
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

    private ContactsAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new ContactsAdapter(getActivity());
        }
        return mAdapter;
    }


    @Override
    protected void initData() {
        super.initData();
        if (FriendDaoManager.getFriendCount() > 0) {
            if (Log.DEBUG) {
                Log.d("ContactsFragment", "initData--------db exit");
            }
            List<FriendInfo> friends = getFriends();
            Collections.sort(friends);
            getAdapter().addItem(friends);
            getAdapter().notifyDataSetChanged();
        } else {
            if (Log.DEBUG) {
                Log.d("ContactsFragment", "initData--------first in , no db");
            }
            saveFriend();
        }
    }

    private void saveFriend() {
        int count = 20;
        List<FriendInfo> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            FriendInfo friendInfo = new FriendInfo();
            friendInfo.setRelation(FriendInfo.TYPE_FRIEND);
            friendInfo.setUserInfo(new UserInfo("张三" + i,"id_is_a"+i,"男"));
            list.add(friendInfo);
        }

        for (int i = 0; i < count; i++) {
            FriendInfo friendInfo = new FriendInfo();
            friendInfo.setRelation(FriendInfo.TYPE_FRIEND);
            friendInfo.setUserInfo(new UserInfo("李四" + i,"id_is_b"+i,"男"));
            list.add(friendInfo);
        }

        for (int i = 0; i < count; i++) {

            FriendInfo friendInfo = new FriendInfo();
            friendInfo.setRelation(FriendInfo.TYPE_FRIEND);
            friendInfo.setUserInfo(new UserInfo("丁一" + i,"id_is_c"+i,"女"));
            list.add(friendInfo);
        }

        for (int i = 0; i < count; i++) {
            FriendInfo friendInfo = new FriendInfo();
            friendInfo.setRelation(FriendInfo.TYPE_FRIEND);
            friendInfo.setUserInfo(new UserInfo("赵周" + i,"id_is_d"+i,"男"));
            list.add(friendInfo);
        }

        for (int i = 0; i < count; i++) {
            FriendInfo friendInfo = new FriendInfo();
            friendInfo.setRelation(FriendInfo.TYPE_FRIEND);
            friendInfo.setUserInfo(new UserInfo("安以轩" + i,"id_is_e"+i,"女"));
            list.add(friendInfo);
        }

        for (int i = 0; i < count; i++) {
            FriendInfo friendInfo = new FriendInfo();
            friendInfo.setRelation(FriendInfo.TYPE_FRIEND);
            friendInfo.setUserInfo(new UserInfo("艾派德" + i,"id_is_f"+i,"男"));
            list.add(friendInfo);
        }

        for (int i = 0; i < count; i++) {
            FriendInfo friendInfo = new FriendInfo();
            friendInfo.setRelation(FriendInfo.TYPE_FRIEND);
            friendInfo.setUserInfo(new UserInfo("胡歌" + i,"id_is_g"+i,"男"));
            list.add(friendInfo);
        }

        for (int i = 0; i < count; i++) {
            FriendInfo friendInfo = new FriendInfo();
            friendInfo.setRelation(FriendInfo.TYPE_FRIEND);
            friendInfo.setUserInfo(new UserInfo("崔季" + i,"id_is_h"+i,"女"));
            list.add(friendInfo);
        }

        for (int i = 0; i < count; i++) {
            FriendInfo friendInfo = new FriendInfo();
            friendInfo.setRelation(FriendInfo.TYPE_FRIEND);
            friendInfo.setUserInfo(new UserInfo("董小姐" + i,"id_is_i"+i,"女"));
            list.add(friendInfo);
        }

        for (int i = 0; i < count; i++) {
            FriendInfo friendInfo = new FriendInfo();
            friendInfo.setRelation(FriendInfo.TYPE_FRIEND);
            friendInfo.setUserInfo(new UserInfo("高空" + i,"id_is_j"+i,"男"));
            list.add(friendInfo);
        }

//        for (int i = 0; i < count; i++) {
//            FriendInfo friendInfo = new FriendInfo();
//            friendInfo.setRelation(FriendInfo.TYPE_FRIEND);
//            friendInfo.setUserInfo(new UserInfo("季藏" + i));
//            list.add(friendInfo);
//        }
//
//        for (int i = 0; i < count; i++) {
//            FriendInfo friendInfo = new FriendInfo();
//            friendInfo.setRelation(FriendInfo.TYPE_FRIEND);
//            friendInfo.setUserInfo(new UserInfo("彭砰" + i));
//            list.add(friendInfo);
//        }
        try {
            FriendDaoManager.saveFriends(list);
            toastShort("写入数据库成功,请返回重新进入次页面");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<FriendInfo> getFriends() {
        try {
            return FriendDaoManager.getAllFriend();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
