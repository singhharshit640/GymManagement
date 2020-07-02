package com.sleepingpandaaa.gymmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AddUserDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_detail);
        Tools.setSystemBarLight(AddUserDetail.this);
        Tools.setSystemBarColor(AddUserDetail.this, R.color.colorPrimaryDark);
    }
}