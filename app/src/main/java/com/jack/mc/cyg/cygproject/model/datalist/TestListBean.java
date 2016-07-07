package com.jack.mc.cyg.cygproject.model.datalist;

import java.util.List;

/**
 * Created by Jack on 16/6/16.
 */
public class TestListBean {

    private String status;
    private String msg;
    private List<BookInfo> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<BookInfo> getData() {
        return data;
    }

    public void setData(List<BookInfo> data) {
        this.data = data;
    }
}