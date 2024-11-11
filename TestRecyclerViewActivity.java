package com.example.myapplication.CommonViewActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
@SuppressLint("MissingInflatedId")
public class TestRecyclerViewActivity extends AppCompatActivity {
    // 这里是用来点击跳转到RecyclerView的
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_recycler_view);

        mTextView = findViewById(R.id.id_recycleView);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击跳转到RecyclerView页面
                Intent intent = new Intent(TestRecyclerViewActivity.this,TestRecyclerView02Activity.class);
                startActivity(intent);
            }
        });
    }
}
