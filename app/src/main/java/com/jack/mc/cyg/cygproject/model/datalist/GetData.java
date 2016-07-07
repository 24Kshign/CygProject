package com.jack.mc.cyg.cygproject.model.datalist;

import com.jack.mc.cyg.cygproject.cyg.callback.RzCallback;
import com.loopj.android.http.RequestParams;

/**
 * Created by Jack on 16/7/7.
 */
public interface GetData {

    void httpPost(RequestParams params, RzCallback<TestListBean> callback);

}