package com.example.myapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class RateCalcActivity extends AppCompatActivity {

    String TAG = "RateCalc";
    float rate = 0f;
    EditText input2;
    TextView show2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_calc);
        String title = getIntent().getStringExtra("title");
        rate = getIntent().getFloatExtra("rate",0f);

        Log.i(TAG, "onCreate: title=" + title);
        Log.i(TAG, "onCreate: rate=" + rate);
        ((TextView)findViewById(R.id.title2)).setText(title); //匿名对象
        input2 = (EditText)findViewById(R.id.input2);
        input2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                TextView show = (TextView) RateCalcActivity.this.findViewById(R.id.show2);
                if(s.length()>0){
                    float val = Float.parseFloat(s.toString());
                    show.setText(String.format(val + "RMB==>" + val * 100f/rate));
                }else {
                    show.setText("");
                }

            }
        });
    }
    /*public void onClick(View v){
        String str = input2.getText().toString();
        float rmb = 0;
        if (str.length()>0) {
            rmb = Float.parseFloat(str);
        }else {
            Toast.makeText(this,"请输入金额",Toast.LENGTH_SHORT).show();
        }
        if(v.getId()==R.id.button2){
            ((TextView)findViewById(R.id.show2)).setText(String.format("%.2f", rmb * 100f/rate));
        }
    }*/
}
