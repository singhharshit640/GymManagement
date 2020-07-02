package com.sleepingpandaaa.gymmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NetStatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_stats);
        Tools.setSystemBarLight(NetStatsActivity.this);
        Tools.setSystemBarColor(NetStatsActivity.this, R.color.colorPrimaryDark);
    }
}