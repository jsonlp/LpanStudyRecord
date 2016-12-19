package com.lpan.study.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.lpan.study.activity.GoalActivity;

/**
 * Created by lpan on 2016/12/19.
 */

public class FragmentUtils {

    public static void navigateToInNewActivity(Activity context, Fragment fragment, Bundle bundle) {
        Intent intent = new Intent(context, GoalActivity.class);

        intent.putExtra(GoalActivity.EXTRAS_CLASS_NAME, fragment.getClass()
                .getName());
        intent.putExtra(GoalActivity.EXTRAS_BUNDLE, bundle);

        context.startActivity(intent);
    }
}
