package com.sleepingpandaaa.gymmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginAdminActivity extends AppCompatActivity {

    TextView tvAdminSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        Tools.setSystemBarLight(LoginAdminActivity.this);
        Tools.setSystemBarColor(LoginAdminActivity.this, R.color.white);

        tvAdminSignin = findViewById(R.id.tvAdminSignin);

        tvAdminSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginAdminActivity.this, DashboardAdmin.class);
                startActivity(intent);
            }
        });
    }
}