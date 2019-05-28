package com.example.myapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class FragmentActivity extends android.support.v4.app.FragmentActivity {

    private Fragment mFragments[];
    private RadioGroup radioGroup;
    private FragmentManager fragmentManager; //管理切换的管理器
    private FragmentTransaction fragmentTransaction; //事务管理
    private RadioButton rbtHome,rbtFunc,rbtSetting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        mFragments = new Fragment[3];
        fragmentManager = getSupportFragmentManager();
        mFragments[0] = fragmentManager.findFragmentById(R.id.fragment_main);
        mFragments[1] = fragmentManager.findFragmentById(R.id.fragment_func);
        mFragments[2] = fragmentManager.findFragmentById(R.id.fragment_setting);
        fragmentTransaction =
                fragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]);
        fragmentTransaction.show(mFragments[0]).commit();

        rbtHome = findViewById(R.id.radioHome);
        rbtFunc = findViewById(R.id.radioFunc);
        rbtSetting = findViewById(R.id.radioSetting);
        rbtHome.setBackgroundResource(R.drawable.shape3);

        radioGroup = findViewById(R.id.bottomGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                fragmentTransaction =
                        fragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]);

                rbtHome.setBackgroundResource(R.drawable.shape2);
                rbtFunc.setBackgroundResource(R.drawable.shape2);
                rbtSetting.setBackgroundResource(R.drawable.shape2);

                switch (checkedId){
                    case R.id.radioHome:
                        fragmentTransaction.show(mFragments[0]).commit();
                        rbtHome.setBackgroundResource(R.drawable.shape3);
                        break;
                    case R.id.radioFunc:
                        fragmentTransaction.show(mFragments[1]).commit();
                        rbtFunc.setBackgroundResource(R.drawable.shape3);
                        break;
                    case R.id.radioSetting:
                        fragmentTransaction.show(mFragments[2]).commit();
                        rbtSetting.setBackgroundResource(R.drawable.shape3);
                        break;
                    default:
                        break;
                }
            }
        });


    }
}
