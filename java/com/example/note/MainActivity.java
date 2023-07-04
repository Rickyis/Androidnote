package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mList;
    private Intent mIntent;
    private MyAdapter myAdapter;
    private DiaryDb mNotedb;
    private Cursor cursor;
    private SQLiteDatabase dbreader;
    List<Record> recordList=new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = (ListView) this.findViewById(R.id.list);
        mNotedb = new DiaryDb(this);
        dbreader = mNotedb.getReadableDatabase();//创建可读数据库
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("Range")
            @Override
//            监听item点击事件
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Record record = (Record) mList.getItemAtPosition(i);
                Intent intent = new Intent(MainActivity.this, Showcontent.class);
                //将数据传到ShowContent中显示
                intent.putExtra(DiaryDb.ID,String.valueOf(record.getId1()));
                intent.putExtra(DiaryDb.TITLE,record.getTitle1());
                intent.putExtra(DiaryDb.CONTENT,record.getContent1());
                intent.putExtra(DiaryDb.TIME,record.getCreate_time1());
                startActivity(intent);
            }
        });

    }
    //"添加"按钮的方法
    public void add(View v) {
        mIntent = new Intent(MainActivity.this, AddDiary.class);
        //接收数据作者名
        String author=getIntent().getStringExtra(DiaryDb.AUTHOR);
        //将作者名传递给AddContent，添加进数据库
        mIntent.putExtra(DiaryDb.AUTHOR,author);
        startActivity(mIntent);

    }
    public void selectDb() {
        //获取输入的作者名，根据作者名，用游标去查找对应数据库的列值
        String author=this.getIntent().getStringExtra(DiaryDb.AUTHOR);
        cursor = dbreader.query(DiaryDb.TABLE_NAME,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            //创建一个Record对象，用来保存数据
            Record record;
            while(!cursor.isAfterLast()){
                record = new Record();
                record.setId1(cursor.getInt(cursor.getColumnIndex(DiaryDb.ID)));
                record.setTitle1(cursor.getString(cursor.getColumnIndex(DiaryDb.TITLE)));
                record.setContent1(cursor.getString(cursor.getColumnIndex(DiaryDb.CONTENT)));
                record.setAuthor1(cursor.getString(cursor.getColumnIndex(DiaryDb.AUTHOR)));
                record.setCreate_time1(cursor.getString(cursor.getColumnIndex(DiaryDb.TIME)));
                //如果与输入作者名相同，加入到List中
                if(record.getAuthor1().equals(author)){
                    recordList.add(record);
                }
                cursor.moveToNext();
            }
        }
        //关闭游标
        cursor.close();
        //创建一个Adapter实例
        myAdapter = new MyAdapter(this,recordList,R.layout.item);
        mList.setAdapter(myAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //将列表清空
        recordList.clear();
        selectDb();
    }
}
