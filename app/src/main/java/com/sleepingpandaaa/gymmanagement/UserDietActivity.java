package com.sleepingpandaaa.gymmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class UserDietActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_diet);
        Tools.setSystemBarLight(UserDietActivity.this);
        Tools.setSystemBarColor(UserDietActivity.this, R.color.colorPrimaryDark);
    }
}