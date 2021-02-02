package com.yll.changshu.service;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.yll.changshu.Adapter.AuditDetailAdapter;
import com.yll.changshu.Adapter.BorrowListAdpter;
import com.yll.changshu.Adapter.BorrowToolAdapter;
import com.yll.changshu.R;
import com.yll.changshu.dao.BorrowListDao;
import com.yll.changshu.dao.BorrowToolDao;
import com.yll.changshu.dao.ToolInventoryDao;
import com.yll.changshu.dao.UserDao;
import com.yll.changshu.entity.BorrowList;
import com.yll.changshu.entity.BorrowTool;
import com.yll.changshu.entity.ToolInventory;
import com.yll.changshu.entity.User;
import com.yll.changshu.util.pubFun;

import java.util.List;

public class AuditDetailActivity extends AppCompatActivity {
    private int list_id;
    private int user_id;
    private String corp_name;
    private String apl_time;
    private TextView listId;
    private TextView apply_corp;
    private TextView apply_time;
    private RadioGroup radioGrp;
    private TextView reason;
    private BorrowList borrowList;
    private LinearLayout denyll;
    private List<BorrowTool> borrowToolList;
    private ListView listView;
    private AuditDetailAdapter auditDetailAdapter;
    private Handler handler;

    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    auditDetailAdapter = new AuditDetailAdapter(AuditDetailActivity.this, borrowToolList);
                    listView.setAdapter(auditDetailAdapter);
                    break;
                case 1:
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putInt("user_id",user_id);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                    break;
                default:
                    break;
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audit_detail);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Bundle bundle = getIntent().getExtras();
        user_id = bundle.getInt("user_id");
        list_id = bundle.getInt("list_id");
        corp_name = bundle.getString("corp_name");
        apl_time = bundle.getString("apply_time");
        handler = new AuditDetailActivity.MyHandler();

        getBorrowListDetail();

        listId = (TextView) findViewById(R.id.list_id);
        apply_corp = (TextView) findViewById(R.id.apply_corp);
        apply_time = (TextView) findViewById(R.id.apply_time);
        listId.setText(""+list_id);
        apply_corp.setText(corp_name);
        apply_time.setText(apl_time);

        listView = (ListView) findViewById(R.id.borrows_list);

        radioGrp = (RadioGroup) findViewById(R.id.radioGrp);
        //第一种获得单选按钮值的方法
        //为radioGroup设置一个监听器:setOnCheckedChanged()
        radioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                RadioButton radbtn = (RadioButton) findViewById(checkedId);
//                System.out.println(checkedId);
//                System.out.println(R.id.deny);
                denyll = (LinearLayout) findViewById(R.id.denyll);
                if(checkedId == R.id.approve){
                    denyll = (LinearLayout) findViewById(R.id.denyll);
                    denyll.setVisibility(View.GONE);
                }else {
                    denyll.setVisibility(View.VISIBLE);
                }
            }
        });


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

    private void getBorrowListDetail(){
        new Thread(new Runnable() {
            @Override
            public void run() {
            try{
                Message msg = handler.obtainMessage();
                BorrowListDao borrowListDao = BorrowListDao.getBorrowListDao();
                UserDao userDao = UserDao.getUserDao();
                borrowList = borrowListDao.getBorrowListByList_id(list_id);
                User user = userDao.getUserById(borrowList.getCustomId());
                borrowList.setApply_corp(user.getCompany_name());
//                System.out.println(user.getCompany_name());

                BorrowToolDao borrowToolDao = BorrowToolDao.getBorrowToolDao();
                borrowToolList = borrowToolDao.getBorrowtoolByList_id(list_id);
                ToolInventoryDao toolInventoryDao = ToolInventoryDao.getToolInventoryDao();
//                System.out.println(borrowToolList.size());
                for(BorrowTool borrowTool : borrowToolList){
                    ToolInventory toolInventory =
                            toolInventoryDao.getToolInventoryByToolname_idAndTooltype(
                                    borrowTool.getToolname_id(),borrowTool.getType());
                    borrowTool.setRest_number(toolInventory.getNow_number());
                }
                msg.what = 0;
                handler.sendMessage(msg);
            } catch (Exception e){
                e.printStackTrace();
            }
            }
        }).start();
    }

    public void OnCommitClick(View v){
        RadioButton deny = (RadioButton) findViewById(R.id.deny);
        RadioButton approve = (RadioButton)findViewById(R.id.approve);

        if(deny.isChecked()) {
            reason = (TextView) findViewById(R.id.reason);
            String denyReason = reason.getText().toString();
            borrowList.setCancel_reason(denyReason);
            borrowList.setState(4);
        } else if(approve.isChecked()){
            borrowList.setState(1);
        } else {
            Toast.makeText(AuditDetailActivity.this, "请选择批准或者不批准", Toast.LENGTH_SHORT).show();
            return;
        }
        updateBorrowList();
    }

    public void updateBorrowList(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Message msg = handler.obtainMessage();
                    BorrowListDao borrowListDao = BorrowListDao.getBorrowListDao();
                    borrowListDao.updateBorrowListStateByList_id(borrowList.getState(), borrowList.getList_id());
                    if(borrowList.getState() == 4){
                        borrowListDao.updateBorrowListCancel_reasonByList_id(borrowList.getList_id(), borrowList.getCancel_reason());
                    }
//                    else {
//                        ToolInventoryDao toolInventoryDao = ToolInventoryDao.getToolInventoryDao();
//                        for(BorrowTool borrowTool : borrowToolList){
//                            toolInventoryDao.updateToolInventoryNow_numberByToolname_idAndTooltype();
//                        }
//                    }
                    msg.what = 1;
                    handler.sendMessage(msg);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
