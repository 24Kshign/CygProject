package com.jack.mc.cyg.cygproject.model.datalist;

import com.jack.mc.cyg.cygproject.cyg.callback.RzCallback;
import com.jack.mc.cyg.cygproject.cyg.fastjson.FastJSON;
import com.jack.mc.cyg.cygproject.cyg.http.BaseHttpPost;
import com.jack.mc.cyg.cygproject.cyg.http.NetCallBack;
import com.jack.mc.cyg.cygproject.cyg.http.RequestUtils;
import com.loopj.android.http.RequestParams;

/**
 * Created by Jack on 16/7/7.
 */
public class HttpRequestDataList extends BaseHttpPost implements GetData{

    @Override
    protected String getUri() {
        return "/teacher?type=4&num=30";
    }


    @Override
    public void httpPost(RequestParams params, final RzCallback<TestListBean> callback) {
        RequestUtils.ClientPost(getUrl(), params, new NetCallBack() {
            @Override
            public void onMySuccess(byte[] response) {
                TestListBean dataObj = FastJSON.jsonStrToJavaObj(new String(response), TestListBean.class);
                if (dataObj == null) {
                    callback.onFailure("请求失败");
                    return;
                }
                callback.onSuccess(dataObj);
            }

            @Override
            public void onMyFailure(byte[] response, Throwable throwable) {
                callback.onFailure("请求失败");
            }
        });
    }
}
