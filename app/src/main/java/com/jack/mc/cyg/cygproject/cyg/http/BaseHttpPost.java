package com.jack.mc.cyg.cygproject.cyg.http;


import com.jack.mc.cyg.cygproject.cyg.app.HttpServletAddress;

/**
 * Created by Jack on 16/7/6.
 */
public abstract class BaseHttpPost {

    protected abstract String getUri();

    protected String getDomain() {
        return HttpServletAddress.getInstance().getAddress();
    }

    protected String getUrl() {
        return getDomain() + getUri();
    }
}
