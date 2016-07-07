package com.jack.mc.cyg.cygproject.cyg.util;

import android.support.annotation.Nullable;
import android.widget.EditText;

/**
 * 字符操作的辅助类
 */
public final class CygStringUtil {

    private CygStringUtil() {
    }

    public static String EMPTY_STRING = "";

    public static String valueOf(Object value) {
        return value != null ? value.toString() : EMPTY_STRING;
    }

    public static boolean isEmpty(@Nullable String string) {
        return string == null || string.isEmpty();
    }

    public static boolean isEmpty(String... strings) {
        for (String str : strings) {
            if (str == null || str.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static String getEditTextContent(EditText editText) {
        if (editText == null) {
            return null;
        } else {
            return editText.getText().toString().trim();
        }
    }
}
