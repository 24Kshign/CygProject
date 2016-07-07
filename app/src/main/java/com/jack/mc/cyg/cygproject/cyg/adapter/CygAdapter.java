package com.jack.mc.cyg.cygproject.cyg.adapter;

import android.app.Activity;

/**
 *
 */
public abstract class CygAdapter<T> extends CygBaseAdapter<T> {

    public CygAdapter(Activity activity, boolean isDataListReferenceMode) {
        super(isDataListReferenceMode);
        mActivity = activity;
    }

    public CygAdapter(Activity activity) {
        mActivity = activity;
    }

    private final Activity mActivity;

    protected Activity getActivity() {
        return mActivity;
    }
}
