package com.example.myapplication.CommonViewActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;

@SuppressLint("MissingInflatedId")
public class TestRecyclerView02Activity extends AppCompatActivity {
    // 这里用来显示RecyclerView的
    private RecyclerView mRecyclerView;
    private ArrayList<String> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_recycler_view02);
        mRecyclerView = findViewById(R.id.id_recycleView02);
        initData();
        // 指定显示的样式 这里可以指定是按每条竖着摆放还是按每个格子进行摆放，从这个参数就可以看出这个RecyclerView可以实现ListView和GridView的效果；
        //mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 和ListView一样，RecyclerView也需要搭配Adapter使用，但是这里的Adapter和ListView的不太一样，这里要搭配ViewHolder使用
        mRecyclerView.setAdapter(new RecyclerAdapter());
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mData.add("" + i);
        }
    }

    /**
     * 列表适配器
     */
    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // 创建ViewHolder
            View itemView = LayoutInflater.from(TestRecyclerView02Activity.this)
                    .inflate(R.layout.item_home,parent,false);

            // 这里生成ViewHolder需要一些itemView，所以在上面才会写inflate的代码
            ViewHolder viewHolder = new ViewHolder(itemView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            // 绑定数据
            holder.itemTV.setText(mData.get(position));

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView itemTV;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                itemTV = itemView.findViewById(R.id.id_num);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_action_gridview:
                // 根据选择的样式来设定
                mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
                break;
            case R.id.id_action_listview:
                // 根据选择的样式来设定
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recyclerview,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
