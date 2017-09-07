package com.lpan.study.fragment;

import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lpan.study.model.CardDataItem;
import com.lpan.study.utils.CollectionUtils;
import com.lpan.study.view.slidepanel.CardAdapter;
import com.lpan.study.view.slidepanel.CardSlidePanel;
import com.test.lpanstudyrecord.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpan on 2017/9/6.
 */

public class SlidePanelFragment extends BaseFragment {

    private CardSlidePanel mCardSlidePanel;

    private List<CardDataItem> mDataList;

    private static final int CARD_COUNT = 20;

    private int imagePaths[] = {
            R.drawable.wall01, R.drawable.wall02,
            R.drawable.wall03, R.drawable.wall04,
            R.drawable.wall05, R.drawable.wall06,
            R.drawable.wall07, R.drawable.wall08,
            R.drawable.wall09, R.drawable.wall10,
            R.drawable.wall01, R.drawable.wall02,
            R.drawable.wall03, R.drawable.wall04,
            R.drawable.wall05, R.drawable.wall06,
            R.drawable.wall07, R.drawable.wall08,
            R.drawable.wall09, R.drawable.wall10}; // 20个图片资源

    private String names[] = {"郭富城", "刘德华", "张学友", "李连杰", "成龙", "谢霆锋",
            "霍建华", "胡歌", "吴孟达", "梁朝伟"}; // 20个人名


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_slide_panel;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mCardSlidePanel = (CardSlidePanel) view.findViewById(R.id.card_slide_panel);
        mCardSlidePanel.setCardSwitchListener(new CardSlidePanel.CardSwitchListener() {
            @Override
            public void onShow(int index) {
                Log.d("Card", "正在显示-" + mDataList.get(index).userName);

            }

            @Override
            public void onCardVanish(int index, int type) {
                Log.d("Card", "正在消失-" + mDataList.get(index).userName + " 消失type=" + type);

                if(index == mDataList.size()-1){
                    toastShort("无更多数据,请稍后");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getData();
                            mCardSlidePanel.getAdapter().notifyDataSetChanged();
                        }
                    },1000);
                }

            }
        });

    }

    private void getData(){
        if (CollectionUtils.isEmpty(mDataList)) {
            mDataList = new ArrayList<>();
        }
        for (int i = 0; i < CARD_COUNT; i++) {
            CardDataItem cardDataItem = new CardDataItem(imagePaths[i], "jason" + i, i, i);
            mDataList.add(cardDataItem);
        }
    }

    @Override
    protected void initData() {
        super.initData();
        mDataList = new ArrayList<>();
        for (int i = 0; i < CARD_COUNT; i++) {
            CardDataItem cardDataItem = new CardDataItem(imagePaths[i], "jason" + i, i, i);
            mDataList.add(cardDataItem);
        }

        mCardSlidePanel.setAdapter(new CardAdapter() {
            @Override
            public int getLayoutId() {
                return R.layout.card_item;
            }

            @Override
            public int getCount() {
                return mDataList.size();
            }

            @Override
            public void bindView(View view, int index) {
                Object tag = view.getTag();
                ViewHolder viewHolder;
                if (null != tag) {
                    viewHolder = (ViewHolder) tag;
                } else {
                    viewHolder = new ViewHolder(view);
                    view.setTag(viewHolder);
                }

                viewHolder.bindData(mDataList.get(index));
            }

            @Override
            public Rect obtainDraggableArea(View view) {
                // 可滑动区域定制，该函数只会调用一次
                View contentView = view.findViewById(R.id.card_item_content);
                View topLayout = view.findViewById(R.id.card_top_layout);
                View bottomLayout = view.findViewById(R.id.card_bottom_layout);
                int left = view.getLeft() + contentView.getPaddingLeft() + topLayout.getPaddingLeft();
                int right = view.getRight() - contentView.getPaddingRight() - topLayout.getPaddingRight();
                int top = view.getTop() + contentView.getPaddingTop() + topLayout.getPaddingTop();
                int bottom = view.getBottom() - contentView.getPaddingBottom() - bottomLayout.getPaddingBottom();
                return new Rect(left, top, right, bottom);
            }
        });
    }

    class ViewHolder {

        ImageView imageView;
        View maskView;
        TextView userNameTv;
        TextView imageNumTv;
        TextView likeNumTv;
        View layout;

        public ViewHolder(View view) {
            layout = view.findViewById(R.id.card_item_content);
            imageView = (ImageView) view.findViewById(R.id.card_image_view);
            maskView = view.findViewById(R.id.maskView);
            userNameTv = (TextView) view.findViewById(R.id.card_user_name);
            imageNumTv = (TextView) view.findViewById(R.id.card_pic_num);
            likeNumTv = (TextView) view.findViewById(R.id.card_like);
        }

        public void bindData(CardDataItem itemData) {
            //image
            imageView.setImageResource(itemData.imagePath);
            userNameTv.setText(itemData.userName);
            imageNumTv.setText(itemData.imageNum + "");
            likeNumTv.setText(itemData.likeNum + "");

            maskView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toastShort("进入大图");
                }
            });
        }
    }
}
