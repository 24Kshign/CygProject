package com.jack.mc.cyg.cygproject.cyg.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jack on 16/5/28.
 */
public final class CygActivityUtil {

    private CygActivityUtil(){}

    public static View layoutInflate(Activity activity, int layoutResource) {
        return activity.getLayoutInflater().inflate(layoutResource, null);
    }

    public static View getRootView(Activity activity) {
        return ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
    }

    //startActivity
    public static final int DEFAULT_FLAGS = 0;
    public static void startActivity(Context context, Class<? extends Activity> cls, Bundle bundle, int flags) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setFlags(flags);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, Class<? extends Activity> cls, Bundle bundle) {
        startActivity(context, cls, bundle, DEFAULT_FLAGS);
    }

    public static void startActivity(Context context, Class<? extends Activity> cls, int flags) {
        startActivity(context, cls, null, flags);
    }

    public static void startActivity(Context context, Class<? extends Activity> cls) {
        startActivity(context, cls, null, DEFAULT_FLAGS);
    }



    //Fragment startActivity
    public static void startActivity(Fragment fragment, Class<? extends Activity> cls, Bundle bundle, int flags) {
        startActivity(fragment.getActivity(), cls, bundle, flags);
    }

    public static void startActivity(Fragment fragment, Class<? extends Activity> cls, Bundle bundle) {
        startActivity(fragment.getActivity(), cls, bundle, DEFAULT_FLAGS);
    }

    public static void startActivity(Fragment fragment, Class<? extends Activity> cls, int flags) {
        startActivity(fragment.getActivity(), cls, null, flags);
    }

    public static void startActivity(Fragment fragment, Class<? extends Activity> cls) {
        startActivity(fragment.getActivity(), cls, null, DEFAULT_FLAGS);
    }

    public static void startActivityForResult(Activity activity, Class<? extends Activity> cls, Bundle bundle, int flags, int requestCode) {
        Intent intent = new Intent(activity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setFlags(flags);

        activity.startActivityForResult(intent, requestCode);
    }

    public static void startActivityForResult(Activity activity, Class<? extends Activity> cls, Bundle bundle, int requestCode) {
        startActivityForResult(activity, cls, bundle, DEFAULT_FLAGS, requestCode);
    }

    public static void startActivityForResult(Activity activity, Class<? extends Activity> cls, int flags, int requestCode) {
        startActivityForResult(activity, cls, null, flags, requestCode);
    }

    public static void startActivityForResult(Activity activity, Class<? extends Activity> cls, int requestCode) {
        startActivityForResult(activity, cls, null, DEFAULT_FLAGS, requestCode);
    }


}