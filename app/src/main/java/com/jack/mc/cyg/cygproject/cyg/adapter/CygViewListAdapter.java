package com.jack.mc.cyg.cygproject.cyg.adapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * 用于展示少量view
 * 没有 convertView 和 ViewHolder 机制
 * 直接就设置进来view的列表，getView就不需要反复进行数据设置
 */
public class CygViewListAdapter extends CygBaseAdapter<View> {

    public CygViewListAdapter(boolean isDataListReferenceMode) {
        super(isDataListReferenceMode);
    }

    public CygViewListAdapter() {
    }

    @Override
    protected final View getView(int position, View convertView, ViewGroup parent, View data) {
        return data;
    }
}
