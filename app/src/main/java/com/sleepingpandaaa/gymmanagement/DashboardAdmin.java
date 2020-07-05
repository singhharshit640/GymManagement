package com.sleepingpandaaa.gymmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DashboardAdmin extends AppCompatActivity {

    ImageView ivManage, ivNetStats, ivPayment,ivLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);
        Tools.setSystemBarLight(DashboardAdmin.this);
        Tools.setSystemBarColor(DashboardAdmin.this, R.color.colorPrimaryDark);

        ivManage = findViewById(R.id.ivManage);
        ivNetStats = findViewById(R.id.ivNetStats);
        ivPayment = findViewById(R.id.ivPayment);
        ivLogout = findViewById(R.id.logoutAdmin);

        ivManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardAdmin.this, ManageActivity.class);
                startActivity(intent);
            }
        });

        ivNetStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardAdmin.this, NetStatsActivity.class);
                startActivity(intent);
            }
        });

        ivPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardAdmin.this, PaymentActivity.class);
                startActivity(intent);
            }
        });

        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardAdmin.this, StartActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}