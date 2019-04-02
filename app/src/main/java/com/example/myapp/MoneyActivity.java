package com.example.myapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MoneyActivity extends AppCompatActivity {

    EditText rmd;
    TextView show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);

        rmd = findViewById(R.id.rmd);
        show = findViewById(R.id.show);
    }
    public void onClick(View v){
        String str = rmd.getText().toString();
        float r=0;
        float ans;
        if (str.length()>0) {
            r=Float.parseFloat(str);
        }
        if(v.getId()==R.id.dollar){
            ans = r * (1/6.7f);
            show.setText(String.format("%.2f", ans));
        }else if(v.getId()==R.id.won){
            ans = r * 500;
            show.setText(String.format("%.2f", ans));
        }else if(v.getId()==R.id.euro){
            ans = r * (1/11f);
            show.setText(String.format("%.2f", ans));
        }

    }
}
