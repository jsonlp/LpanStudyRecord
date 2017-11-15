package com.lpan.study.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.TextView;

import com.lpan.study.fragment.UserProfileFragment;
import com.lpan.study.model.CardDataItem;
import com.lpan.study.utils.CollectionUtils;
import com.lpan.study.view.roundedimageview.RoundedImageView;
import com.lpan.study.view.slidepanel.CardAdapter;
import com.lpan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpan on 2017/9/14.
 */

public class SlideCardAdapter extends CardAdapter {

    private List<CardDataItem> mList;

    private Context mContext;

    private int mLayoutId;

    public SlideCardAdapter(Context context, int layoutId) {
        mContext = context;
        mLayoutId = layoutId;
        mList = new ArrayList<>();
    }

    public void addItem(CardDataItem t) {
        mList.add(t);
    }

    public void addItem(List<CardDataItem> t) {
        mList.addAll(t);
    }

    public List<CardDataItem> getList(){
        return  mList;
    }


    @Override
    public int getLayoutId() {
        return mLayoutId;
    }

    @Override
    public int getCount() {
        return CollectionUtils.isEmpty(mList) ? 0 : mList.size();
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

        viewHolder.bindData(mList.get(index), mContext);
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

    class ViewHolder {

        RoundedImageView imageView;
        View maskView;
        TextView userNameTv;
        TextView imageNumTv;
        TextView likeNumTv;
        View layout;

        public ViewHolder(View view) {
            layout = view.findViewById(R.id.card_item_content);
            imageView = (RoundedImageView) view.findViewById(R.id.card_image_view);
            maskView = view.findViewById(R.id.maskView);
            userNameTv = (TextView) view.findViewById(R.id.card_user_name);
            imageNumTv = (TextView) view.findViewById(R.id.card_pic_num);
            likeNumTv = (TextView) view.findViewById(R.id.card_like);
        }

        public void bindData(final CardDataItem itemData, final Context context) {
            //image
            imageView.setImageResource(itemData.imagePath);
            userNameTv.setText(itemData.userName);
            imageNumTv.setText(itemData.imageNum + "");
            likeNumTv.setText(itemData.likeNum + "");

            maskView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    toastShort("进入大图");
                    UserProfileFragment.show(context, maskView, itemData.imagePath);
                }
            });
        }
    }
}
