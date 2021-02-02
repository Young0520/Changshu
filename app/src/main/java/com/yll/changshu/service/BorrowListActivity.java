package com.yll.changshu.service;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.yll.changshu.Adapter.BorrowToolAdapter;
import com.yll.changshu.R;
import com.yll.changshu.dao.BorrowListDao;
import com.yll.changshu.dao.BorrowToolDao;
import com.yll.changshu.entity.BorrowList;
import com.yll.changshu.entity.BorrowTool;
import com.yll.changshu.util.pubFun;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
public class BorrowListActivity extends AppCompatActivity {
    private Calendar calendarStart = Calendar.getInstance();
    private Calendar calendarEnd = Calendar.getInstance();
    private DatePickerDialog datePicker = null;
    private ListView listview;

    private Button startTimeBtn = null;
    private Button endTimeBtn = null;
    private int father_id;
    private int user_id;
    private int user_rank;
    private int toolname_id;
    private List<BorrowTool> borrowToolList;
    private BorrowToolAdapter borrowToolAdapter;
    private Handler handler;

    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    Toast.makeText(BorrowListActivity.this,"工具借用单申请完成", Toast.LENGTH_LONG).show();
                    Bundle bundle = new Bundle();
                    bundle.putInt("user_id", user_id);
                    bundle.putInt("user_rank",user_rank);
                    Intent intent = new Intent(BorrowListActivity.this,UserMainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    }


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrow_list);


        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        startTimeBtn = (Button) findViewById(R.id.btnStartTime);
        endTimeBtn = (Button) findViewById(R.id.btnEndTime);

        startTimeBtn.setText(pubFun.format(new Date()));
        endTimeBtn.setText(pubFun.format(new Date()));

        Bundle bundle = getIntent().getExtras();
        user_id = bundle.getInt("user_id");
        user_rank = bundle.getInt("user_rank");
        father_id = bundle.getInt("node_id");
        toolname_id = bundle.getInt("toolname_id");
        borrowToolList = (List<BorrowTool>)bundle.getSerializable("borrowToolList");
        listview = (ListView) findViewById(R.id.borrows_list);
        borrowToolAdapter = new BorrowToolAdapter(this, borrowToolList);
        listview.setAdapter(borrowToolAdapter);
        handler = new BorrowListActivity.MyHandler();

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

    public void OnCommitClick(View v){
        if(TextUtils.isEmpty(startTimeBtn.getText())){
            Toast.makeText(BorrowListActivity.this, "请选择借用时间。", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(endTimeBtn.getText())){
            Toast.makeText(BorrowListActivity.this, "请选择归还时间。", Toast.LENGTH_LONG).show();
            return;
        }
        final Date startTime = calendarStart.getTime();
        final Date endTime = calendarEnd.getTime();
        if(!endTime.after(startTime)){
            Toast.makeText(BorrowListActivity.this, "归还时间早于借用时间", Toast.LENGTH_LONG).show();
            return;
        }
        Boolean flag = true;
        final List<BorrowTool> borrowToolListTemp = new ArrayList<>();
        for(int i = 0; i < borrowToolAdapter.getCount(); i++){
            View view = (View)listview.getAdapter().getView(i,null,null);
            BorrowTool borrowTool = (BorrowTool)view.getTag();
            EditText borrow_num = (EditText)view.findViewById(R.id.borrow_num);
            if(TextUtils.isEmpty(borrow_num.getText())){
                Toast.makeText(BorrowListActivity.this, "请填写借用数量。", Toast.LENGTH_LONG).show();
                flag = false;
                break;
            } else {
                borrowTool.setNumber(Integer.parseInt(borrow_num.getText().toString()));
                borrowToolListTemp.add(borrowTool);
            }
        }
        if(flag){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        Message msg = handler.obtainMessage();
                        BorrowListDao borrowListDao = BorrowListDao.getBorrowListDao();
                        BorrowList borrowList = new BorrowList();
                        int list_id = new Long(new Date().getTime()/1000).intValue();
                        borrowList.setList_id(list_id);
                        borrowList.setState(0);
                        borrowList.setCustomId(user_id);
                        borrowList.setApply_out(startTime);
                        borrowList.setApply_return(endTime);
                        borrowListDao.insertBorrowList(borrowList);

                        BorrowToolDao borrowToolDao = BorrowToolDao.getBorrowToolDao();
                        for(int i = 0;i<borrowToolListTemp.size();i++){
                            borrowToolListTemp.get(i).setList_id(list_id);
                            borrowToolDao.insertBorrowTool(borrowToolListTemp.get(i));
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

    public void OnStartTimeClick(View v){
        openDate(startDateSetListenerSatrt, calendarStart);
    }

    public void OnEndTimeClick(View v){
        openDate(endDateSetListenerSatrt, calendarEnd);
    }

    private void openDate(DatePickerDialog.OnDateSetListener dateSetListener, Calendar calendar) {
        datePicker = new DatePickerDialog(this, dateSetListener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePicker.show();
    }

    /**
     * return date
     */
    private DatePickerDialog.OnDateSetListener startDateSetListenerSatrt = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            calendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            calendarStart.set(Calendar.MONTH, monthOfYear);
            calendarStart.set(Calendar.YEAR, year);
            startTimeBtn.setText(pubFun.format(calendarStart.getTime()));
        }
    };

    /**
     * return date
     */
    private DatePickerDialog.OnDateSetListener endDateSetListenerSatrt = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            calendarEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            calendarEnd.set(Calendar.MONTH, monthOfYear);
            calendarEnd.set(Calendar.YEAR, year);
            endTimeBtn.setText(pubFun.format(calendarEnd.getTime()));
        }
    };

}
