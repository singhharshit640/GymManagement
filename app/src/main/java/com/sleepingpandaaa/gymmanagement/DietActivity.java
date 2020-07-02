package com.sleepingpandaaa.gymmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DietActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);
        Tools.setSystemBarLight(DietActivity.this);
        Tools.setSystemBarColor(DietActivity.this, R.color.colorPrimaryDark);
    }
}