package com.yll.changshu.service;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yll.changshu.R;
import com.yll.changshu.dao.UserDao;
import com.yll.changshu.entity.User;
import com.yll.changshu.util.pubFun;


public class LoginActivity extends AppCompatActivity {

    private EditText user_name;
    private EditText editPwd;
    private Button btnLogin;
    private int user_rank;
    private Handler handler;
    private int user_id;

    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 2:
                    Toast.makeText(LoginActivity.this, "密码不正确！", Toast.LENGTH_SHORT).show();
                    editPwd.setText("");
                    break;
                case 1:
                    Toast.makeText(LoginActivity.this, "该用户不存在！", Toast.LENGTH_SHORT).show();
                    user_name.setText("");
                    editPwd.setText("");
                    break;
                case 0:
                    Bundle bundle = new Bundle();
                    bundle.putInt("user_id",user_id);
                    bundle.putString("psw", editPwd.getText().toString());
                    bundle.putInt("user_rank", user_rank);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    if(user_rank == 1){
                        intent.setClass(LoginActivity.this, UserMainActivity.class);
                    }
                    else{
                        intent.setClass(LoginActivity.this, ManagerMainActivity.class);
                    }
                    LoginActivity.this.startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    }


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Bundle bundle = getIntent().getExtras();
        user_name = (EditText) findViewById(R.id.userName);
        editPwd = (EditText) findViewById(R.id.editPwd);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        handler = new LoginActivity.MyHandler();
    }

    /**
     * login event
     * @param v
     */
    public void OnMyLoginClick(View v){
        if(pubFun.isEmpty(user_name.getText().toString()) || pubFun.isEmpty(editPwd.getText().toString())){
            Toast.makeText(this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String userName = user_name.getText().toString();
                    String psw = editPwd.getText().toString();
                    try{
                        Message msg = handler.obtainMessage();
                        UserDao userDao = new UserDao();
                        User user = userDao.getUserByName(userName);
                        if(user == null){
                            msg.what = 1;
                        }
                        if (user != null){
                            if(user.getPsw().equals(psw)){
                                user_rank = user.getUser_rank();
                                user_id = user.getUserId();
                                msg.what = 0;
                            } else {
                                msg.what = 2;
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

}