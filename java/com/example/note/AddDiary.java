package com.example.note;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddDiary extends AppCompatActivity {
    private EditText et_content,et_title;
    private DiaryDb mDb;
//    SQLiteDatabase是一个类，用于帮助管理SQLite数据库。它提供了创建、查询、更新和删除数据库中数据的方法。
    private SQLiteDatabase mSqldb;
    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddiary);
//        获取启动当前活动（Activity）的意图（Intent）对象
        Intent intent=this.getIntent();
        //接受作者名
        name=intent.getStringExtra(DiaryDb.AUTHOR);
        et_title = (EditText) findViewById(R.id.et_title);
        et_content = (EditText) this.findViewById(R.id.et_content);
        mDb = new DiaryDb(this);
        //创建可读、写的数据库对象
        mSqldb = mDb.getWritableDatabase();

    }
    //"保存"按钮的方法
    public void save(View v) {
        DbAdd();
        finish();
    }
    public void DbAdd() {
        //ContentValues是用于存储数据库表的列值的类。它用于向数据库表中插入或更新数据。
        ContentValues cv = new ContentValues();
        cv.put(DiaryDb.TITLE,et_title.getText().toString());
        cv.put(DiaryDb.CONTENT,et_content.getText().toString());
        cv.put(DiaryDb.AUTHOR,name);
        cv.put(DiaryDb.TIME,getTime());
        //向数据库中插入修改的键值对数据
        mSqldb.insert(DiaryDb.TABLE_NAME,null,cv);
    }

    //"取消"按钮的方法
    public void cancle(View v) {
        et_title.setText("");
        et_content.setText("");
        finish();
    }

    //获取当前系统时间
    public String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date();
        String str = sdf.format(date);
        return str;
    }

}