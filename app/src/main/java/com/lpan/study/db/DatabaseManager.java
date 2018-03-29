package com.lpan.study.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.lpan.study.context.GreenDaoContext;
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

    private Context mContext;

    private DatabaseManager(Context context) {
        this.mContext = context;
        mDevOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        getDaoMaster(context);
        getDaoSession(context);
    }

    public static DatabaseManager getInstance(Context context) {
        if (mDatabaseManager == null) {
            synchronized (DatabaseManager.class) {
                if (mDatabaseManager == null) {
                    mDatabaseManager = new DatabaseManager(context);
                }
            }
        }
        return mDatabaseManager;
    }

    /**
     * 获取DaoMaster
     *
     * @param context
     * @return
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (null == mDaoMaster) {
            synchronized (DatabaseManager.class) {
                if (null == mDaoMaster) {
                    mDaoMaster = new DaoMaster(getWritableDatabase(context));
                }
            }
        }
        return mDaoMaster;
    }

    /**
     * 获取DaoSession
     *
     * @param context
     * @return
     */
    public static DaoSession getDaoSession(Context context) {
        if (null == mDaoSession) {
            synchronized (DatabaseManager.class) {
                mDaoSession = getDaoMaster(context).newSession();
            }
        }

        return mDaoSession;
    }

    /**
     * 获取可读数据库
     *
     * @param context
     * @return
     */
    public static SQLiteDatabase getReadableDatabase(Context context) {
        if (null == mDevOpenHelper) {
            getInstance(context);
        }
        return mDevOpenHelper.getReadableDatabase();
    }

    /**
     * 获取可写数据库
     *
     * @param context
     * @return
     */
    public static SQLiteDatabase getWritableDatabase(Context context) {
        if (null == mDevOpenHelper) {
            getInstance(context);
        }

        return mDevOpenHelper.getWritableDatabase();
    }

}
