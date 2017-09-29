package com.lpan.study.view.pulltorefresh;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.lpan.study.view.pulltorefresh.internal.LoadingLayout;

public class RecyclerViewRefreshListView extends PullToRefreshBase<JiemoRecyclerView> {

    private OnLastItemVisibleListener mOnLastItemVisibleListener;
    private boolean scrollingDisable;

    public RecyclerViewRefreshListView(Context context) {
        super(context);
        init();
    }

    public RecyclerViewRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RecyclerViewRefreshListView(Context context, Mode mode) {
        super(context, mode);
        init();
    }

    public RecyclerViewRefreshListView(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
        init();
    }

    public final void setOnLastItemVisibleListener(OnLastItemVisibleListener listener) {
        mOnLastItemVisibleListener = listener;
    }

    public void setScrollingDisable(boolean scrollingDisable) {
//        super.setScrollingDisable(scrollingDisable);
        this.scrollingDisable = scrollingDisable;
    }

    private void init() {
        mRefreshableView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (RecyclerView.SCROLL_STATE_IDLE == newState) {
                    if (mOnLastItemVisibleListener != null && isLastItemVisible()) {
                        mOnLastItemVisibleListener.onLastItemVisible();
                    }
                    RecyclerViewRefreshListView.super.setScrollingDisable(scrollingDisable);

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    protected boolean isReadyForPullStart() {
        return isFirstItemVisible();
    }

    protected boolean isReadyForPullEnd() {
        return isLastItemVisible();
    }

    public boolean isFirstItemVisible() {
        final RecyclerView.Adapter adapter = mRefreshableView.getAdapter();

        if (null == adapter || adapter.getItemCount() == 0) {
            //            if (DEBUG) {
            //                Log.d(TAG, "isFirstItemVisible. Empty View.");
            //            }
            return true;

        } else {

            /**
             * This check should really just be:
             * mRefreshableView.getFirstVisiblePosition() == 0, but
             * PtRListView internally use a HeaderView which messes the
             * positions up. For now we'll just add one to account for it
             * and rely on the inner condition which checks getTop().
             */
            View view = mRefreshableView.getLayoutManager().findViewByPosition(0);
            if (view != null) {
                return view.getTop() >= mRefreshableView.getTop();

            }
        }

        return false;
    }

    private boolean isLastItemVisible() {
        final RecyclerView.Adapter adapter = mRefreshableView.getAdapter();
        if (null == adapter || adapter.getItemCount() == 0) {
            return true;
        } else {
            View view = mRefreshableView.getLayoutManager().findViewByPosition(
                    adapter.getItemCount() - 1);
            if (view != null) {
                return view.getBottom() <= mRefreshableView.getBottom();
            }
        }

        return false;
    }

    //

    public void setShowLoadMore(boolean visible) {
        if (getFooterLayout() != null) {
            if (visible) {
                getFooterLayout().showProgress();
                getFooterLayout().setVisibility(View.VISIBLE);
            } else {
                getFooterLayout().hideAllViews();
                getFooterLayout().setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected final JiemoRecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        return new JiemoRecyclerView(context, attrs);
    }

    @Override
    protected void onRefreshing(State state, boolean doScroll) {
        RecyclerView.Adapter adapter = mRefreshableView.getAdapter();

        if (!getShowViewWhileRefreshing() || null == adapter || adapter.getItemCount() == 0) {
            super.onRefreshing(state, doScroll);
            return;
        }

        if (getMode().showHeaderLoadingLayout()) {
            getHeaderLayout().refreshing();
        }

        if (getMode().showFooterLoadingLayout()) {
            getFooterLayout().refreshing();
        }

        final LoadingLayout /*origLoadingView,*/listViewLoadingView, oppositeListViewLoadingView;
        final int selection, scrollToY;
        switch (getCurrentMode()) {
            case MANUAL_REFRESH_ONLY:
            case PULL_FROM_END:
                //                origLoadingView = getFooterLayout();
                //                listViewLoadingView = mFooterLoadingView;
                listViewLoadingView = getFooterLayout();
                //                oppositeListViewLoadingView = mHeaderLoadingView;
                oppositeListViewLoadingView = getHeaderLayout();
                selection = mRefreshableView.getAdapter().getItemCount() - 1;
                scrollToY = getScrollY() - getFooterSize();
                break;
            case PULL_FROM_START:
            default:
                //                origLoadingView = getHeaderLayout();
                //                listViewLoadingView = mHeaderLoadingView;
                listViewLoadingView = getHeaderLayout();
                //                oppositeListViewLoadingView = mFooterLoadingView;
                oppositeListViewLoadingView = getFooterLayout();
                selection = 0;
                //                scrollToY = getScrollY() + getHeaderSize();
                if (state == State.REFRESHING) {
                    scrollToY = getScrollY() + getHeaderSize() - getActionbarHeight();
                } else {
                    scrollToY = 0;
                }

                break;
        }

        // Hide our original Loading View
        //        origLoadingView.reset();
        //        origLoadingView.hideAllViews();

        // Make sure the opposite end is hidden too
        oppositeListViewLoadingView.setVisibility(View.GONE);

        // Show the ListView Loading View and set it to refresh.
        refreshListLoadingView(listViewLoadingView, state, doScroll, scrollToY, selection);
    }

    private void refreshListLoadingView(LoadingLayout listViewLoadingView, State state,
                                        boolean doScroll, int scrollToY, int selection) {
        listViewLoadingView.setVisibility(View.VISIBLE);
        listViewLoadingView.refreshing();
        if (doScroll) {
            // We need to disable the automatic visibility changes for now
            disableLoadingLayoutVisibilityChanges();
            // We scroll slightly so that the ListView's header/footer is at the
            // same Y position as our normal header/footer
            if (state != State.REFRESHING) {
                setHeaderScroll(scrollToY);
            }

            mRefreshableView.getLayoutManager().scrollToPosition(selection);
            // Smooth scroll as normal
            OnSmoothScrollFinishedListener listener = new OnSmoothScrollFinishedListener() {

                @Override
                public void onSmoothScrollFinished() {
                    callRefreshListener();
                }
            };
            //            smoothScrollTo(0, listener);
            smoothScrollTo(0 - listViewLoadingView.getContentSize(), listener);
        } else {
            callRefreshListener();
        }
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof Bundle)) {
            state = null;
        }
        super.onRestoreInstanceState(state);
    }

}
