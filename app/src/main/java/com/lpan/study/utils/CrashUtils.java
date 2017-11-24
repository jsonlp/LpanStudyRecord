package com.lpan.study.utils;

import android.content.Context;
import android.os.Environment;

import com.lpan.study.context.AppContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lpan on 2017/11/24.
 */

public class CrashUtils {

    private static boolean checkSDCard() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return true;
        } else {
            return false;
        }
    }

    public static void crashLog(Throwable ex) {

        String exceptionStr = "\r\n====Log:"
                + new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date()) + "====\r\n"
                + getExceptionInfo(ex) + "\r\n";

        FileOutputStream outStream = null;
        try {

            if (checkSDCard()) {
                File file = new File(Environment.getExternalStorageDirectory().getPath()
                        + "/PandaCrashlog.txt");
                if (!file.exists()) {
                    file.createNewFile();
                }
                outStream = new FileOutputStream(file, true);
            } else {
                outStream = AppContext.getContext().openFileOutput("PandaCrashlog.txt",
                        Context.MODE_APPEND);
            }

            outStream.write(exceptionStr.getBytes());
            outStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (outStream != null) {
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static String getErrorInfoFromException(Exception e) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return sw.toString();
        } catch (Exception e2) {
            return "bad getErrorInfoFromException";
        }
    }

    private static String getExceptionInfo(Throwable ex) {
        Writer writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        ex.printStackTrace(pw);
        pw.close();
        String error = writer.toString();
        return error;
    }
}
