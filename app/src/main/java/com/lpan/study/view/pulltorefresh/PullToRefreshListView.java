package com.lpan.study.view.pulltorefresh;

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

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.lpan.study.context.AppContext;
import com.lpan.study.utils.ViewUtils;
import com.lpan.study.view.Slider;
import com.lpan.study.view.pulltorefresh.internal.EmptyViewMethodAccessor;
import com.lpan.study.view.pulltorefresh.internal.LoadingLayout;
import com.test.lpanstudyrecord.R;

public class PullToRefreshListView extends PullToRefreshAdapterViewBase<ListView> {

    //    private LoadingLayout mHeaderLoadingView;
    //
    //    private LoadingLayout mFooterLoadingView;
    //
    //    private FrameLayout mLvFooterLoadingFrame;

    private boolean mListViewExtrasEnabled;

    public PullToRefreshListView(Context context) {
        super(context);
    }

    public PullToRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshListView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshListView(Context context, Mode mode, AnimationStyle style) {
        super(context, mode, style);
    }

    @Override
    public final Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
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

    @Override
    public void onRefreshing(final State state, final boolean doScroll) {
        /**
         * If we're not showing the Refreshing view, or the list is empty,
         * the the header/footer views won't show so we use the normal
         * method.
         */
        ListAdapter adapter = mRefreshableView.getAdapter();
        if (!mListViewExtrasEnabled || !getShowViewWhileRefreshing() || null == adapter
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

    @Override
    protected void onPullLoadReset() {
        if (!mListViewExtrasEnabled) {
            super.onPullLoadReset();
            return;
        }

        final LoadingLayout /*originalLoadingLayout,*/listViewLoadingLayout;
        final int /*scrollToHeight, */selection;
        final boolean scrollLvToEdge;
        switch (getCurrentMode()) {
            case MANUAL_REFRESH_ONLY:
            case PULL_FROM_END:
                listViewLoadingLayout = getFooterLayout();
                selection = mRefreshableView.getCount() - 1;
                scrollLvToEdge = Math.abs(mRefreshableView.getLastVisiblePosition() - selection) <= 1;
                break;
            case PULL_FROM_START:
            default:
                listViewLoadingLayout = getHeaderLayout();
                selection = 0;
                scrollLvToEdge = Math.abs(mRefreshableView.getFirstVisiblePosition() - selection) <= 1;
                break;
        }

        if (listViewLoadingLayout.getVisibility() == View.VISIBLE) {

            if (scrollLvToEdge && getState() != State.MANUAL_REFRESHING) {
                mRefreshableView.setSelection(selection);
            }
        }

        super.onPullLoadReset();
    }

    @Override
    public void onReset() {
        /**
         * If the extras are not enabled, just call up to super and return.
         */
        if (!mListViewExtrasEnabled) {
            super.onReset();
            return;
        }

        final LoadingLayout /*originalLoadingLayout,*/listViewLoadingLayout;
        final int /*scrollToHeight, */selection;
        final boolean scrollLvToEdge;
        switch (getCurrentMode()) {
            case MANUAL_REFRESH_ONLY:
            case PULL_FROM_END:
                //                originalLoadingLayout = getFooterLayout();
                //                listViewLoadingLayout = mFooterLoadingView;
                listViewLoadingLayout = getFooterLayout();
                selection = mRefreshableView.getCount() - 1;
                //                scrollToHeight = getFooterSize();
                scrollLvToEdge = Math.abs(mRefreshableView.getLastVisiblePosition() - selection) <= 1;
                break;
            case PULL_FROM_START:
            default:
                //                originalLoadingLayout = getHeaderLayout();
                //                listViewLoadingLayout = mHeaderLoadingView;
                listViewLoadingLayout = getHeaderLayout();
                //                scrollToHeight = -getHeaderSize();
                selection = 0;
                scrollLvToEdge = Math.abs(mRefreshableView.getFirstVisiblePosition() - selection) <= 1;
                break;
        }

        // If the ListView header loading layout is showing, then we need to
        // flip so that the original one is showing instead
        if (listViewLoadingLayout.getVisibility() == View.VISIBLE) {

            // Set our Original View to Visible
            //            originalLoadingLayout.showInvisibleViews();

            // Hide the ListView Header/Footer
            //            listViewLoadingLayout.setVisibility(View.GONE);

            /**
             * Scroll so the View is at the same Y as the ListView
             * header/footer, but only scroll if: we've pulled to refresh,
             * it's positioned correctly
             */
            if (scrollLvToEdge && getState() != State.MANUAL_REFRESHING) {
                mRefreshableView.setSelection(selection);
                //                setHeaderScroll(scrollToHeight);
            }
        }

        // Finally, call up to super
        super.onReset();
    }

    @Override
    protected LoadingLayoutProxy createLoadingLayoutProxy(final boolean includeStart,
                                                          final boolean includeEnd) {
        LoadingLayoutProxy proxy = super.createLoadingLayoutProxy(includeStart, includeEnd);

        if (mListViewExtrasEnabled) {
            final Mode mode = getMode();

            if (includeStart && mode.showHeaderLoadingLayout()) {
                //            	proxy.addLayout(mHeaderLoadingView);
                proxy.addLayout(getHeaderLayout());
            }
            if (includeEnd && mode.showFooterLoadingLayout()) {
                //	            proxy.addLayout(mFooterLoadingView);
                proxy.addLayout(getFooterLayout());
            }
        }

        return proxy;
    }

    protected ListView createListView(Context context, AttributeSet attrs) {
        final ListView lv;
        //		if (VERSION.SDK_INT >= VERSION_CODES.GINGERBREAD) {
        //			lv = new InternalListViewSDK9(context, attrs);
        //		} else {
        lv = new InternalListView(context, attrs);
        lv.setDividerHeight(0);
        //		}
        return lv;
    }

    @Override
    protected ListView createRefreshableView(Context context, AttributeSet attrs) {
        ListView lv = createListView(context, attrs);

        // Set it to this so it can be used in ListActivity/ListFragment
        lv.setId(android.R.id.list);
        return lv;
    }

    @Override
    protected void handleStyledAttributes(TypedArray a) {
        super.handleStyledAttributes(a);

        mListViewExtrasEnabled = a.getBoolean(R.styleable.PullToRefresh_ptrListViewExtrasEnabled,
                true);

        if (mListViewExtrasEnabled) {
            final FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER_HORIZONTAL);

            //            // Create Loading Views ready for use later
            //            FrameLayout frame = new FrameLayout(getContext());
            //
            //            mHeaderLoadingView = createLoadingLayout(getContext(), Mode.PULL_FROM_START, a, true);
            //            mHeaderLoadingView.setVisibility(View.GONE);
            //
            //            frame.addView(mHeaderLoadingView, lp);
            //            mRefreshableView.addHeaderView(frame, null, false);

            //            mLvFooterLoadingFrame = new FrameLayout(getContext());
            //            mFooterLoadingView = createLoadingLayout(getContext(), Mode.PULL_FROM_END, a);
            //            mFooterLoadingView.setVisibility(View.GONE);
            //            mFooterLoadingView.showProgress();
            //            mLvFooterLoadingFrame.addView(mFooterLoadingView, lp);

            if (!a.hasValue(R.styleable.PullToRefresh_ptrScrollingWhileRefreshingEnabled)) {
                setScrollingWhileRefreshingEnabled(true);
            }
        }
    }

    @Override
    public void setShowLoadMore(boolean visible) {
        //        if (mFooterLoadingView != null) {
        //            mFooterLoadingView.setVisibility(visible ? View.VISIBLE : View.GONE);
        //        }
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

    public boolean isFooterVisiable() {
        return getFooterLayout().getVisibility() == VISIBLE;
    }

    public void hideHeaderLoading() {
        //        resetHeader();
        onReset();
    }

    @TargetApi(9)
    final class InternalListViewSDK9 extends InternalListView {

        public InternalListViewSDK9(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY,
                                       int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY,
                                       boolean isTouchEvent) {

            final boolean returnValue = super.overScrollBy(deltaX, deltaY, scrollX, scrollY,
                    scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);

            // Does all of the hard work...
            OverscrollHelper.overScrollBy(PullToRefreshListView.this, deltaX, scrollX, deltaY,
                    scrollY, isTouchEvent);

            return returnValue;
        }
    }


    @Override
    protected void onPullLoadComplete(int index) {
        super.onPullLoadComplete(index);
        scrollTo(0, 0);
        mRefreshableView.setSelectionFromTop(index, ViewUtils.dp2px(AppContext.getContext(), 50));

    }

    protected class InternalListView extends ListView implements EmptyViewMethodAccessor {

        private float xDistance, yDistance, lastX, lastY;

        private boolean mAddedLvFooter = false;

        public InternalListView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        protected void dispatchDraw(Canvas canvas) {
            /**
             * This is a bit hacky, but Samsung's ListView has got a bug in
             * it when using Header/Footer Views and the list is empty.
             * This masks the issue so that it doesn't cause an FC. See
             * Issue #66.
             */
            try {
                super.dispatchDraw(canvas);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {
            /**
             * This is a bit hacky, but Samsung's ListView has got a bug in
             * it when using Header/Footer Views and the list is empty.
             * This masks the issue so that it doesn't cause an FC. See
             * Issue #66.
             */

            try {
                return super.dispatchTouchEvent(ev);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        public void setAdapter(ListAdapter adapter) {
            // Add the Footer View at the last possible moment
            //            if (null != mLvFooterLoadingFrame && !mAddedLvFooter) {
            //                addFooterView(mLvFooterLoadingFrame, null, false);
            //                mAddedLvFooter = true;
            //            }
            if (null != getFooterLayout() && !mAddedLvFooter) {
                addFooterView(getFooterLayout(), null, false);
                mAddedLvFooter = true;
            }
            super.setAdapter(adapter);
        }

        @Override
        public void setEmptyView(View emptyView) {
            PullToRefreshListView.this.setEmptyView(emptyView);
        }

        @Override
        public void setEmptyViewInternal(View emptyView) {
            super.setEmptyView(emptyView);
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            switch (ev.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    xDistance = yDistance = 0f;
                    lastX = ev.getX();
                    lastY = ev.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    final float curX = ev.getX();
                    final float curY = ev.getY();
                    xDistance += Math.abs(curX - lastX);
                    yDistance += Math.abs(curY - lastY);
                    lastX = curX;
                    lastY = curY;

                    if (xDistance > yDistance || Slider.isSliderScrolling()) {
                        return false;
                    }
                    break;
                case MotionEvent.ACTION_UP:

                    break;
            }

            return super.onInterceptTouchEvent(ev);
        }

    }

}
