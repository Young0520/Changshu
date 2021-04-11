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

import com.yll.changshu.Adapter.BorrowListAdpter;
import com.yll.changshu.R;
import com.yll.changshu.dao.BorrowListDao;
import com.yll.changshu.dao.UserDao;
import com.yll.changshu.entity.BorrowList;
import com.yll.changshu.entity.User;
import com.yll.changshu.util.pubFun;

import java.util.List;

public class AuditListActivity extends AppCompatActivity {

    private int user_id;
    private List<BorrowList> borrowLists;
    private ListView listView;
    private BorrowListAdpter borrowListAdpter;
    private Handler handler;

    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    borrowListAdpter = new BorrowListAdpter(AuditListActivity.this, borrowLists);
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
                            intent.setClass(AuditListActivity.this, AuditDetailActivity.class);
                            AuditListActivity.this.startActivityForResult(intent,1);
                        }
                    });
                    break;
                default:
                    break;
            }
        }
    }


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audit_list);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Bundle bundle = getIntent().getExtras();
        user_id = bundle.getInt("user_id");
        listView = (ListView) findViewById(R.id.audits_list);
        handler = new AuditListActivity.MyHandler();
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
                    UserDao userDao = UserDao.getUserDao();
                    borrowLists = borrowListDao.getBorrowListByApprover_id(user_id);
                    for(BorrowList borrowList : borrowLists){
                        User user = userDao.getUserById(borrowList.getCustomId());
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //这里要通过请求码来判断数据的来源
        switch (requestCode) {
            case 1:
                //判断请求的结果是否成功，resultCode == RESULT_OK，代表成功了
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    Intent intent = new Intent();
                    user_id = bundle.getInt("user_id");
                    intent.putExtras(bundle);
                    intent.setClass(this, AuditListActivity.class);
                    startActivity(intent);
                    finish();//关闭自己
                }
                break;
            default:
                break;
        }
    }

}
