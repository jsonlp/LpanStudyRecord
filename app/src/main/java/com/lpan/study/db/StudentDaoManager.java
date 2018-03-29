package com.lpan.study.db;


import com.lpan.study.greendao.StudentDao;
import com.lpan.study.db.entity.Student;

import java.util.List;

/**
 * Created by lpan on 2018/3/29.
 */

public class StudentDaoManager {


    public static void insert(Student student) {
        DatabaseManager.getDaoSession().getStudentDao().insert(student);
    }

    public static void insertAll(List<Student> students) {
        DatabaseManager.getDaoSession().getStudentDao().insertInTx(students);
    }

    public static List<Student> queryAll() {
        return DatabaseManager.getDaoSession().getStudentDao().queryBuilder().list();
    }

    public static List<Student> querybyName(String name) {
        return DatabaseManager.getDaoSession().getStudentDao().queryBuilder()
                .where(StudentDao.Properties.StuName.eq(name)).list();
    }


    public static void deletebyName(String name) {
        DatabaseManager.getDaoSession().getStudentDao().queryBuilder()
                .where(StudentDao.Properties.StuName.eq(name)).buildDelete()
                .executeDeleteWithoutDetachingEntities();
    }

    public static void update() {
        Student student = DatabaseManager.getDaoSession().getStudentDao().queryBuilder().where(StudentDao.Properties.StuName.eq("李四")).build().unique();
        student.setStuName("更名李五");
        DatabaseManager.getDaoSession().getStudentDao().update(student);
    }
}
