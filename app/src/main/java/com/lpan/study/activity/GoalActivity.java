package com.lpan.study.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.widget.LinearLayout;

import com.lpan.study.fragment.HomeHolderFragment;
import com.lpan.study.utils.FragmentUtils;
import com.test.lpanstudyrecord.R;

/**
 * Created by lpan on 2016/12/19.
 */

public class GoalActivity extends BaseActivity {

    public static final String EXTRAS_CLASS_NAME = "extras_class_name";

    public static final String EXTRAS_BUNDLE = "extras_bundle";


    private LinearLayout mContainer;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_goal;
    }

    @Override
    protected void initView() {
        super.initView();
        mContainer = (LinearLayout) findViewById(R.id.container);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String className = getIntent().getExtras().getString(EXTRAS_CLASS_NAME);
        Bundle activityBundle = getIntent().getBundleExtra(EXTRAS_BUNDLE);

        loadFragment(className,activityBundle);
    }

    private void loadFragment(String className,Bundle bundle) {
        if (!TextUtils.isEmpty(className)) {
            Fragment fragment = null;
            try {
                fragment = (Fragment) Class.forName(className).newInstance();
                FragmentUtils.replaceFragment(R.id.container, getSupportFragmentManager(),
                        fragment, bundle);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }

    }
}
