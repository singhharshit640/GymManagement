package com.sleepingpandaaa.gymmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextView tvLoginAsUser, tvLogInAsAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tools.setSystemBarLight(MainActivity.this);
        Tools.setSystemBarColor(MainActivity.this, R.color.white);

        tvLoginAsUser = findViewById(R.id.tvLoginAsUser);
        tvLogInAsAdmin = findViewById(R.id.tvLoginAsAdmin);

        tvLoginAsUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        tvLogInAsAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginAdminActivity.class);
                startActivity(intent);
            }
        });
    }
}