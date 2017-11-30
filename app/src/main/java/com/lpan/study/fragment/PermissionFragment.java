package com.lpan.study.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lpan.R;
import com.lpan.study.adapter.PermissionAdapter;
import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.model.ContactInfo;
import com.lpan.study.task.ScanContactsTask;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lpan on 2017/11/28.
 */

public class PermissionFragment extends ButterKnifeFragment implements View.OnClickListener {

    public static final int PERMISSION_CODE = 1;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private PermissionAdapter mAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_permission;
    }

    @Override
    protected void initData() {
        super.initData();
        mRecyclerView.setLayoutManager(getLayoutManager());
        mRecyclerView.setAdapter(getAdapter());
    }

    private PermissionAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new PermissionAdapter(getActivity());
        }
        return mAdapter;
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @OnClick({R.id.text1})
    @Override
    public void onClick(View v) {
        testPermission();
    }

    private void testPermission() {
        int check = checkPermission(PermissionFragment.this.getActivity(), Manifest.permission.READ_CONTACTS);
        if (check != PackageManager.PERMISSION_GRANTED) {
            toastLong("try to get permission");
            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("permission rationale")
                        .setMessage("we need your contact to help you find more friend.")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSION_CODE);
                            }
                        })
                        .setNegativeButton("cancel", null)
                        .show();
            } else {
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSION_CODE);
            }
        } else {

            readContacts();
            toastLong("already had permission");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                toastShort("allow");
                readContacts();
            } else {
                toastShort("refuse");
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

    private void readContacts() {
        new ScanContactsTask() {
            @Override
            protected List<ContactInfo> doInBackground(Void... params) {
                return super.doInBackground(params);
            }

            @Override
            protected void onPostExecute(List<ContactInfo> contactInfos) {
                super.onPostExecute(contactInfos);
                toastLong("count=" + contactInfos.size());

                getAdapter().clearItem();
                getAdapter().addItem(contactInfos);
                getAdapter().notifyDataSetChanged();
            }
        }.execute();
    }

    public int checkPermission(Activity activity, String permissionName) {
        return ContextCompat.checkSelfPermission(activity, permissionName);
    }
}
