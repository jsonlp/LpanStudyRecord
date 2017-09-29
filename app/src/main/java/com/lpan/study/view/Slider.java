/*
* 文 件 名：Slider.java
* 版 权：Copyright 2009-2011 Huawei Tech.Co.Ltd.All Rights Reserved.
* 描 述：
* 修 改 人：haoran.gao
* 修改时间：2011-11-21
* 修改内容：新增
*/
package com.lpan.study.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.lpan.study.context.AppContext;
import com.lpan.study.utils.ViewUtils;


/**
 * Like slider
 *
 * @author AlbertGao
 */
public class Slider extends ViewGroup {

    /**
     * Child count in view
     */
    public static final int DEFAULT_CHILD_COUNT_IN_VIEW = 3;

    private static final String TAG = "Slider";

    private static final int SCREEN_WIDTH = ViewUtils.getScreenWidth(AppContext.getContext());

    /**
     * Velocity threshold to slide
     */
    private static final int SNAP_VELOCITY = 300;

    /**
     * Horizontal offset threshold to slide
     */
    private static final int SNAP_OFFSET = SCREEN_WIDTH / DEFAULT_CHILD_COUNT_IN_VIEW / 3;

    /**
     * Sliding state
     */
    private static TouchState mTouchState = TouchState.TOUCH_STATE_SETTLED;

    /**
     * Child count in view
     */
    private int mChildCountInView = DEFAULT_CHILD_COUNT_IN_VIEW;

    /**
     * Current screen index
     */
    private int mCurScreen;

    /**
     * Minimum slide offset
     */
    private int mTouchSlop;

    /**
     * Actual width
     */
    private int mActualWidth;

    /**
     * Record last X-axis of a touch action
     */
    private float mLastMotionX;

    /**
     * Record last Y-axis of a touch action
     */
    private float mLastMotionY;

    /**
     * Record last X-axis of a down action
     */
    private float mLastDownX;

    /**
     * Check if reach edges
     */
    private boolean mIsCheckFringe = true;

    /**
     * The mark representing the touchable attribute of slider
     */
    private boolean mIsTouchable = true;

    /**
     * Widget to scroll the view
     */
    private Scroller mScroller;

    /**
     * Track the velocity after a touch action
     */
    private VelocityTracker mVelocityTracker;

    /**
     * Sliding listener
     */
    private OnSlidingListener mOnSlidingListener;

