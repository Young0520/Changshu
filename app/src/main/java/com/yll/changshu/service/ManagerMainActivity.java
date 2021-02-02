package com.yll.changshu.service;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.yll.changshu.R;

public class ManagerMainActivity extends AppCompatActivity {

    private int user_rank;
    private int user_id;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_main);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Bundle bundle = getIntent().getExtras();
        user_rank = bundle.getInt("user_rank");
        user_id = bundle.getInt("user_id");

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

    public void OnToolStockQueryClick(View v){
        Bundle bundle = new Bundle();
        bundle.putInt("user_id",user_id);
        bundle.putInt("user_rank", user_rank);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(ManagerMainActivity.this, FirstListActivity.class);
        ManagerMainActivity.this.startActivity(intent);
    }

    public  void OnBorrowAuditClick(View v){
        Bundle bundle = new Bundle();
        bundle.putInt("user_id",user_id);
        bundle.putInt("user_rank", user_rank);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(ManagerMainActivity.this, AuditListActivity.class);
        ManagerMainActivity.this.startActivity(intent);
    }

    public void OnChangePswClick(View v){
        Bundle bundle = new Bundle();
//        bundle.putString("user_name",user_name);
        bundle.putInt("user_id", user_id);
        bundle.putInt("user_rank", user_rank);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(ManagerMainActivity.this, PasswordActivity.class);
        ManagerMainActivity.this.startActivityForResult(intent, 1);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //这里要通过请求码来判断数据的来源
        switch (requestCode) {
            case 1:
                //判断请求的结果是否成功，resultCode == RESULT_OK，代表成功了
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
//                    Toast.makeText(ManagerMainActivity.this, "返回", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }
}
