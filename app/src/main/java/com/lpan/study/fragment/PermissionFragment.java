package com.lpan.study.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.lpan.R;
import com.lpan.study.fragment.base.ButterKnifeFragment;

import butterknife.OnClick;

/**
 * Created by lpan on 2017/11/28.
 */

public class PermissionFragment extends ButterKnifeFragment implements View.OnClickListener {

    public static final int PERMISSION_CODE = 1;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_permission;
    }


    @OnClick({R.id.text1})
    @Override
    public void onClick(View v) {
        readContacts();
    }

    private void readContacts() {

        if (ContextCompat.checkSelfPermission(PermissionFragment.this.getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            toastLong("try to get permission");
            ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_CODE);
        } else {

            callPhone();
            toastLong("already had permission");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                toastShort("allow");
                callPhone();

            } else {
                toastShort("refuse");
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

    private void callPhone(){
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + "10086");
        intent.setData(data);
        startActivity(intent);
    }
}
