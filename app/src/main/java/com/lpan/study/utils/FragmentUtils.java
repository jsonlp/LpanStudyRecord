package com.lpan.study.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.lpan.study.activity.FullScreenActivity;
import com.lpan.study.activity.TransparentActivity;

/**
 * Created by lpan on 2016/12/19.
 */

public class FragmentUtils {

    public static void navigateToInNewActivity(Context context, Fragment fragment, Bundle bundle) {
        Intent intent = new Intent(context, TransparentActivity.class);

        intent.putExtra(TransparentActivity.EXTRAS_CLASS_NAME, fragment.getClass()
                .getName());
        intent.putExtra(TransparentActivity.EXTRAS_BUNDLE, bundle);
        context.startActivity(intent);
    }

    public static void navigateToInNewActivityWithTranstion(Context context, View view, Fragment fragment, Bundle bundle) {
        Intent intent = new Intent(context, TransparentActivity.class);

        intent.putExtra(TransparentActivity.EXTRAS_CLASS_NAME, fragment.getClass()
                .getName());
        intent.putExtra(TransparentActivity.EXTRAS_BUNDLE, bundle);
//        context.startActivity(intent);

//        // 这里指定了共享的视图元素
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation((Activity) context, view, "image");
        ActivityCompat.startActivity(context, intent, options.toBundle());
    }

    public static void navigateToInFullScreenActivity(Context context, Fragment fragment, Bundle bundle) {
        Intent intent = new Intent(context, FullScreenActivity.class);

        intent.putExtra(TransparentActivity.EXTRAS_CLASS_NAME, fragment.getClass()
                .getName());
        intent.putExtra(TransparentActivity.EXTRAS_BUNDLE, bundle);

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
