package com.sleepingpandaaa.gymmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class DashboardUser extends AppCompatActivity {

    ImageView ivAddDetails, ivDiet, ivPlan, ivProfile, ivLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_user);
        Tools.setSystemBarLight(DashboardUser.this);
        Tools.setSystemBarColor(DashboardUser.this, R.color.colorPrimaryDark);

        ivAddDetails = findViewById(R.id.ivAddDetails);
        ivDiet = findViewById(R.id.ivDiet);
        ivPlan = findViewById(R.id.ivPlan);
        ivProfile = findViewById(R.id.ivProfile);
        ivLogout = findViewById(R.id.ivLogout);

        ivAddDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardUser.this, AddUserDetail.class);
                startActivity(intent);
            }
        });

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardUser.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });

        ivPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardUser.this, CurrentPlanActivity.class);
                startActivity(intent);
            }
        });

        ivDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardUser.this, DietActivity.class);
                startActivity(intent);
            }
        });


    }
}