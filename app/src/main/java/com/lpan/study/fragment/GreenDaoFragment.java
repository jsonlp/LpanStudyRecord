package com.lpan.study.fragment;

import android.view.View;
import android.widget.TextView;

import com.lpan.R;
import com.lpan.study.db.StudentDaoManager;
import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.db.entity.Student;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lpan on 2018/3/29.
 */

public class GreenDaoFragment extends ButterKnifeFragment {


    @BindView(R.id.green_dao_insert_tv) TextView mGreenDaoInsertTv;
    @BindView(R.id.green_dao_delete_tv) TextView mGreenDaoDeleteTv;
    @BindView(R.id.green_dao_update_tv) TextView mGreenDaoUpdateTv;
    @BindView(R.id.green_dao_query_tv) TextView mGreenDaoQueryTv;
    @BindView(R.id.desc) TextView mDesc;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_green_dao;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
    }

    @OnClick({R.id.green_dao_insert_tv, R.id.green_dao_delete_tv, R.id.green_dao_update_tv, R.id.green_dao_query_tv, R.id.desc,
            R.id.green_dao_insert_all_tv, R.id.green_dao_delete_all_tv, R.id.green_dao_query_all_tv,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.green_dao_insert_tv:
                Student student1 = new Student(95227, "01229270", "李四", "女", "22", 168);
                StudentDaoManager.insert(student1);

                break;
            case R.id.green_dao_delete_tv:
                StudentDaoManager.deletebyName("李四");

                break;
            case R.id.green_dao_query_tv:
                List<Student> students1 = StudentDaoManager.querybyName("李四");
                setText(students1);
                break;

            case R.id.green_dao_insert_all_tv:
                List<Student> list = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    Student student = new Student((i + 1020), "020" + (i + 2010), "张小" + i, "男", "" + (i + 20), 188);
                    list.add(student);
                }
                StudentDaoManager.insertAll(list);

                break;
            case R.id.green_dao_delete_all_tv:

                break;
            case R.id.green_dao_query_all_tv:
                List<Student> students = StudentDaoManager.queryAll();
                setText(students);
                break;
            case R.id.green_dao_update_tv:
                StudentDaoManager.update();
                break;
        }
    }


    private void setText(List<Student> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Student student : list) {
            stringBuilder.append(student.toString());
            stringBuilder.append("\n");
        }
        mDesc.setText(stringBuilder.toString());
    }
}
