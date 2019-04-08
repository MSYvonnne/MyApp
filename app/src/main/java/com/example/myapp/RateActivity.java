package com.example.myapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RateActivity extends AppCompatActivity {

    EditText inp_rmb,inp_rate;
    TextView ans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        inp_rmb = findViewById(R.id.inp_rmb);
        inp_rate = findViewById(R.id.inp_rate);
        ans = findViewById(R.id.ans);
    }

    public void onClick(View v){
        String rmb = inp_rmb.getText().toString();
        String rate = inp_rate.getText().toString();
        float rm=0,ra=0,an;
        if (rmb.length()>0||rate.length()>0) {
            rm=Float.parseFloat(rmb);
            ra=Float.parseFloat(rate);
        }
        if(v.getId()==R.id.butn){
            an = rm * (1/ra);
            ans.setText(String.format("%.2f",an));
        }
    }
}
