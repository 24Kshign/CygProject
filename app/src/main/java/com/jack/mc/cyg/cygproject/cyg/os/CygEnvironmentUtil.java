package com.jack.mc.cyg.cygproject.cyg.os;

import android.os.Environment;

import com.jack.mc.cyg.cygproject.cyg.app.CygApplication;
import com.jack.mc.cyg.cygproject.cyg.util.CygLog;
import com.jack.mc.cyg.cygproject.cyg.util.CygStringUtil;

import java.io.File;

/**
 *
 */
public final class CygEnvironmentUtil {

    private CygEnvironmentUtil() {
    }

    public static boolean hasExternalStorage() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static String getExternalFilesPath() {
        if (!CygEnvironmentUtil.hasExternalStorage()) {
            CygLog.error("没有SDK卡！");
            return CygStringUtil.EMPTY_STRING;
        }
        try {
            File file = CygApplication.getInstance().getExternalFilesDir(null);
            if (file == null) {
                CygLog.error();
                return CygStringUtil.EMPTY_STRING;
            }
            return file.getCanonicalPath();
        } catch (Exception e) {
            CygLog.error(e);
        }
        return CygStringUtil.EMPTY_STRING;
    }
}
