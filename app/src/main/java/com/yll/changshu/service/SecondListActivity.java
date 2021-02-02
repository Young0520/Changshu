package com.yll.changshu.service;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.yll.changshu.Adapter.ToolNameAdapter;
import com.yll.changshu.R;
import com.yll.changshu.dao.ToolNameDao;
import com.yll.changshu.entity.ToolName;

import java.util.List;

public class SecondListActivity extends AppCompatActivity {
    private ListView listview;
    private int user_id;
    private int user_rank;
    private int father_id;
    private List<ToolName> toolNameList;
    private ToolNameAdapter toolNameAdapter;
    private Handler handler;

    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    toolNameAdapter = new ToolNameAdapter(SecondListActivity.this, toolNameList);
                    listview.setAdapter(toolNameAdapter);
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            Intent intent=new Intent();
                            Bundle bundle=new Bundle();
                            ToolName tn = (ToolName)view.getTag();
                            bundle.putInt("toolname_id", tn.getNode_name_id());
                            bundle.putInt("user_id",user_id);
                            bundle.putInt("node_id",father_id);
                            bundle.putInt("user_rank",user_rank);
                            intent.putExtras(bundle);
                            intent.setClass(SecondListActivity.this, ToolListActivity.class);
                            SecondListActivity.this.startActivityForResult(intent,1);
                        }
                    });
                    break;
                default:
                    break;
            }
        }
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

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_list);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Bundle bundle = getIntent().getExtras();
        user_id = bundle.getInt("user_id");
        user_rank = bundle.getInt("user_rank");
        father_id = bundle.getInt("node_id");
        listview = (ListView) findViewById(R.id.second_list);
        handler = new SecondListActivity.MyHandler();
        getListData();



    }

    private void getListData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Message msg = handler.obtainMessage();
                    ToolNameDao toolNameDao = ToolNameDao.getToolNameDao();
                    toolNameList = toolNameDao.getToolNameByFather_id(father_id);
                    msg.what = 0;
                    handler.sendMessage(msg);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
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
//                    Toast.makeText(SecondListActivity.this, "返回1", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }
}
