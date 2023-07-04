package com.example.note;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;




import java.util.List;
//组件包
class Views {
    TextView titleView;
    TextView authorView;
    TextView timeView;

}//适配器
public class MyAdapter extends BaseAdapter {
//    在适配器中获取系统资源和服务
    private Context mContext;
    //数据集合
    private List<Record> mList;
    private int layoutId;


    public MyAdapter(Context mContext,List<Record> mList,int layoutId) {
        super();
        this.mContext = mContext;
        this.mList=mList;//事物列表
        this.layoutId=layoutId;//界面id
    }
    @Override
//    确定适配器需要展示多少个列表项
    public int getCount() {
        if (mList != null && mList.size() > 0)
            //返回列表大小
            return mList.size();
        else
            return 0;
    }
    //移除事件
    public void removeItem(int position){
        this.mList.remove(position);
    }
    //指定位置返回对应的数据项
    @Override
    public Object getItem(int position) {
        if (mList != null && mList.size() > 0)
            return mList.get(position);
        else
            return null;
    }
    //得到事件
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Views views;
// 没有可重用的视图
        if (view == null) {
// LayoutInflater加载布局文件并将其转换为对应的视图对象，getApplicationContext()获取应用程序的全局上下文，inflate()方法将布局文件转换为对应的视图对象
            view = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.item, viewGroup,false);
            views = new Views();
            //定义views用来查找特定目录
            views.titleView = view.findViewById(R.id.list_title);
            views.authorView = view.findViewById(R.id.list_author);
            views.timeView = view.findViewById(R.id.list_time);
            view.setTag(views);
//        有可重用的视图
        } else {
            views = (Views) view.getTag();
        }
        Record record = mList.get(position);
        String tile = record.getTitle1(); //设置标题
        views.titleView.setText((position+1)+"."+(tile.length()>7?tile.substring(0,7)+"...":tile));

        String author =record.getAuthor1();//设置作者
        views.authorView.setText(author);

        String createTime = record.getCreate_time1();  //设置创建时间
        views.timeView.setText(createTime);
        return view;
    }
}