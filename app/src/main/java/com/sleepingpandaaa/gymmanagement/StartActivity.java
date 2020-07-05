package com.sleepingpandaaa.gymmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity {

    TextView tvLoginAsUser, tvLogInAsAdmin;
    ConstraintLayout constraint_layout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Tools.setSystemBarLight(StartActivity.this);
        Tools.setSystemBarColor(StartActivity.this, R.color.colorPrimaryDark);

        tvLoginAsUser = findViewById(R.id.tvLoginAsUser);
        tvLogInAsAdmin = findViewById(R.id.tvLoginAsAdmin);
        constraint_layout = findViewById(R.id.constraint_layout);
        mAuth = FirebaseAuth.getInstance();

        tvLoginAsUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        tvLogInAsAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, LoginAdminActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currenUser = mAuth.getCurrentUser();
        if(currenUser == null){
            //Donothing
        }else{
            if(currenUser.getUid().equals("MhpqOupASgTpy1twPgRXc0kwyCE3")){
                startActivity(new Intent(getApplicationContext(),DashboardAdmin.class));
                finish();
            }else{
                startActivity(new Intent(getApplicationContext(),DashboardUser.class));
                finish();
            }

        }
    }

}