package com.example.myapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FuncFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup containter, Bundle savedInstanceState){
        return inflater.inflate(R.layout.frame_func,containter);
    }

    @Nullable
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        TextView tv = (TextView)getView().findViewById(R.id.funcTextV1);
        tv.setText("这是功能页面");
    }
}
