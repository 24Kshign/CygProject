package com.jack.mc.cyg.cygproject.cyg.http;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

/**
 * Author:Created by JackCheng
 * Email:17764576259@163.com
 * Time:2015/12/27 19:03
 * Copyright:1.0
 */

//封装AsyncHttp类
public class RequestUtils {

    public static AsyncHttpClient client = new AsyncHttpClient();

    public static void ClientGet(String url, RequestParams params, NetCallBack ncb) {
        client.get(url, params, ncb);
        client.setTimeout(5000);
    }

    public static void ClientPost(String url, RequestParams params, NetCallBack ncb) {
        client.post(url, params, ncb);
        client.setTimeout(5000);
    }
}
