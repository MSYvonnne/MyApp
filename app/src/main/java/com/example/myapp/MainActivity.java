package com.example.myapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void openOne(View btn){
        Intent intentPhone= new Intent(Intent.ACTION_CALL, Uri.parse("tel:15328066553"));
        startActivity(intentPhone);
    }
}
