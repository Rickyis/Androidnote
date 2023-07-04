package com.example.note;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    private EditText et_author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_author = (EditText)findViewById(R.id.et_author);


    }
    //“登录”按钮方法，在布局文件中通过标签绑定监听器
    public void login(View view){
        //获取SharedPreferences.Editor对象,用来改SharedPreferences中的数据
        SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
        //将键值对存入SharedPreferences文件
        if(TextUtils.isEmpty(et_author.getText().toString())){
//            将指定键值对添加到SharedPreferences中
            editor.putString(DiaryDb.AUTHOR,"YYJ");
        }else {
            editor.putString(DiaryDb.AUTHOR, et_author.getText().toString());
        }
//        立即将修改操作应用到SharedPreferences中
        editor.commit();
        //使用显示Intent，通过Login启动MainActivity
        Intent intent = new Intent(Login.this, MainActivity.class);
        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
        String  auther_name1= pref.getString(DiaryDb.AUTHOR,"");
        //传递数据
        intent.putExtra(DiaryDb.AUTHOR, auther_name1);
        startActivity(intent);
    }

}
