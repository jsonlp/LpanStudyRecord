package com.lpan.study.fragment;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.lpan.study.adapter.SlideCardAdapter;
import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.model.CardDataItem;
import com.lpan.study.view.slidepanel.CardSlidePanel;
import com.test.lpanstudyrecord.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lpan on 2017/9/6.
 */

public class SlidePanelFragment extends BaseFragment implements View.OnClickListener {

    private CardSlidePanel mCardSlidePanel;

    private List<Integer> mImageList = new ArrayList<>();

    private static final int CARD_COUNT = 20;

    private SlideCardAdapter mAdapter;

    private ImageView mDontLike, mLike;

    private Integer imagePaths[] = {
            R.drawable.wall01, R.drawable.wall02,
            R.drawable.wall03, R.drawable.wall04,
            R.drawable.wall05, R.drawable.wall06,
            R.drawable.wall07, R.drawable.wall08,
            R.drawable.wall09, R.drawable.wall10}; // 20个图片资源


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_slide_panel;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mDontLike = (ImageView) view.findViewById(R.id.dont_like);
        mLike = (ImageView) view.findViewById(R.id.like);

        mCardSlidePanel = (CardSlidePanel) view.findViewById(R.id.card_slide_panel);
        mCardSlidePanel.setCardSwitchListener(new CardSlidePanel.CardSwitchListener() {
            @Override
            public void onShow(int index) {
                Log.d("Card", "正在显示-" + getAdapter().getList().get(index).userName);

            }

            @Override
            public void onCardVanish(int index, int type) {
                Log.d("Card", "正在消失-" + getAdapter().getList().get(index).userName + " 消失type=" + type);

                if (index == getAdapter().getList().size() - 1) {
                    toastShort("无更多数据,请稍后");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getData(20);
                            mCardSlidePanel.getAdapter().notifyDataSetChanged();
                        }
                    }, 1000);
                }

            }
        });
        mDontLike.setOnClickListener(this);
        mLike.setOnClickListener(this);

    }

    private void getData(int count) {
        int x = count / 10 + 1;
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, imagePaths);
        for (int i = 0; i < x; i++) {
            mImageList.addAll(list);
        }

        for (int i = 0; i < count; i++) {
            CardDataItem cardDataItem = new CardDataItem(mImageList.get(i), "jason" + i, i, i);
            getAdapter().addItem(cardDataItem);
        }
    }

    @Override
    protected void initData() {
        super.initData();
//        getData(500);
        mCardSlidePanel.setAdapter(getAdapter());

        getData(1);
        getAdapter().notifyDataSetChanged();

    }

    public SlideCardAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new SlideCardAdapter(getActivity(), R.layout.card_item);
        }
        return mAdapter;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.dont_like:
                if (mCardSlidePanel != null) {
                    mCardSlidePanel.vanishOnBtnClick(CardSlidePanel.VANISH_TYPE_LEFT);
                }
                break;

            case R.id.like:
                if (mCardSlidePanel != null) {
                    mCardSlidePanel.vanishOnBtnClick(CardSlidePanel.VANISH_TYPE_RIGHT);
                }
                break;
        }
    }
}
