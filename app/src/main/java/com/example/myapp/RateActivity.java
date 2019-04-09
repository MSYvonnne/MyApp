package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class RateActivity extends AppCompatActivity {

    private static final String TAG = "RateActivity";
    EditText dollarText,wonText,euroText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        Intent intent = getIntent();
        float dollar2 = intent.getFloatExtra("dollar_rate_key",0.0f);
        float won2 = intent.getFloatExtra("won_rate_key",0.0f);
        float euro2 = intent.getFloatExtra("euro_rate_key",0.0f);

        Log.i(TAG, "onCreate: dollar2=" + dollar2);
        Log.i(TAG, "onCreate: won2=" + won2);
        Log.i(TAG, "onCreate: euro2=" + euro2);

        dollarText = findViewById(R.id.rate_dollar);
        dollarText.setText(String.valueOf(dollar2));
        wonText = findViewById(R.id.rate_won);
        wonText.setText(String.valueOf(won2));
        euroText = findViewById(R.id.rate_euro);
        euroText.setText(String.valueOf(euro2));
    }

    public void save(View btn){
        Log.i(TAG, "save:");
        //获取新的值
        float newDollar = Float.parseFloat(dollarText.getText().toString());
        float newWon = Float.parseFloat(wonText.getText().toString());
        float newEuro = Float.parseFloat(euroText.getText().toString());
        //Log.i(TAG, "save: 取得新的汇率");
        //保存
        Intent intent = getIntent();
        Bundle bdl = new Bundle();
        bdl.putFloat("dollar_rate_key",newDollar);
        bdl.putFloat("won_rate_key",newWon);
        bdl.putFloat("euro_rate_key",newEuro);
        intent.putExtras(bdl);
        setResult(2,intent);
        //返回调用页面
        finish();
    }
}
