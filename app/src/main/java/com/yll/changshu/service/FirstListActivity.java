package com.yll.changshu.service;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.yll.changshu.R;

public class FirstListActivity extends AppCompatActivity {
    private int user_id;
    private int user_rank;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_list);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Bundle bundle = getIntent().getExtras();
        user_id = bundle.getInt("user_id");
        user_rank = bundle.getInt("user_rank");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void OnQianyinClick(View v){
        Bundle bundle = new Bundle();
        bundle.putInt("user_id", user_id);
        bundle.putInt("user_rank", user_rank);
        bundle.putInt("node_id", 1);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(FirstListActivity.this, SecondListActivity.class);
        FirstListActivity.this.startActivity(intent);

    }

    public void OnYajieClick(View v){

    }
}
