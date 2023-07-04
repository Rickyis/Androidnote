package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Showcontent extends AppCompatActivity {
    Button update;
    private TextView time,tv_title,showtext;
    private DiaryDb mDb;
    private SQLiteDatabase mSql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcontent);
        update=(Button)findViewById(R.id.save) ;
        tv_title = (TextView)this.findViewById(R.id.tv_title);
        time = (TextView)this.findViewById(R.id.tv_createtime);
        showtext = (EditText)this.findViewById(R.id.showtext);
        mDb = new DiaryDb(this);
        mSql = mDb.getWritableDatabase();//创建可读、写的数据库对象
        //保存按钮，匿名内部类作为监听器
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values=new ContentValues();
                //DiaryDb.CONTENT 和 showtext 视图对象的文本内容放入 ContentValues 对象中。
                values.put(DiaryDb.CONTENT,showtext.getText().toString());
                //获取从上一个界面传递过来的名为 DiaryDb.ID 的额外数据,根据主键ID修改日记内容
                String id = getIntent().getStringExtra(DiaryDb.ID);
                mSql.update(DiaryDb.TABLE_NAME,values, DiaryDb.ID+"=?",new String[]{id});
                //跳转完以后结束活动
                finish();
            }
        });

        //显示修改后日记标题、内容、创建时间
        tv_title.setText(getIntent().getStringExtra(DiaryDb.TITLE));
        showtext.setText(getIntent().getStringExtra(DiaryDb.CONTENT));
        time.setText(getIntent().getStringExtra(DiaryDb.TIME));

    }
    //“删除”按钮
    public void delete(View v) {
        //根据日记Id删除
        String id = getIntent().getStringExtra(DiaryDb.ID);
        mSql.delete(DiaryDb.TABLE_NAME, DiaryDb.ID+"=?" ,new String[]{id});
        //跳转完以后结束活动
        finish();
    }
}