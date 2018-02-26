package com.lpan.study.fragment;

import android.graphics.PointF;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lpan.R;
import com.lpan.study.adapter.ClassroomAdapter;
import com.lpan.study.context.AppContext;
import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.listener.OnAvatarClickListener;
import com.lpan.study.model.UserInfo;
import com.lpan.study.utils.Log;
import com.lpan.study.utils.ViewUtils;
import com.lpan.study.view.MyPopupWindow;
import com.lpan.study.view.pathanimation.AnimatorPath;
import com.lpan.study.view.pathanimation.PaperPlaneView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by liaopan on 2018/1/11 15:29.
 */

public class ClassroomFragment extends ButterKnifeFragment implements OnAvatarClickListener<UserInfo>, View.OnClickListener {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @BindView(R.id.top_tag)
    TextView mTopTag;

    @BindView(R.id.left_tag)
    TextView mLeftTag;

    @BindView(R.id.center_tag)
    TextView mCenterTag;

    @BindView(R.id.right_tag)
    TextView mRightTag;

    @BindView(R.id.bottom_tag)
    View mBottomTag;

    @BindView(R.id.left_line)
    View mLeftLine;

    @BindView(R.id.right_line)
    View mRightLine;

    @BindView(R.id.paper_plane1)
    PaperPlaneView mPlane1;

    @BindView(R.id.paper_plane2)
    PaperPlaneView mPlane2;

    @BindView(R.id.paper_plane3)
    PaperPlaneView mPlane3;

    @BindView(R.id.paper_plane4)
    PaperPlaneView mPlane4;

    @BindView(R.id.paper_plane5)
    PaperPlaneView mPlane5;

    @BindView(R.id.layout)
    View mRecyclerLayout;


    private ClassroomAdapter mAdapter;

    private MyPopupWindow mPopupWindow;

    private View mPopupContentView;

    private static final int POPUP_WIDTH = (int) (ViewUtils.ONE_DP * 150);

    private static final int POPUP_HEIGHT = (int) (ViewUtils.ONE_DP * 40);

    private int mPosition;

