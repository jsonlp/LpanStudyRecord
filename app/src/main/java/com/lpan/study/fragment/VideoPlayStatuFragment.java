package com.lpan.study.fragment;

import android.view.View;
import android.widget.TextView;

import com.lpan.study.view.ThrobbingNoteView;
import com.test.lpanstudyrecord.R;

/**
 * Created by lpan on 2017/6/9.
 */

public class VideoPlayStatuFragment extends BaseFragment implements View.OnClickListener {

    private TextView mStart, mStop;

    private ThrobbingNoteView mThrobbingNoteView;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_video_play_statu;
    }


    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mStart = (TextView) view.findViewById(R.id.text1);
        mStop = (TextView) view.findViewById(R.id.text2);
        mThrobbingNoteView = (ThrobbingNoteView) view.findViewById(R.id.throbbing_note_view);

        mStart.setOnClickListener(this);
        mStop.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text1:
                mThrobbingNoteView.startAnimation();
                break;

            case R.id.text2:
                mThrobbingNoteView.stopAnimation();

                break;
        }
    }

}
