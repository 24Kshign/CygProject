package com.jack.mc.cyg.cygproject.presenter.datalist;

import com.jack.mc.cyg.cygproject.cyg.callback.RzCallback;
import com.jack.mc.cyg.cygproject.model.datalist.GetData;
import com.jack.mc.cyg.cygproject.model.datalist.HttpRequestDataList;
import com.jack.mc.cyg.cygproject.model.datalist.TestListBean;

/**
 * Created by Jack on 16/7/7.
 */
public class DataListPresenter {

    private GetData getData;

    public DataListPresenter() {
        getData = new HttpRequestDataList();
    }

    public void execute(RzCallback<TestListBean> callback) {
        getData.httpPost(null, callback);
    }

    /**
     * 内部类实现单例模式
     * 延迟加载，减少内存开销
     */
    private static class SingletonHolder {
        private static DataListPresenter instance = new DataListPresenter();
    }

    public static DataListPresenter getInstance() {
        return SingletonHolder.instance;
    }
}
