package com.sleepingpandaaa.gymmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfileActivity extends AppCompatActivity {

    private TextView tvName, tvGender, tvAge, tvBloodGroup, tvHeight, tvWeight,tvDate,tvPlan;
    private FirebaseAuth mAuth;
    private String currentUserId;
    private DatabaseReference userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Tools.setSystemBarLight(UserProfileActivity.this);
        Tools.setSystemBarColor(UserProfileActivity.this, R.color.colorPrimaryDark);

        tvName = findViewById(R.id.tvName);
        tvGender = findViewById(R.id.tvGender);
        tvAge = findViewById(R.id.tvAge);
        tvBloodGroup = findViewById(R.id.tvBloodGroup);
        tvHeight = findViewById(R.id.tvHeight);
        tvWeight = findViewById(R.id.tvWeight);
        tvDate = findViewById(R.id.tvDateOfJoining);
        tvPlan = findViewById(R.id.tvPlan);

        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        userInfo = FirebaseDatabase.getInstance().getReference().child("UserInfo");
        userInfo.child(currentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Log.d("Imp ", "onDataChange: " + dataSnapshot.child("date").getValue().toString());
                    tvName.setText(dataSnapshot.child("nameFirst").getValue().toString() + " " + dataSnapshot.child("lastName").getValue().toString());
                    tvAge.setText("Age: " + dataSnapshot.child("userAge").getValue().toString());
                    tvHeight.setText("Height: " + dataSnapshot.child("userHeight").getValue().toString() + " cm");
                    tvWeight.setText("Weight: " + dataSnapshot.child("userWeight").getValue().toString() + " kg");
                    tvPlan.setText("Plan Selected: " + dataSnapshot.child("plan").getValue().toString());
                    tvGender.setText("Gender: " + dataSnapshot.child("gender").getValue().toString());
                    tvBloodGroup.setText("BloodGrp: " + dataSnapshot.child("bloodGrp").getValue().toString());
                    tvDate.setText("Date: " + dataSnapshot.child("date").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}