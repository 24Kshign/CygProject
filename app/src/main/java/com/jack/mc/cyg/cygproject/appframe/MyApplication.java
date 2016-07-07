package com.jack.mc.cyg.cygproject.appframe;

import com.jack.mc.cyg.cygproject.cyg.app.CygApplication;

/**
 * Created by Jack on 16/7/7.
 */
public class MyApplication extends CygApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        AppFramework.init();
    }
}
