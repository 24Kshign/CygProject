package com.jack.mc.cyg.cygproject.appframe;


import com.jack.mc.cyg.cygproject.cyg.app.HttpServletAddress;

/**
 * Created by Jack on 16/5/29.
 */
public class AppFramework {

    private AppFramework() {
    }

    public static void init() {
        // Http服务器地址
        HttpServletAddress.getInstance().setOnlineAddress("http://www.imooc.com/api");
    }
}
