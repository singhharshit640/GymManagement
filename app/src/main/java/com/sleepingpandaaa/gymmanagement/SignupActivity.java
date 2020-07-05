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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    TextView tvSignIn1,tvSignUp1;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;
    private TextInputEditText emailID1;
    private TextInputEditText password1;
    private DatabaseReference RootRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Tools.setSystemBarLight(this);
        Tools.setSystemBarColor(this, R.color.white);

        InitializeAll();

        tvSignIn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
        tvSignUp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendUserToUserActivity();
            }
        });
    }

    private void SendUserToUserActivity() {
        String email = emailID1.getText().toString();
        String password = password1.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(SignupActivity.this,"Please enter Email",Toast.LENGTH_SHORT)
                    .show();
        }
        else if(TextUtils.isEmpty(password)){

            Toast.makeText(SignupActivity.this,"Please enter Password",Toast.LENGTH_SHORT)
                    .show();
        }
        else{
            loadingBar.setTitle("Signing You In");
            loadingBar.setMessage("Please wait...");
            loadingBar.setCanceledOnTouchOutside(true);
            loadingBar.show();

            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                String currentUserId = mAuth.getCurrentUser().getUid();
                                RootRef.child("Users").child(currentUserId).setValue("");
                                Toast.makeText(SignupActivity.this,"Account Created Successfully!",Toast.LENGTH_SHORT)
                                        .show();
                                loadingBar.dismiss();
                                startActivity(new Intent(getApplicationContext(),DashboardUser.class));
                            }
                            else{
                                loadingBar.dismiss();
                                String message = task.getException().toString();
                                Toast.makeText(SignupActivity.this, "Error : " + message, Toast.LENGTH_SHORT)
                                        .show();
                            }
                        }
                    });
        }
    }

    private void InitializeAll() {
        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);
        emailID1 = findViewById(R.id.etUsername1);
        password1 = findViewById(R.id.etPassword1);
        tvSignIn1 = findViewById(R.id.tvSignIn1);
        tvSignUp1 = findViewById(R.id.tvSignup1);
        RootRef = FirebaseDatabase.getInstance().getReference();
    }

}