    private List<PaperPlaneView> mPlanes = new ArrayList<>();

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_classroom;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 10));

        mRecyclerView.setAdapter(getSeatChatAdapter());
        mPlanes.add(mPlane1);
        mPlanes.add(mPlane2);
        mPlanes.add(mPlane3);
        mPlanes.add(mPlane4);
        mPlanes.add(mPlane5);

    }

    public ClassroomAdapter getSeatChatAdapter() {
        if (mAdapter == null) {
            mAdapter = new ClassroomAdapter(getActivity(), this);
        }
        return mAdapter;
    }

    @Override
    protected void initData() {
        super.initData();
        List<UserInfo> list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setName("jason" + i);
            list.add(userInfo);
        }
        getSeatChatAdapter().addItem(list);
        getSeatChatAdapter().notifyDataSetChanged();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setTag();
            }
        }, 1000);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setTag() {

        int itemWidth = (int) (ViewUtils.ONE_DP * 23);
        View startView = mRecyclerView.getChildAt(0);
        int[] startLocation = new int[2];
        startView.getLocationOnScreen(startLocation);
        int dy = (int) (startLocation[1]);

        View topTag = mRecyclerView.getChildAt(4);
        int[] topLocation = new int[2];
        topTag.getLocationOnScreen(topLocation);
        mTopTag.getLayoutParams().height = (int) (ViewUtils.ONE_DP * 20);
        mTopTag.getLayoutParams().width = ViewUtils.getScreenWidth(AppContext.getContext());
        setLayoutY(mTopTag, (int) (topLocation[1] + itemWidth + itemWidth / 2 + ViewUtils.ONE_DP * 10 + -dy));


        View leftTag = mRecyclerView.getChildAt(21);
        int[] leftLocation = new int[2];
        leftTag.getLocationOnScreen(leftLocation);
        mLeftTag.getLayoutParams().width = ViewUtils.getScreenWidth(AppContext.getContext()) * 3 / 10;
        mLeftTag.getLayoutParams().height = (int) (ViewUtils.ONE_DP * 20);
        setLayoutY(mLeftTag, (int) (leftLocation[1] + ViewUtils.ONE_DP * 40 - dy));

        View centerTag = mRecyclerView.getChildAt(23);
        int[] centerLocation = new int[2];
        centerTag.getLocationOnScreen(centerLocation);
        mCenterTag.getLayoutParams().width = ViewUtils.getScreenWidth(AppContext.getContext()) * 4 / 10;
        mCenterTag.getLayoutParams().height = (int) (ViewUtils.ONE_DP * 20);
        setLayout(mCenterTag, centerLocation[0], (int) (centerLocation[1] + ViewUtils.ONE_DP * 50 - dy));

        View rightTag = mRecyclerView.getChildAt(27);
        int[] rightLocation = new int[2];
        rightTag.getLocationOnScreen(rightLocation);
        mRightTag.getLayoutParams().width = ViewUtils.getScreenWidth(AppContext.getContext()) * 3 / 10;
        mRightTag.getLayoutParams().height = (int) (ViewUtils.ONE_DP * 20);
        setLayout(mRightTag, rightLocation[0], (int) (rightLocation[1] + ViewUtils.ONE_DP * 40 - dy));


        View bottomTag = mRecyclerView.getChildAt(34);
        int[] bottomLocation = new int[2];
        bottomTag.getLocationOnScreen(bottomLocation);
        mBottomTag.getLayoutParams().height = (int) (ViewUtils.ONE_DP * 20);
        mBottomTag.getLayoutParams().width = ViewUtils.getScreenWidth(AppContext.getContext());
        setLayoutY(mBottomTag, (int) (bottomLocation[1] + itemWidth + itemWidth / 2 + ViewUtils.ONE_DP * 10 + -dy));


        View leftLine = mRecyclerView.getChildAt(13);
        int[] leftLineLocation = new int[2];
        leftLine.getLocationOnScreen(leftLineLocation);
        setLayout(mLeftLine, (int) (leftLineLocation[0] - ViewUtils.ONE_DP * 5), leftLineLocation[1]);

        View rightLine = mRecyclerView.getChildAt(17);
        int[] rightLineLocation = new int[2];
        rightLine.getLocationOnScreen(rightLineLocation);
        setLayout(mRightLine, (int) (rightLineLocation[0] - ViewUtils.ONE_DP * 5), rightLineLocation[1]);

    }

    private void layoutView(View view, int position, int width, int height, int dy) {
        View tagView = mRecyclerView.getChildAt(position);
        int[] location = new int[2];
        tagView.getLocationOnScreen(location);
        view.getLayoutParams().height = height;
        view.getLayoutParams().width = width;
        setLayoutY(mBottomTag, (int) (location[1] + ViewUtils.ONE_DP * 60 - dy));

    }

    /*
     * 设置控件所在的位置YY，并且不改变宽高，
     * XY为绝对位置
     */
    public void setLayout(View view, int x, int y) {
        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(view.getLayoutParams());
        margin.setMargins(x, y, x + margin.width, y + margin.height);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(margin);
        if (Log.DEBUG) {
            Log.d("ClassroomFragment", "setLayout--------left=" + x + "  top=" + y + "  right=" + (x + margin.width) + "  bpttom=" + (y + margin.height));
        }
        view.setLayoutParams(layoutParams);
    }

    /*
     * 设置控件所在的位置X，并且不改变宽高，
     * X为绝对位置，此时Y可能归0
     */
    public static void setLayoutX(View view, int x) {
        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(view.getLayoutParams());
        margin.setMargins(x, margin.topMargin, x + margin.width, margin.bottomMargin);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(margin);
        view.setLayoutParams(layoutParams);
    }

    /*
     * 设置控件所在的位置Y，并且不改变宽高，
     * Y为绝对位置，此时X可能归0
     */
    public static void setLayoutY(View view, int y) {
        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(view.getLayoutParams());
        margin.setMargins(margin.leftMargin, y, margin.rightMargin, y + margin.height);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(margin);
        view.setLayoutParams(layoutParams);
    }


    @Override
    public void OnAvatarClick(UserInfo user, View view, int position) {
        mPosition = position;
        if (mPopupWindow == null) {
            mPopupContentView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_popup_see_pofile, null);
            View seeProfile = mPopupContentView.findViewById(R.id.see_profile);
            View tapeChat = mPopupContentView.findViewById(R.id.tape_chat);
            seeProfile.setOnClickListener(this);
            tapeChat.setOnClickListener(this);
            mPopupWindow = new MyPopupWindow(mPopupContentView, POPUP_WIDTH, POPUP_HEIGHT, true);
//            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setFocusable(false);
        }
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        mPopupWindow.showAtLocation(view, 0, (x - POPUP_WIDTH / 2), (y - POPUP_HEIGHT));

    }

    private PointF getPointByPosition(int position) {
        int ONE_DP = (int) ViewUtils.ONE_DP;
        int SCREEN_HEIGHT = ViewUtils.getStatusHeight(AppContext.getContext());
        int y = (int) mRecyclerLayout.getY();
        int parentTop = mRecyclerLayout.getTop();
        if (Log.DEBUG) {
            Log.d("ClassroomFragment", "getPointByPosition--------y=" + y + "  top=" + parentTop);
        }
        View childAt = mRecyclerView.getChildAt(position);
        int top = 0;
        if (childAt != null) {
            int[] location = new int[2];
            childAt.getLocationOnScreen(location);
            if (position % 10 == 0 || position % 10 == 9) {
                top = 0;
            } else if (position % 10 == 1 || position % 10 == 8) {
                top = ONE_DP * 5;
            } else if (position % 10 == 2 || position % 10 == 7) {
                top = ONE_DP * 9;
            } else if (position % 10 == 3 || position % 10 == 6) {
                top = ONE_DP * 11;
            } else {
                top = ONE_DP * 12;
            }
            return new PointF(location[0], location[1] - SCREEN_HEIGHT - parentTop + top);
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tape_chat:
                mPopupWindow.dismiss();

                fly(0, mPosition);
                fly(1, mPosition);
                fly(2, mPosition);
                fly(3, mPosition);
                fly(4, mPosition);

                fly(5, mPosition);
                fly(6, mPosition);
                fly(7, mPosition);
                fly(8, mPosition);
                fly(9, mPosition);

                break;
        }
    }

    private void fly(final int fromPosition, final int toPosition) {
        PointF startPoint = getPointByPosition(fromPosition);
        PointF endPoint = getPointByPosition(toPosition);
        if (startPoint != null && endPoint != null) {
            PointF control = new PointF(endPoint.x, startPoint.y);
            PaperPlaneView freePlane = getFreePlane();
            if (freePlane != null) {
                freePlane.setBusy(true);
                freePlane.fly(0, startPoint, control, endPoint);
                if (Log.DEBUG) {
                    Log.d("ClassroomFragment", "can fly--------" + freePlane.toString());
                }
            } else {
                //没有空闲飞机
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fly(fromPosition, toPosition);
                    }
                }, 1000);
            }
        }
    }

    private PaperPlaneView getFreePlane() {
        for (PaperPlaneView paperPlaneView : mPlanes) {
            if (!paperPlaneView.isBusy()) {
                return paperPlaneView;
            }
        }
        return null;
    }
}