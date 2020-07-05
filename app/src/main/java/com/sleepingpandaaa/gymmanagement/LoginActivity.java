package com.sleepingpandaaa.gymmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private TextView tvSignUp, tvSignIn;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;
    private TextInputEditText emailID;
    private TextInputEditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Tools.setSystemBarLight(this);
        Tools.setSystemBarColor(this, R.color.white);

        InitializeAll();

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailID.getText().toString();
                String passwor = password.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(LoginActivity.this,"Please enter email...",Toast.LENGTH_SHORT)
                            .show();
                }
                else if(TextUtils.isEmpty(passwor)){

                    Toast.makeText(LoginActivity.this,"Please enter password...",Toast.LENGTH_SHORT)
                            .show();
                }
                else{
                    loadingBar.setTitle("Signing You In");
                    loadingBar.setMessage("Please wait...");
                    loadingBar.setCanceledOnTouchOutside(true);
                    loadingBar.show();

                    mAuth.signInWithEmailAndPassword(email,passwor)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        loadingBar.dismiss();
                                        startActivity(new Intent(getApplicationContext(),DashboardUser.class));
                                        finish();
                                    }else{
                                        Log.d("DEBUG", "onComplete: " + task.getException());
                                        Toast.makeText(LoginActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                        Log.d("HiThere", "onComplete: ");
                                    }
                                }
                            });

                }
            }
        });

    }

    private void InitializeAll() {
        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);
        tvSignUp = findViewById(R.id.tvSignUp);
        tvSignIn = findViewById(R.id.tvSignIn);
        emailID = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
    }

}