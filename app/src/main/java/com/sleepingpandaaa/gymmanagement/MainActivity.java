package com.sleepingpandaaa.gymmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView tvLoginAsUser, tvLogInAsAdmin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tools.setSystemBarLight(MainActivity.this);
        Tools.setSystemBarColor(MainActivity.this, R.color.white);

        tvLoginAsUser = findViewById(R.id.tvLoginAsUser);
        tvLogInAsAdmin = findViewById(R.id.tvLoginAsAdmin);
        mAuth = FirebaseAuth.getInstance();

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