    public Slider(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mScroller = new Scroller(context);
        mCurScreen = 0;
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    public Slider(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * Is the slider scrolling
     *
     * @return
     */
    public static boolean isSliderScrolling() {
        return mTouchState == TouchState.TOUCH_STATE_SCROLLING;
    }

    /**
     * Is the slider settled
     *
     * @return
     */
    public static boolean isSliderSettled() {
        return mTouchState == TouchState.TOUCH_STATE_SETTLED;
    }

    /**
     * Get the current screen index
     *
     * @return mCurScreen
     */
    public int getCurScreen() {
        return mCurScreen;
    }

    /**
     * Get max child count in view
     *
     * @return
     */
    public int getChildCountInView() {
        return mChildCountInView;
    }

    /**
     * Set max number of visiable childs
     *
     * @param count
     */
    public void setChildCountInView(int count) {
        mChildCountInView = count;
    }

    /**
     * Sliding listener
     *
     * @param onSlidingListener
     */
    public void setOnSlidingListener(OnSlidingListener onSlidingListener) {
        mOnSlidingListener = onSlidingListener;
    }

    /**
     * Set the touchable attribute
     *
     * @param isTouchable
     */
    public void setTouchable(boolean isTouchable) {
        this.mIsTouchable = isTouchable;
    }

    /**
     * Set the flexibility of slider
     *
     * @param isCheckFringe
     */
    public void setCheckFringe(boolean isCheckFringe) {
        this.mIsCheckFringe = isCheckFringe;
    }

    /**
     * Representing how many child views at most the slider can hold in
     * view
     *
     * @param scale
     */
    public void setScaleX(int scaleX) {
        this.mChildCountInView = scaleX;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        // 解决Slider动态添加视图不刷新的问题：暂时不对changed做判断
        if (true/* changed */) {

            // 左边离第一个子视图开始的距离
            int childLeft = 0;

            final int childWidth = getMeasuredWidth() / mChildCountInView;

            for (int i = 0; i < getChildCount(); i++) {

                final View childView = getChildAt(i);

                if (childView.getVisibility() != View.GONE) {

                    childView.layout(childLeft, 0, childLeft + childWidth, getMeasuredHeight());

                    childLeft += childWidth;
                }
            }

            mActualWidth = childLeft;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int width = MeasureSpec.getSize(widthMeasureSpec);
        // final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        // if (widthMode != MeasureSpec.EXACTLY)
        // {
        // throw new IllegalStateException(
        // "Slider only can run at EXACTLY mode!");
        // }
        // final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        // if (heightMode != MeasureSpec.EXACTLY)
        // {
        // throw new IllegalStateException(
        // "Slider only can run at EXACTLY mode!");
        // }
        // 子窗口赋予与Slider同样的宽和高
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
        }

        scrollTo(mCurScreen * width / mChildCountInView, 0);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (!mIsTouchable) {

            // if (Log.DEBUG) {
            // Log.d(TAG, "onTouchEvent, ACTION = " + event.getAction() + " result = " + false);
            // }

            return false;
        }

        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);

        final float x = event.getX();
        final float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                mLastMotionX = x;
                mLastMotionY = y;
                mLastDownX = x;
                break;

            case MotionEvent.ACTION_MOVE:
                int deltaX = (int) (mLastMotionX - x);
                int deltaY = (int) (mLastMotionY - y);
                int checkX = getScrollX() + deltaX;
                mLastMotionX = x;
                mLastMotionY = y;

                // 判断是否滑到头
                if (mIsCheckFringe && (checkX <= 0 || checkX >= mActualWidth - getWidth())) {

                    // if (Log.DEBUG) {
                    // Log.d(TAG, "onTouchEvent, ACTION = " + event.getAction() + " result = "
                    // + true);
                    // }

                    return true;
                }

                scrollBy(deltaX, 0);
                break;

            case MotionEvent.ACTION_UP:
                mVelocityTracker.computeCurrentVelocity(1000);
                int velocityX = (int) mVelocityTracker.getXVelocity();
                int offsetX = (int) (x - mLastDownX);
                int deltaScreen = velocityX * getChildCount();

                if (getChildCount() <= mChildCountInView) {
                    snapToScreen(mCurScreen);
                } else if ((velocityX > SNAP_VELOCITY || offsetX > SNAP_OFFSET) && mCurScreen > 2) {
                    // 滑动速度足够向左滚动视图
                    snapToScreen(mCurScreen - 3);
                    // 将ACTION_UP事件传出
                    if (mOnSlidingListener != null) {
                        mOnSlidingListener.onSnapToLeft();
                    }
                } else if ((velocityX < -SNAP_VELOCITY || offsetX < -SNAP_OFFSET)
                        && mCurScreen < getChildCount() - mChildCountInView) {
                    // 滑动速度足够向右滚动视图
                    snapToScreen(mCurScreen + 3);
                    // 将ACTION_UP事件传出
                    if (mOnSlidingListener != null) {
                        mOnSlidingListener.onSnapToRight();
                    }
                } else {
                    snapToScreen(mCurScreen);
                }

                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
                mTouchState = TouchState.TOUCH_STATE_SETTLED;
                break;

            case MotionEvent.ACTION_CANCEL:
                mTouchState = TouchState.TOUCH_STATE_SETTLED;
                snapToScreen(mCurScreen);
                break;
        }

        // if (Log.DEBUG) {
        // Log.d(TAG, "onTouchEvent, ACTION = " + event.getAction() + " result = " + true);
        // }

        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        boolean result = super.dispatchTouchEvent(ev);

        // if (Log.DEBUG) {
        // Log.d(TAG, "dispatchTouchEvent, ACTION = " + ev.getAction() + " result = " + result);
        // }

        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        final int action = ev.getAction();

        if ((action == MotionEvent.ACTION_MOVE)
                && (mTouchState == TouchState.TOUCH_STATE_SCROLLING)) {

            // if (Log.DEBUG) {
            // Log.d(TAG, "onInterceptTouchEvent, ACTION = " + ev.getAction() + " result = "
            // + true);
            // }

            return true;
        }

        final float x = ev.getX();
        final float y = ev.getY();

        switch (action) {
            case MotionEvent.ACTION_MOVE:

                final int xDiff = (int) Math.abs(mLastMotionX - x);
                final int yDiff = (int) Math.abs(mLastMotionY - y);
                if (xDiff > yDiff || xDiff > mTouchSlop) {
                    mTouchState = TouchState.TOUCH_STATE_SCROLLING;
                }
                break;

            case MotionEvent.ACTION_DOWN:
                mLastMotionX = x;
                mLastMotionY = y;
                mLastDownX = x;

                if (mScroller.isFinished()) {
                    mTouchState = TouchState.TOUCH_STATE_SETTLED;
                } else {
                    mTouchState = TouchState.TOUCH_STATE_SCROLLING;
                }
                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mTouchState = TouchState.TOUCH_STATE_SETTLED;
                break;
        }

        // if (Log.DEBUG) {
        // Log.d(TAG, "onInterceptTouchEvent, ACTION = " + ev.getAction() + " return "
        // + (mTouchState != TouchState.TOUCH_STATE_SETTLED));
        // }

        return mTouchState != TouchState.TOUCH_STATE_SETTLED;
    }

    /**
     * Sliding to the target screen
     *
     * @param whichScreen
     */
    public void snapToScreen(int whichScreen) {
        whichScreen = Math.max(0, Math.min(whichScreen, getChildCount() - 1));
        if (getScrollX() != (whichScreen * getWidth() / mChildCountInView)) {
            final int delta = whichScreen * getWidth() / mChildCountInView - getScrollX();
            mScroller.startScroll(getScrollX(), 0, delta, 0, Math.abs(delta) * 2);
            mCurScreen = whichScreen;
            invalidate();
        }
    }

    public void snapToNext() {
        if (mCurScreen < getChildCount() - mChildCountInView) {
            snapToScreen(mCurScreen + 3);
        }
    }

    public void snapToPrevious() {
        if (mCurScreen > 0) {
            snapToScreen(mCurScreen - 3);
        }
    }

    /**
     * Set the target screen in view as the default view
     *
     * @param whichScreen
     */
    public void setToScreen(int whichScreen) {
        whichScreen = Math.max(0, Math.min(whichScreen, getChildCount() - 1));
        mCurScreen = whichScreen;
        scrollTo(whichScreen * getWidth() / mChildCountInView, 0);
    }

    /**
     * Check if the slider reach the left edge
     *
     * @return
     */
    public boolean reachLeftEdge() {
        return getChildCount() <= mChildCountInView || mCurScreen <= 0;
    }

    /**
     * Check if the slider reach the right edge
     *
     * @return
     */
    public boolean reachRightEdge() {
        return getChildCount() <= mChildCountInView
                || mCurScreen >= (getChildCount() - mChildCountInView);
    }

    private enum TouchState {
        TOUCH_STATE_SETTLED, TOUCH_STATE_SCROLLING
    }

    public interface OnSlidingListener {

        public void onSnapToLeft();

        public void onSnapToRight();
    }
}
