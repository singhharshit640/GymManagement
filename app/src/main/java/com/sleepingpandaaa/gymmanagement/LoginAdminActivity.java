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

public class LoginAdminActivity extends AppCompatActivity {

    private TextView tvAdminSignin;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;
    private TextInputEditText etAdminUsername;
    private TextInputEditText etAdminPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        Tools.setSystemBarLight(LoginAdminActivity.this);
        Tools.setSystemBarColor(LoginAdminActivity.this, R.color.white);

        InitializeAll();


        tvAdminSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Adminemail = etAdminUsername.getText().toString();
                String Adminpassword = etAdminPassword.getText().toString();

                if(TextUtils.isEmpty(Adminemail)){
                    Toast.makeText(LoginAdminActivity.this,"Please enter Email",Toast.LENGTH_SHORT)
                            .show();
                }
                else if(TextUtils.isEmpty(Adminpassword)){

                    Toast.makeText(LoginAdminActivity.this,"Please enter Password",Toast.LENGTH_SHORT)
                            .show();
                }
                else{
                    loadingBar.setTitle("Signing You In");
                    loadingBar.setMessage("Please wait...");
                    loadingBar.setCanceledOnTouchOutside(true);
                    loadingBar.show();

                    mAuth.signInWithEmailAndPassword(Adminemail,Adminpassword)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        loadingBar.dismiss();
                                        startActivity(new Intent(getApplicationContext(),DashboardAdmin.class));
                                        finish();
                                    }else{
                                        Log.d("DEBUG", "onComplete: " + task.getException());
                                        Toast.makeText(LoginAdminActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
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
        tvAdminSignin = findViewById(R.id.tvAdminSignin);
        etAdminUsername = findViewById(R.id.etAdminUsername);
        etAdminPassword = findViewById(R.id.etAdminPassword);
    }
}