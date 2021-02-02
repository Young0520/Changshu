package com.yll.changshu.service;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.yll.changshu.Adapter.ToolInventoryAdapter;
import com.yll.changshu.R;
import com.yll.changshu.dao.ToolInventoryDao;
import com.yll.changshu.entity.BorrowTool;
import com.yll.changshu.entity.ToolInventory;
import java.util.ArrayList;
import java.util.List;

public class ToolListActivity extends AppCompatActivity {

    private ListView listview;
    private int father_id;
    private int user_id;
    private int user_rank;
    private TextView toshow;
    private Button btnGen;
    private int toolname_id;
    private List<ToolInventory> toolInventoryList;
    private ToolInventoryAdapter toolInventoryAdapter;
    private Handler handler;
    private ArrayList<BorrowTool> borrowToolList;

    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    toolInventoryAdapter = new ToolInventoryAdapter(ToolListActivity.this, toolInventoryList, user_rank);
                    listview.setAdapter(toolInventoryAdapter);
                    break;
                default:
                    break;
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tool_list);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Bundle bundle = getIntent().getExtras();
        user_id = bundle.getInt("user_id");
        father_id = bundle.getInt("node_id");
        user_rank = bundle.getInt("user_rank");
        toolname_id = bundle.getInt("toolname_id");
        listview = (ListView) findViewById(R.id.tools_list);
        if(user_rank == 1){
            toshow = (TextView) findViewById(R.id.toshow);
            toshow.setVisibility(View.VISIBLE);
            btnGen = (Button) findViewById(R.id.btnGen);
            btnGen.setVisibility(View.VISIBLE);
        }
        handler = new ToolListActivity.MyHandler();
        getToolInventoryData();

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

    public void getToolInventoryData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Message msg = handler.obtainMessage();
                    ToolInventoryDao toolInventoryDao = ToolInventoryDao.getToolInventoryDao();
                    toolInventoryList = toolInventoryDao.getToolInventoryByToolname_id(toolname_id);
                    msg.what = 0;
                    handler.sendMessage(msg);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

    }
//
//    public void OnBackClick(View v){
////        Bundle bundle = new Bundle();
////        bundle.putInt("toolname_id", toolname_id);
////        bundle.putInt("user_id",user_id);
////        bundle.putInt("node_id",father_id);
//        Intent intent = new Intent();
////        intent.putExtras(bundle);
//        setResult(RESULT_OK, intent);
//        finish();
//    }

    public void OnGenApplyClick(View v){
        borrowToolList = new ArrayList<BorrowTool>();
        for(int i = 0;i<toolInventoryAdapter.getCount();i++){
            View view = (View)listview.getAdapter().getView(i,null,null);
//            LinearLayout linearLayout = (LinearLayout)listview.getAdapter().getView(i,null,null);
            ToolInventory toolInventory = (ToolInventory) view.getTag();
            CheckBox checkBox = (CheckBox)view.findViewById(R.id.checkBox);
            TextView type = (TextView) view.findViewById(R.id.type);
            TextView parameter = (TextView) view.findViewById(R.id.parameter);
            TextView rest_number = (TextView) view.findViewById(R.id.rest_number);
//            System.out.println(type.getText().toString());
//            System.out.println(checkBox.isChecked());
            if(checkBox.isChecked()){
                BorrowTool borrowTool = new BorrowTool();
                borrowTool.setToolname(toolInventory.getToolname());
                borrowTool.setType(toolInventory.getTooltype());
                borrowTool.setToolname_id(toolInventory.getToolname_id());
                borrowTool.setRest_number(toolInventory.getNow_number());
//                System.out.println(borrowTool.getRest_number());
                borrowTool.setNumber(0);
                borrowTool.setState(0);
                borrowToolList.add(borrowTool);
//                borrowTool.setRest_number(Integer.parseInt(rest_number.getText().toString()));
            }
        }
        Bundle bundle = new Bundle();
        bundle.putInt("user_id",user_id);
        bundle.putInt("user_rank", user_rank);
        bundle.putSerializable("borrowToolList", borrowToolList);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(ToolListActivity.this, BorrowListActivity.class);
        ToolListActivity.this.startActivityForResult(intent, 1);
    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        //这里要通过请求码来判断数据的来源
//        switch (requestCode) {
//            case 1:
//                //判断请求的结果是否成功，resultCode == RESULT_OK，代表成功了
//                if (resultCode == RESULT_OK) {
//                    Bundle bundle = data.getExtras();
//                    getToolInventoryData();
////                    Toast.makeText(AuditListActivity.this, "返回", Toast.LENGTH_LONG).show();
//                }
//                break;
//            default:
//                break;
//        }
//    }

}
