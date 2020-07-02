package com.sleepingpandaaa.gymmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CurrentPlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_plan);
        Tools.setSystemBarLight(CurrentPlanActivity.this);
        Tools.setSystemBarColor(CurrentPlanActivity.this, R.color.colorPrimaryDark);
    }
}