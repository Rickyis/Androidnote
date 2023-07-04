package com.example.note;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DiaryDb extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "yyj";
    public static final String ID = "_id";//ID
    public static final String TITLE = "title";//标题
    public static final String AUTHOR = "author";//作者
    public static final String CONTENT = "content";//内容
    public static final String TIME = "time";//创建时间

    public DiaryDb(Context context) {
        super(context, "yyj", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql ="create table "+TABLE_NAME+" ("
                +ID+" integer primary key AUTOINCREMENT, "
                +TITLE+" TEXT,"
                +AUTHOR+" TEXT,"
                +CONTENT +" TEXT NOT NULL,"
                +TIME+" TEXT NOT NULL )";
        db.execSQL(sql);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
