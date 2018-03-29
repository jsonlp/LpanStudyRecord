package com.lpan.study.fragment;

import android.view.View;
import android.widget.TextView;

import com.lpan.R;
import com.lpan.study.context.GreenDaoContext;
import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.greendao.DaoMaster;
import com.lpan.study.greendao.DaoSession;
import com.lpan.study.greendao.StudentDao;
import com.lpan.study.model.Student;

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

    private DaoMaster mDaoMaster;

    private DaoSession mDaoSession;

    private StudentDao mStudentDao;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_green_dao;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        getStuDao();
    }

    private void getStuDao() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(new GreenDaoContext(), "student.db", null);
        mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
        mStudentDao = mDaoSession.getStudentDao();
    }


    @OnClick({R.id.green_dao_insert_tv, R.id.green_dao_delete_tv, R.id.green_dao_update_tv, R.id.green_dao_query_tv, R.id.desc,
            R.id.green_dao_insert_all_tv, R.id.green_dao_delete_all_tv, R.id.green_dao_query_all_tv,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.green_dao_insert_tv:
                insert();

                break;
            case R.id.green_dao_delete_tv:
                delete();
                break;
            case R.id.green_dao_query_tv:
                queryWhere();
                break;

            case R.id.green_dao_insert_all_tv:
                insertAll();

                break;
            case R.id.green_dao_delete_all_tv:

                break;
            case R.id.green_dao_query_all_tv:
                queryAll();
                break;
            case R.id.green_dao_update_tv:
                update();
                break;
        }
    }

    private void insert() {
        Student student = new Student(95227, "01229270", "李四", "女", "22",168);
        mStudentDao.insert(student);
        toastShort("insert success");
    }

    private void insertAll() {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student((i + 1020), "020" + (i + 2010), "张小" + i, "男", "" + (i + 20),188);
            list.add(student);
        }
        mStudentDao.insertInTx(list);
        toastShort("insert success");
    }

    private void queryAll() {
        List<Student> list = mStudentDao.queryBuilder().list();
        setText(list);
    }

    private void queryWhere() {
        List<Student> students = mStudentDao.queryBuilder().where(StudentDao.Properties.StuName.eq("张小3")).list();
        setText(students);
    }


    private void delete() {
        mStudentDao.queryBuilder().where(StudentDao.Properties.StuSex.eq("女")).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    private void update() {
        Student student = mStudentDao.queryBuilder().where(StudentDao.Properties.StuName.eq("李四")).build().unique();
        student.setStuName("更名李五");
        mStudentDao.update(student);
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
