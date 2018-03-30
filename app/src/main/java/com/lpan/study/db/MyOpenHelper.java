package com.lpan.study.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.lpan.study.greendao.DaoMaster;
import com.lpan.study.greendao.FriendDao;
import com.lpan.study.greendao.StudentDao;

import org.greenrobot.greendao.database.Database;

/**
 * Created by lpan on 2018/3/29.
 */

public class MyOpenHelper extends DaoMaster.OpenHelper {
    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        ////操作数据库的更新 有几个表升级都可以传入到下面

        MigrationHelper.getInstance().migrate(db,StudentDao.class);
        MigrationHelper.getInstance().migrate(db,FriendDao.class);

    }
}
