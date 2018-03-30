package com.lpan.study.db;

import android.content.Context;

import com.lpan.study.context.AppContext;
import com.lpan.study.greendao.DaoMaster;
import com.lpan.study.greendao.DaoSession;

/**
 * Created by lpan on 2018/3/29.
 */

public class DatabaseManager {

    public static final String DB_NAME = "panda.db";

    private static DatabaseManager mDatabaseManager;

    private static DaoMaster.DevOpenHelper mDevOpenHelper;

    private static DaoMaster mDaoMaster;

    private static DaoSession mDaoSession;
    static Context mContext = new GreenDaoContext();


    private DatabaseManager() {
//        Context context = AppContext.context;
        mDevOpenHelper = new DaoMaster.DevOpenHelper(mContext, DB_NAME);
        getDaoMaster();
        getDaoSession();
    }

    public static DatabaseManager getInstance() {
        if (mDatabaseManager == null) {
            synchronized (DatabaseManager.class) {
                if (mDatabaseManager == null) {
                    mDatabaseManager = new DatabaseManager();
                }
            }
        }
        return mDatabaseManager;
    }

    public static DaoMaster getDaoMaster() {
        if (null == mDaoMaster) {
            synchronized (DatabaseManager.class) {
                if (null == mDaoMaster) {
                    MyOpenHelper helper = new MyOpenHelper(mContext,DB_NAME,null);
                    mDaoMaster = new DaoMaster(helper.getWritableDatabase());
                }
            }
        }
        return mDaoMaster;
    }

    public static DaoSession getDaoSession() {
        if (null == mDaoSession) {
            synchronized (DatabaseManager.class) {
                mDaoSession = getDaoMaster().newSession();
            }
        }

        return mDaoSession;
    }

}
