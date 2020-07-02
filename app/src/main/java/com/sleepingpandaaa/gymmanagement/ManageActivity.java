package com.sleepingpandaaa.gymmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ManageActivity extends AppCompatActivity {

    TextView tvRemoveUser, tvUserDiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        Tools.setSystemBarLight(ManageActivity.this);
        Tools.setSystemBarColor(ManageActivity.this, R.color.colorPrimaryDark);

        tvRemoveUser = findViewById(R.id.tvRemoveUser);
        tvUserDiet = findViewById(R.id.tvUserDiet);

        tvUserDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageActivity.this, UserDietActivity.class);
                startActivity(intent);
            }
        });

    }
}