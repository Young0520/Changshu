package com.yll.changshu.service;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.yll.changshu.Adapter.BorrowListAdpter;
import com.yll.changshu.Adapter.BorrowToolAdapter;
import com.yll.changshu.R;
import com.yll.changshu.dao.BorrowListDao;
import com.yll.changshu.dao.ToolNameDao;
import com.yll.changshu.dao.UserDao;
import com.yll.changshu.entity.BorrowList;
import com.yll.changshu.entity.BorrowTool;
import com.yll.changshu.entity.User;
import com.yll.changshu.util.pubFun;

import java.util.List;

public class ApplyListActivity extends AppCompatActivity {

    private List<BorrowList> borrowLists;
    private ListView listView;
    private int user_id;
    private BorrowListAdpter borrowListAdpter;
    private Handler handler;

    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    if(borrowLists.size() == 0){
                        Toast.makeText(ApplyListActivity.this, "暂无借用申请记录", Toast.LENGTH_SHORT).show();
                    } else {
                        borrowListAdpter = new BorrowListAdpter(ApplyListActivity.this, borrowLists);
                        listView.setAdapter(borrowListAdpter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view,
                                                    int position, long id) {
                                Intent intent=new Intent();
                                Bundle bundle=new Bundle();
                                BorrowList bl = (BorrowList) view.getTag();
                                bundle.putInt("list_id",bl.getList_id());
                                bundle.putInt("user_id",user_id);
                                bundle.putString("corp_name", bl.getApply_corp());
                                bundle.putString("apply_time",pubFun.format(bl.getApply_out()));
                                bundle.putInt("state", bl.getState());
                                bundle.putString("cancel_reason", bl.getCancel_reason());
                                intent.putExtras(bundle);
                                intent.setClass(ApplyListActivity.this, AuditDetailActivity.class);
                                ApplyListActivity.this.startActivityForResult(intent,1);
                            }
                        });
                        break;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply_list);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Bundle bundle = getIntent().getExtras();
        user_id = bundle.getInt("user_id");
        listView = (ListView) findViewById(R.id.applies_list);

        handler = new ApplyListActivity.MyHandler();
        getBorrowListsData();


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

    public void getBorrowListsData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Message msg = handler.obtainMessage();
                    BorrowListDao borrowListDao = BorrowListDao.getBorrowListDao();
                    borrowLists = borrowListDao.getBorrowListByUser_id(user_id);
                    UserDao userDao = UserDao.getUserDao();
                    User user = userDao.getUserById(user_id);
                    for(BorrowList borrowList : borrowLists){
                        borrowList.setApply_corp(user.getCompany_name());
                    }
                    msg.what = 0;
                    handler.sendMessage(msg);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
