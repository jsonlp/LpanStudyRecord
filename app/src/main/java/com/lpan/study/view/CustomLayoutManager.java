package com.lpan.study.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class CustomLayoutManager extends RecyclerView.LayoutManager {

    private int verticalOffset = 0;
    private int totalHeight = 0;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);

        if (getItemCount() <= 0) {
            return;
        }
        detachAndScrapAttachedViews(recycler);

        int offsetY = 0;
        totalHeight = 0;
        for (int i = 0; i < getItemCount(); i++) {
            View viewForPosition = recycler.getViewForPosition(i);
            addView(viewForPosition);
            measureChildWithMargins(viewForPosition, 0, 0);
            int width = getDecoratedMeasuredWidth(viewForPosition);
            int height = getDecoratedMeasuredHeight(viewForPosition);
            layoutDecorated(viewForPosition, 0, offsetY, width, height + offsetY);
            offsetY += height;
            totalHeight += height;
        }

        totalHeight = Math.max(totalHeight, getVerticalSpace());

    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int distance = dy;

        if (verticalOffset + dy < 0) {
            distance = -verticalOffset;
        } else if (verticalOffset + dy > totalHeight - getVerticalSpace()) {
            distance = totalHeight - verticalOffset - getVerticalSpace();
        }
        verticalOffset += distance;
        offsetChildrenVertical(-distance);
        return distance;
    }

    private int getVerticalSpace() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }


}
