package com.jack.mc.cyg.cygproject.cyg.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jack.mc.cyg.cygproject.cyg.util.CygLog;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class CygBaseAdapter<T> extends BaseAdapter {

    protected abstract View getView(int position, View convertView, ViewGroup parent, T data);

    ////////////////////////////////////////////////////////////////////////////////////////////////

    protected CygBaseAdapter(boolean isDataListReferenceMode) {
        mIsDataListReferenceMode = isDataListReferenceMode;
    }

    protected CygBaseAdapter() {
        this(true);
    }

    private final boolean mIsDataListReferenceMode;
    private List<T> mDataList;

    public List<T> getDataList() {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        return mDataList;
    }

    public void setDataList(List<T> dataList) {
        if (mIsDataListReferenceMode) {
            mDataList = dataList;
        } else {
            if (mDataList == null) {
                mDataList = new ArrayList<>();
            } else {
                mDataList.clear();
            }
            if (dataList != null) {
                mDataList.addAll(dataList);
            }
        }
        notifyDataSetChanged();
    }

    public T getData(int position) {
        return mDataList.get(position);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public int getCount() {
        if (mDataList == null) {
            return 0;
        }
        return mDataList.size();
    }

    @Override
    public T getItem(int position) {
        if (mDataList == null) {
            return null;
        }
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        try {
            T data = null;
            if (mDataList != null && mDataList.size() > position) {
                data = mDataList.get(position);
            }
            return getView(position, convertView, parent, data);
        } catch (Exception e) {
            CygLog.error(e);
        }
        return null;
    }
}
