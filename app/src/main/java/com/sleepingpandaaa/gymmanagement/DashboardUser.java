package com.sleepingpandaaa.gymmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashboardUser extends AppCompatActivity {

    ImageView ivAddDetails, ivDiet, ivProfile, ivLogout;
    private FirebaseAuth mAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference RootRef;
    private String currentUserId;
    private TextView addDetail;
    private boolean updatedProfile = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_user);
        Tools.setSystemBarLight(DashboardUser.this);
        Tools.setSystemBarColor(DashboardUser.this, R.color.colorPrimaryDark);

        InitializaAll();

        RootRef.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    if(dataSnapshot.hasChild(currentUserId)){
                        Toast.makeText(DashboardUser.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(DashboardUser.this, "Failure", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ivAddDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!updatedProfile) {
                    Intent intent = new Intent(DashboardUser.this, AddUserDetail.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(DashboardUser.this, UpdateDetailActivity.class);
                    startActivity(intent);
                }
            }
        });

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardUser.this, UserProfileActivity.class);
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

        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent loginIntent = new Intent(DashboardUser.this, StartActivity.class);
                loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(loginIntent);
            }
        });
    }

    public  void InitializaAll(){
        ivAddDetails = findViewById(R.id.ivAddDetails);
        ivDiet = findViewById(R.id.ivDiet);
        ivProfile = findViewById(R.id.ivProfile);
        ivLogout = findViewById(R.id.ivLogout);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mAuth.getCurrentUser();
        RootRef = FirebaseDatabase.getInstance().getReference();
        currentUserId = mFirebaseUser.getUid();
        addDetail = findViewById(R.id.addDetail);
//        Log.d("idddd", "InitializaAll: " + currentUserId);
        RootRef.child("UserInfo").child(currentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    if(dataSnapshot.hasChild("nameFirst")){
                        addDetail.setText("Update Detail");
                        updatedProfile = true;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}