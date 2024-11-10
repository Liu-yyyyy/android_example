package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SwipeRefreshLayoutActivity extends AppCompatActivity {

    private ListView mRefreshListView;
    private SwipeRefreshLayout mSwipeRefresh;
    private ArrayList<String> mItems;
    private BaseAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout);
        mRefreshListView = findViewById(R.id.lv_refresh);
        mSwipeRefresh = findViewById(R.id.id_refreshLayout);

        initData();
        mAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return mItems.size();
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
                TextView textView = (TextView) LayoutInflater.from(SwipeRefreshLayoutActivity.this)
                        .inflate(R.layout.item_listview,parent,false);
                textView.setText(mItems.get(position));
                return textView;
            }
        };
        mRefreshListView.setAdapter(mAdapter);

        // 在这里设置下拉的相关属性
        setSwipeRefresh();
    }

    private void setSwipeRefresh() {
        // 设置进度条的颜色，不定长参数可以设置多种颜色
        // 对于RefreshLayout，网上说最多四种颜色，不用使用android.R.color,否则会卡死；
        // 这里的颜色就是下拉转圈圈显示的颜色
        mSwipeRefresh.setColorSchemeColors(Color.BLUE);

        //设置进度条的背景颜色
        mSwipeRefresh.setProgressBackgroundColorSchemeColor(Color.WHITE);
        // 设置大小
        mSwipeRefresh.setSize(SwipeRefreshLayout.DEFAULT);
        // 设置手指划过多少个像素开始出发刷新
        mSwipeRefresh.setDistanceToTriggerSync(100);
        // 设置下拉监听事件
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 新开线程来执行
                // 这里使用handler的postDelayed函数，就是先睡眠1秒钟然后再执行run函数里面的代码；
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 下拉的刷新处理
                        refreshData();
                        // 结束后停止刷新
                        mSwipeRefresh.setRefreshing(false);
                        // 关闭下拉刷新
                        mSwipeRefresh.setRefreshing(false);
                    }
                },1000);
            }
        });
    }

    private void refreshData() {
        // 在这里添加下拉刷新显示的数据
        for (int i = 0; i < 10; i++) {
            mItems.add("新添数据：i -> " + i);
        }

        // 在这里要执行适配器更新的操作
        mAdapter.notifyDataSetChanged();
    }

    private void initData() {
        mItems = new ArrayList<>();
        mItems.add("春");
        mItems.add("夏");
        mItems.add("秋");
        mItems.add("冬");
    }
}