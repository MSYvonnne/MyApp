package com.example.myapp;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyList2Activity extends ListActivity implements Runnable,AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {

    private static final String TAG = "MyList2Activity";
    Handler handler;
    private List<HashMap<String,String>> listItems; //存放文字、图片信息
    private SimpleAdapter listItemAdapter; //适配器
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListView();

        //MyAdapter myAdapter = new MyAdapter(this,R.layout.activity_my_list2,listItems);
        //this.setListAdapter(myAdapter);

        this.setListAdapter(listItemAdapter);
        Thread t = new Thread(this);
        t.start();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                if(msg.what==7){
                    listItems = (List<HashMap<String,String>>) msg.obj;
                    //ListAdapter adapter = new ArrayAdapter<String>(MyList2Activity.this,
                    // R.layout.support_simple_spinner_dropdown_item);

                    //生成适配器的item和动态数组对应的元素
                    listItemAdapter = new SimpleAdapter(MyList2Activity.this,listItems,//listitem数据源
                            R.layout.activity_my_list2, //listitem的XML布局实现
                            new String[]{"ItemT","ItemD"},new int[]{R.id.itemT,R.id.itemD});
                    setListAdapter(listItemAdapter);
                }
                super.handleMessage(msg);
            }
        };
        getListView().setOnItemClickListener(this); //点击list一栏，跳转下一个页面 短按
        getListView().setOnItemLongClickListener(this); //长按
    }

    private void initListView(){
        listItems = new ArrayList<HashMap<String,String>>();
        for (int i = 0; i < 10; i++){
            HashMap<String,String> map = new HashMap<String, String>();
            map.put("ItemT","Rate:" + i); //k不一样，所以每个+1
            map.put("ItemD","detail:" + i);
            listItems.add(map);
        }
        //生成适配器的item和动态数组对应的元素
        listItemAdapter = new SimpleAdapter(this,listItems,//listitem数据源
                R.layout.activity_my_list2, //listitem的XML布局实现
                new String[]{"ItemT","ItemD"},new int[]{R.id.itemT,R.id.itemD});
    }

    @Override
    public void run() {
        //获取网络数据，放入list带回主线程
        List<HashMap<String,String>> retList = new ArrayList<HashMap<String, String>>();
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.usd-cny.com/bankofchina.htm").get();
            Log.i(TAG, "run: " + doc.title());
            Elements tables = doc.getElementsByTag("table");

            Element table1 = tables.get(0);
            //获取td中的数据
            Elements tds = table1.getElementsByTag("td");
            for(int i = 0;i<tds.size();i+=6){
                Element td1 = tds.get(i);
                Element td2 = tds.get(i+5);
                Log.i(TAG, "run: text=" + td1.text());
                Log.i(TAG, "run: val=" + td2.text());
                HashMap<String,String> map = new HashMap<String, String>();
                map.put("ItemT",td1.text());
                map.put("ItemD",td2.text());
                retList.add(map);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        //获取msg对象，用于返回主线程
        Message msg = handler.obtainMessage(7);
        msg.obj = retList;
        handler.sendMessage(msg);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String,String> map = (HashMap<String, String>) getListView().getItemAtPosition(position);
        String title = map.get("ItemT");
        String detail = map.get("ItemD");

        //打开新的页面，传参
        Intent rateCalc = new Intent(this,RateCalcActivity.class);
        rateCalc.putExtra("title",title);
        rateCalc.putExtra("rate",Float.parseFloat(detail));
        startActivity(rateCalc);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        Log.i(TAG, "onItemLongClick: 长按position = " + position);

        //构造对话框，进行确认操作
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示").setMessage("请确认是否删除当前数据栏").
                setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG, "onClick: 对话框事件处理");
                        //删除操作
                        listItems.remove(position); //删除数据
                        listItemAdapter.notifyDataSetChanged(); //刷新
                    }
                }).setNegativeButton("否",null);

        builder.create().show();

        Log.i(TAG, "onItemLongClick: size = " + listItems.size());
        return true;
    }
}
