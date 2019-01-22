package com.lpan.study.utils;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;


/**
 * Created by lpan on 2019/1/22.
 */
public class PermissionUtils {

    public static final int PERMISSION_CODE = 1;


    public static void requestPermission(String[] permissions, Fragment fragment, int requestCode) {
        if (!hasPermission(fragment.getActivity(), permissions[0])) {
            if (fragment.shouldShowRequestPermissionRationale(permissions[0])) {
                AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());
                builder.setTitle("permission rationale")
                        .setMessage("we need your contact to help you find more friend.")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                fragment.requestPermissions(permissions, requestCode);
                            }
                        })
                        .setNegativeButton("cancel", null)
                        .show();
            } else {
                fragment.requestPermissions(permissions, requestCode);
            }
        }
    }

    public static boolean hasPermission(Activity activity, String permissionName) {
        return ContextCompat.checkSelfPermission(activity, permissionName) == PackageManager.PERMISSION_GRANTED;
    }
}
