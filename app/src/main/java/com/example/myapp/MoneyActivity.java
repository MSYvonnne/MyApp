package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MoneyActivity extends AppCompatActivity {

    private static final String TAG ="MoneyActivity" ;
    EditText rmd;
    TextView show;
    float dollar_rate=1/6.7f;
    float won_rate=500;
    float euro_rate=1/11.0f;
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
        getMenuInflater().inflate(R.menu.rate,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
