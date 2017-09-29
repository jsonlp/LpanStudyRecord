/**
 * ****************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * *****************************************************************************
 */
package com.lpan.study.view.pulltorefresh;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.lpan.study.view.pulltorefresh.internal.EmptyViewMethodAccessor;
import com.lpan.study.view.pulltorefresh.internal.LoadingLayout;
import com.test.lpanstudyrecord.R;

public class PullToRefreshGridView extends PullToRefreshAdapterViewBase<GridView> {

    public PullToRefreshGridView(Context context) {
        super(context);
    }

    public PullToRefreshGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshGridView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshGridView(Context context, Mode mode, AnimationStyle style) {
        super(context, mode, style);
    }

    @Override
    public final Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    @Override
    protected final GridView createRefreshableView(Context context, AttributeSet attrs) {
        final GridView gv;
        if (VERSION.SDK_INT >= VERSION_CODES.GINGERBREAD) {
            gv = new InternalGridViewSDK9(context, attrs);
        } else {
            gv = new InternalGridView(context, attrs);
        }

        // Use Generated ID (from res/values/ids.xml)
        gv.setId(R.id.gridview);
        return gv;
    }

    @Override
    protected void onRefreshing(State state, boolean doScroll) {
        ListAdapter adapter = mRefreshableView.getAdapter();
        if (/*!mListViewExtrasEnabled || */!getShowViewWhileRefreshing() || null == adapter
                || adapter.isEmpty()) {
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
                selection = mRefreshableView.getCount() - 1;
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
            // Make sure the ListView is scrolled to show the loading
            // header/footer

            mRefreshableView.setSelection(selection);
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

    class InternalGridView extends GridView implements EmptyViewMethodAccessor {

        public InternalGridView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        public void setEmptyView(View emptyView) {
            PullToRefreshGridView.this.setEmptyView(emptyView);
        }

        @Override
        public void setEmptyViewInternal(View emptyView) {
            super.setEmptyView(emptyView);
        }
    }

    @TargetApi(9)
    final class InternalGridViewSDK9 extends InternalGridView {

        public InternalGridViewSDK9(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY,
                int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY,
                boolean isTouchEvent) {

            final boolean returnValue = super.overScrollBy(deltaX, deltaY, scrollX, scrollY,
                    scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);

            // Does all of the hard work...
            OverscrollHelper.overScrollBy(PullToRefreshGridView.this, deltaX, scrollX, deltaY,
                    scrollY, isTouchEvent);

            return returnValue;
        }
    }
}
