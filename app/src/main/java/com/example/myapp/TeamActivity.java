package com.example.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TeamActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textV2;
    TextView textV4;
    Button pot1,pot2,pot3,reset;
    Button pot1b,pot2b,pot3b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team); //实例化对象
        textV2 =  findViewById(R.id.textV2);
        textV4 =  findViewById(R.id.textV4);

        pot1 = findViewById(R.id.pot1);
        pot1.setOnClickListener(this);
        pot2 = findViewById(R.id.pot2);
        pot2.setOnClickListener(this);
        pot3 = findViewById(R.id.pot3);
        pot3.setOnClickListener(this);

        reset = findViewById(R.id.reset);
        reset.setOnClickListener(this);

        pot1b = findViewById(R.id.pot1b);
        pot1b.setOnClickListener(this);
        pot2b = findViewById(R.id.pot2b);
        pot2b.setOnClickListener(this);
        pot3b = findViewById(R.id.pot3b);
        pot3b.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Log.i("main", "onClick msg....");
        String sum = textV2.getText().toString();
        int c = Integer.valueOf(sum);

        String sumb = textV4.getText().toString();
        int cb = Integer.valueOf(sumb);
        int su = 0;
        switch (v.getId()) {
            case R.id.pot3:
                su = c + 3;
                textV2.setText(""+su);
                break;
            case R.id.pot2:
                su = c + 2;
                textV2.setText(""+ su);
                break;
            case R.id.pot1:
                su = c + 1;
                textV2.setText(""+su);
                break;
            case R.id.reset:
                textV2.setText(""+su);
                textV4.setText(""+su);
                break;
            case R.id.pot3b:
                su = cb + 3;
                textV4.setText(su+"");
                break;
            case R.id.pot1b:
                su = cb + 1;
                textV4.setText(""+su);
                break;
            case R.id.pot2b:
                su = cb + 2;
                textV4.setText(""+ su);
                break;
        }
    }
}
