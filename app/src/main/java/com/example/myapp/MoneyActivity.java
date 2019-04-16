package com.example.myapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MoneyActivity extends AppCompatActivity implements Runnable{

    private static final String TAG ="MoneyActivity" ;
    EditText rmd;
    TextView show;
    float dollar_rate=1/6.7f;
    float won_rate=500;
    float euro_rate=1/11.0f;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);

        rmd = findViewById(R.id.rmd);
        show = findViewById(R.id.show);

        //获取sp里保存的数据
        SharedPreferences abc =getSharedPreferences("myrate",Activity.MODE_PRIVATE);
        dollar_rate = abc.getFloat("dollar_key",0.0f);
        won_rate = abc.getFloat("won_key",0.0f);
        euro_rate = abc.getFloat("euro_key",0.0f);

        //开启子线程
        Thread t = new Thread(this);
        t.start();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                if(msg.what==5){
                    String str = (String) msg.obj;
                    Log.i(TAG, "handleMessage: msg=" + str);
                    show.setText(str);
                }
                super.handleMessage(msg);
            }
        };

    }
    public void onClick(View v){
        String str = rmd.getText().toString();
        float r=0;
        if (str.length()>0) {
            r=Float.parseFloat(str);
        }else {
            Toast.makeText(this,"请输入金额",Toast.LENGTH_SHORT).show();
        }
        if(v.getId()==R.id.dollar){
            show.setText(String.format("%.2f", r * dollar_rate));
        }else if(v.getId()==R.id.won){
            show.setText(String.format("%.2f", r * won_rate));
        }else if(v.getId()==R.id.euro){
            show.setText(String.format("%.2f",r * euro_rate));
        }
    }
    public void openOne(View btn){
        //start activity
        openRate();
    }

    private void openRate() {
        Intent rate= new Intent(this,RateActivity.class);
        rate.putExtra("dollar_rate_key",dollar_rate);
        rate.putExtra("won_rate_key",won_rate);
        rate.putExtra("euro_rate_key",euro_rate);

        Log.i(TAG,"openOne: dollar_rate=" + dollar_rate);
        Log.i(TAG, "openOne: won_rate=" + won_rate);
        Log.i(TAG, "openOne: euro_rate=" + euro_rate);
        //startActivity(rate);
        startActivityForResult(rate,1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加入菜单
        getMenuInflater().inflate(R.menu.rate,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //点击菜单
        if(item.getItemId()==R.id.menu_check){
            openRate();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1 && resultCode==2) {
            Bundle bundle=data.getExtras();
            dollar_rate = bundle.getFloat("dollar_rate_key");
            won_rate = bundle.getFloat("won_rate_key");
            euro_rate = bundle.getFloat("euro_rate_key");
            Log.i(TAG, "onActivityResult: dollar_rate=" + dollar_rate);
            Log.i(TAG, "onActivityResult: won_rate=" + won_rate);
            Log.i(TAG, "onActivityResult: euro_rate=" + euro_rate);

            //将新设置的汇率写入SP
            SharedPreferences abc= getSharedPreferences("myrate",Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = abc.edit();
            editor.putFloat("dollar_key",dollar_rate);
            editor.putFloat("won_key",won_rate);
            editor.putFloat("euro_key",euro_rate);
            editor.commit();
            Log.i(TAG, "onActivityResult: 数据已经保存到abc文件中");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void run() {
        Log.i(TAG, "run: 在运行");
        for (int i=1;i<6;i++){
            Log.i(TAG, "run: i=" + i);
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        //获取msg对象，用于返回主线程
        Message msg = handler.obtainMessage();
        msg.what = 5;
        msg.obj = "run run run";
        handler.sendMessage(msg);

        //获取网页
        try {
            URL url= new URL("http://www.boc.cn/sourcedb/whpj/");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            InputStream in = http.getInputStream();

            String html= inputStream2String(in);
            Log.i(TAG, "run: html" + html);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String inputStream2String(InputStream inputStream) throws IOException {
        final int bufferS = 1024;
        final char[] buffer = new char[bufferS];
        final StringBuffer out = new StringBuffer();
        Reader in = new InputStreamReader(inputStream,"UTF-8");
        for (;;){
            int rsz = in.read(buffer,0,buffer.length);
            if(rsz < 0)
                break;
            out.append(buffer,0,rsz);
        }
        return out.toString();
    }
}
