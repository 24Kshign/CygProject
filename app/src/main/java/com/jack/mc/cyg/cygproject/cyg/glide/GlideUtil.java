package com.jack.mc.cyg.cygproject.cyg.glide;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jack.mc.cyg.cygproject.cyg.drawableresource.CygDrawableResource;


/**
 *
 */
public final class GlideUtil {

    private GlideUtil() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static void setDrawable(ImageView imageView, CygDrawableResource drawableResource) {
        if (imageView==null) {
            return;
        }
        if (drawableResource==null) {
            return;
        }
        switch (drawableResource.getType()) {
            case Integer:
                Glide.with(imageView.getContext()).load(drawableResource.getInteger()).into(imageView);
                break;
            case File:
                Glide.with(imageView.getContext()).load(drawableResource.getFile()).into(imageView);
                break;
            case URL:
                Glide.with(imageView.getContext()).load(drawableResource.getURL()).into(imageView);
                break;
            case Uri:
                Glide.with(imageView.getContext()).load(drawableResource.getUri()).into(imageView);
                break;
            case String:
                Glide.with(imageView.getContext()).load(drawableResource.getString()).into(imageView);
                break;
            case INVALID:
                break;
            default:
                break;
        }
    }
}
