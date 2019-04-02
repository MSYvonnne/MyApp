package com.example.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HelloActivity extends AppCompatActivity implements OnCreateContextMenuListener, View.OnClickListener {
    TextView out;
    EditText inp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        out =  findViewById(R.id.textV);
        out.setText(R.string.btn_label);

        inp =findViewById(R.id.inp);
        String str= inp.getText().toString();
 
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.i("main", "onClick msg....");
        try{
            String text = inp.getText().toString();
            float c = Float.valueOf(text);
            float F = (c * 9) / 5 + 32;
            out.setText("结果为" + String.format("%.3f", F));
        }
        catch (Exception e){
            out.setText("请输入摄氏度");
        }

    }
}
