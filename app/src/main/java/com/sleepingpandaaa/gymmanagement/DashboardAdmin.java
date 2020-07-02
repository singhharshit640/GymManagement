package com.sleepingpandaaa.gymmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DashboardAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);
        Tools.setSystemBarLight(DashboardAdmin.this);
        Tools.setSystemBarColor(DashboardAdmin.this, R.color.colorPrimaryDark);
    }
}