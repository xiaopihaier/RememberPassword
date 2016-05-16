package com.zhf.zhf81.rememberpassword;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button Login;//登陆按钮
    private EditText InputUsername; //用户名输入框
    private EditText InputPassword; //密码输入框
    private CheckBox RememberPassword;//记住密码复选框
    private SharedPreferences pref;//创建一个SharedPreferences类
    private SharedPreferences.Editor editor;//创建一个对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getSharedPreferences("Remember_Password", MODE_PRIVATE);  //保存用户信息进Remember_Password文件，并且为私有
        InputUsername = (EditText) findViewById(R.id.InputUsername);//关联用户名输入框控件
        InputPassword = (EditText) findViewById(R.id.InputPassword);//关联密码输入框控件
        Login = (Button) findViewById(R.id.Login);//关联登陆按钮控件
        RememberPassword = (CheckBox) findViewById(R.id.RememberPassword);//关联记住密码复选框
        boolean Remember_Username = pref.getBoolean("Remember_Password", false);//默认无Remember_Username文件
        String Username = pref.getString("Username", "");
        InputUsername.setText(Username);
        if (Remember_Username) {
            //将账号和密码设置到文本框中
            String Password = pref.getString("Password", "");
            InputPassword.setText(Password);
            RememberPassword.setChecked(true);
        }
        Login.setOnClickListener(new View.OnClickListener() {//实现一个Onclick方法
            @Override
            public void onClick(View view) {
                String Username = InputUsername.getText().toString().trim();
                String Password = InputPassword.getText().toString().trim();
                editor = pref.edit();
                editor.putBoolean("Remember_Username", true);
                editor.putString("Username", Username);
                if (RememberPassword.isChecked()) {
                    editor.putBoolean("Remember_Password", true);
                    editor.putString("Password", Password);
                } else
                    editor.clear();
                editor.commit();
                Intent MyActivity = new Intent(MainActivity.this, MainInterface.class);
                startActivity(MyActivity);
            }
        });
    }
}
