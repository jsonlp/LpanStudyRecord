package com.lpan.study.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class SpaceItemDecoration extends RecyclerView.ItemDecoration{
    public static final int VERTICAL =1 ;
    public static final int HORIZONTAL =2 ;

    private int firstItemSpace;
    private int otherItemSpace;
    private int orientation;

    public SpaceItemDecoration(int orientation,int firstItemSpace, int otherItemSpace) {
        this.firstItemSpace = firstItemSpace;
        this.otherItemSpace = otherItemSpace;
        this.orientation = orientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if(parent.getChildPosition(view) != 0){
            if (orientation == VERTICAL) {
                outRect.top = otherItemSpace;
            }else if(orientation == HORIZONTAL){
                outRect.left = otherItemSpace;
            }
        }
        else{
            if (orientation == VERTICAL) {
                outRect.top = firstItemSpace;
            }else if(orientation == HORIZONTAL){
                outRect.left = firstItemSpace;
            }
        }
    }
}
