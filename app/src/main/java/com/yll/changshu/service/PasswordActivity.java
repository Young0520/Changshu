package com.yll.changshu.service;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.yll.changshu.R;
import com.yll.changshu.dao.UserDao;
import com.yll.changshu.entity.User;

public class PasswordActivity extends AppCompatActivity {
    private EditText oldPsw;
    private EditText newPsw;
    private EditText newPswConfirm;
    private int user_rank;
    private int user_id;
    private Handler handler;

    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    Toast.makeText(PasswordActivity.this, "修改密码成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                    break;
                default:
                    Toast.makeText(PasswordActivity.this, "旧密码不正确", Toast.LENGTH_SHORT).show();
                    oldPsw.setText("");
                    newPsw.setText("");
                    newPswConfirm.setText("");
                    break;
            }
        }
    }


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Bundle bundle = getIntent().getExtras();
        user_rank = bundle.getInt("user_rank");
        user_id = bundle.getInt("user_id");
        oldPsw = (EditText)findViewById(R.id.oldPsw);
        newPsw = (EditText)findViewById(R.id.newPsw);
        newPswConfirm = (EditText)findViewById(R.id.newPwdConfirm);
        handler = new PasswordActivity.MyHandler();
//        user_name = bundle.getString("user_name");
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

    public void OnMyConfirmClick(View v){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Message msg = handler.obtainMessage();
                    if(!newPswConfirm.getText().toString().equals(newPsw.getText().toString())){
                        msg.what = 1;
                    } else {
                        UserDao userDao = UserDao.getUserDao();
                        User user = userDao.getUserById(user_id);
                        if (user.getPsw().equals(oldPsw.getText().toString())) {
                            userDao.updateUserPsw(newPsw.getText().toString(), user_id);
                            msg.what = 0;
                        } else {
                            msg.what = 1;
                        }
                    }
                    handler.sendMessage(msg);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
