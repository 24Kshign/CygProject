package com.jack.mc.cyg.cygproject.view.main;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jack.mc.cyg.cygproject.R;
import com.jack.mc.cyg.cygproject.cyg.callback.RzCallback;
import com.jack.mc.cyg.cygproject.cyg.os.CygMainLooperHandler;
import com.jack.mc.cyg.cygproject.cyg.util.CygLog;
import com.jack.mc.cyg.cygproject.cyg.util.CygToast;
import com.jack.mc.cyg.cygproject.model.datalist.TestListBean;
import com.jack.mc.cyg.cygproject.presenter.datalist.DataListPresenter;
import com.jack.mc.cyg.cygptr.PtrClassicFrameLayout;
import com.jack.mc.cyg.cygptr.PtrDefaultHandler;
import com.jack.mc.cyg.cygptr.PtrFrameLayout;
import com.jack.mc.cyg.cygptr.loadmore.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    PtrClassicFrameLayout ptrClassicFrameLayout;
    ListView mListView;
    private List<String> mData = new ArrayList<String>();
    private ListViewAdapter mAdapter;

    int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ptrClassicFrameLayout = (PtrClassicFrameLayout) this.findViewById(R.id.test_list_view_frame);
        mListView = (ListView) this.findViewById(R.id.test_list_view);
        initData();
    }

    private void initData() {
        mAdapter = new ListViewAdapter(this, mData);
        mListView.setAdapter(mAdapter);

        DataListPresenter.getInstance().execute(new RzCallback<TestListBean>() {
            @Override
            public void onSuccess(TestListBean data) {
                CygLog.debug("data.sixe()------->" + data.getData().size());
            }

            @Override
            public void onFailure(String failure) {

            }
        });

        ptrClassicFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptrClassicFrameLayout.autoRefresh(true);
            }
        }, 150);

        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                CygMainLooperHandler.getInstance().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 0;
                        mData.clear();
                        for (int i = 0; i < 17; i++) {
                            mData.add(new String("  ListView item  -" + i));
                        }
                        mAdapter.notifyDataSetChanged();
                        ptrClassicFrameLayout.refreshComplete();
                        ptrClassicFrameLayout.setLoadMoreEnable(true);
                    }
                }, 1000);
            }
        });

        ptrClassicFrameLayout.setOnLoadMoreListener(new OnLoadMoreListener() {

            @Override
            public void loadMore() {
                CygMainLooperHandler.getInstance().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mData.add(new String("  ListView item  - add " + page));
                        mAdapter.notifyDataSetChanged();
                        ptrClassicFrameLayout.loadMoreComplete(true);
                        page++;
                        CygToast.showToast("load more complete");
                    }
                }, 1000);
            }
        });
    }

    public class ListViewAdapter extends BaseAdapter {
        private List<String> datas;
        private LayoutInflater inflater;

        public ListViewAdapter(Context context, List<String> data) {
            super();
            inflater = LayoutInflater.from(context);
            datas = data;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.ptr_item_list, parent, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(datas.get(position));
            return convertView;
        }

        public List<String> getData() {
            return datas;
        }

    }
}
