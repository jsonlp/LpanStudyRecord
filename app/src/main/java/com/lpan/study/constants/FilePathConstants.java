package com.lpan.study.constants;

import android.os.Environment;

import com.lpan.study.context.AppContext;


/**
 * Created by lpan on 2017/11/22.
 */

public class FilePathConstants {


    //      PATH=/storage/emulated/0
    public static final String EXTERNAL_STORAGE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath();

    //      PATH = /data/user/0/com.lpan/cache
    public static final String INTERNAL_CACHE_STORAGE_DIR = AppContext.getContext().getCacheDir().getAbsolutePath();

    //      PATH = /data/user/0/com.lpan/files
    public static final String INTERNAL_FILES_STORAGE_DIR = AppContext.getContext().getFilesDir().getAbsolutePath();

    public static final String PANDA_TEST_DIR = EXTERNAL_STORAGE_DIR + "/a_panda/";

}
