package com.lpan.study.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.lpan.study.activity.GoalActivity;

/**
 * Created by lpan on 2016/12/19.
 */

public class FragmentUtils {

    public static void navigateToInNewActivity(Context context, Fragment fragment, Bundle bundle) {
        Intent intent = new Intent(context, GoalActivity.class);

        intent.putExtra(GoalActivity.EXTRAS_CLASS_NAME, fragment.getClass()
                .getName());
        intent.putExtra(GoalActivity.EXTRAS_BUNDLE, bundle);

        context.startActivity(intent);
    }

    public static void replaceFragment(int fragmentId, FragmentManager fragmentManager,
                                       Fragment fragment, Bundle bundle) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (bundle != null) {
            fragment.setArguments(bundle);
        }

        fragmentTransaction.replace(fragmentId, fragment, fragment.getClass().getName());
        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();
    }
}
