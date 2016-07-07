package com.jack.mc.cyg.cygproject.cyg.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.TypeUtils;
import com.jack.mc.cyg.cygproject.cyg.util.CygLog;
import com.jack.mc.cyg.cygproject.cyg.util.CygStringUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 对fastjson的封装
 */
public class FastJSON {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // 转成Java对象

    public static <T> T jsonStrToJavaObj(String string, Class<T> cls) {
        try {
            if (CygStringUtil.isEmpty(string)) {
                CygLog.error();
                return null;
            }
            return JSON.parseObject(string, cls);
        } catch (Exception e) {
            CygLog.error(e);
        }
        return null;
    }

    public static <T> T jsonObjToJavaObj(JSONObject jsonObj, Class<T> cls) {
        return castToJavaObj(jsonObj, cls);
    }

    public static <T> T toJavaObj(Object object, Class<T> cls) {
        if (object instanceof String) {
            return jsonStrToJavaObj((String) object, cls);
        } else {
            return castToJavaObj(object, cls);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // 转成Java对象列表

    public static <T> List<T> jsonStrToJavaObjList(String str, Class<T> cls) {
        try {
            if (CygStringUtil.isEmpty(str)) {
                CygLog.error();
                return null;
            }
            return JSON.parseArray(str, cls);
        } catch (Exception e) {
            CygLog.error(e);
        }
        return null;
    }

    public static <T> List<T> toJavaObjList(List list, Class<T> cls) {
        try {
            if (list == null) {
                return null;
            }
            if (list.isEmpty()) {
                return new ArrayList<>();
            }
            List<T> javaObjList = new ArrayList<>();
            for (Object object : list) {
                T javaObj = toJavaObj(object, cls);
                if (javaObj != null) {
                    javaObjList.add(javaObj);
                }
            }
            if (javaObjList.isEmpty()) {
                CygLog.error();
                return null;
            }
            return javaObjList;
        } catch (Exception e) {
            CygLog.error(e);
        }
        return null;
    }

    public static <T> List<T> toJavaObjList(Object object, Class<T> cls) {
        if (object instanceof List) {
            return toJavaObjList((List) object, cls);
        } else {
            return null;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // 转成JSON字符串

    public static String javaObjToJsonStr(Object obj) {
        try {
            if (obj == null) {
                return CygStringUtil.EMPTY_STRING;
            }
            return JSON.toJSONString(obj);
        } catch (Exception e) {
            CygLog.error(e);
        }
        return CygStringUtil.EMPTY_STRING;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // private

    private static <T> T castToJavaObj(Object object, Class<T> cls) {
        try {
            if (object == null) {
                return null;
            }
            return TypeUtils.cast(object, cls, ParserConfig.getGlobalInstance());
        } catch (Exception e) {
            CygLog.error(e);
        }
        return null;
    }
}
