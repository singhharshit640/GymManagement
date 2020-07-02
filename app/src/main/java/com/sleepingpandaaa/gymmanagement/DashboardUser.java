package com.sleepingpandaaa.gymmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class DashboardUser extends AppCompatActivity {

    ImageView btnAddDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_user);
        Tools.setSystemBarLight(DashboardUser.this);
        Tools.setSystemBarColor(DashboardUser.this, R.color.colorPrimaryDark);

        btnAddDetails = findViewById(R.id.btnAddDetails);

        btnAddDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardUser.this, AddUserDetail.class);
                startActivity(intent);
            }
        });

    }
}