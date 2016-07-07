package com.jack.mc.cyg.cygproject.cyg.util;

/**
 *
 */
public final class CygObjectUtil {

    private CygObjectUtil() {
    }

    public static int hashCode(Object o) {
        if (o == null) {
            return 0;
        }
        return o.hashCode();
    }

    public static boolean equals(Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        }
        if (o1 == null || o2 == null) {
            return  false;
        }
        return o1.equals(o2) && o2.equals(o1);
    }
}
