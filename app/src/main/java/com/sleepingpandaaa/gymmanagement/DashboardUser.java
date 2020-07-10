package com.sleepingpandaaa.gymmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class DashboardUser extends AppCompatActivity {

    ImageView ivAddDetails, ivDiet, ivProfile, ivLogout, payNow;
    private FirebaseAuth mAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference RootRef;
    private String currentUserId;
    private TextView addDetail;
    private boolean updatedProfile = false;
    private TextView dateLeftTv;
    private int totalDays;
    private String date;
    ProgressDialog progressDialog;
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
        payNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(totalDays < 99){
                    FirebaseDatabase.getInstance().getReference().child("UserInfo").child(currentUserId).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                Log.d("Imp ", "onDataChange: " + dataSnapshot.child("date").getValue().toString());
                                HashMap<String,Object> map = new HashMap<>();
                                map.put("nameFirst" ,dataSnapshot.child("nameFirst").getValue().toString());
                                map.put("nameLast" , dataSnapshot.child("lastName").getValue().toString());
                                map.put("emailId", dataSnapshot.child("emailId").getValue().toString());
                                map.put("date",date);
                                RootRef.child("Stats").child(currentUserId + date).setValue(map);
                                Toast.makeText(DashboardUser.this, "Payment Successfull", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }else{
                    Toast.makeText(DashboardUser.this, "You don't need to pay now", Toast.LENGTH_LONG).show();
                }

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
        dateLeftTv = findViewById(R.id.dateLeft);
        payNow = findViewById(R.id.payNow);
//        Log.d("idddd", "InitializaAll: " + currentUserId);

        RootRef.child("Users").child(currentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("suspended")){
                    Toast.makeText(DashboardUser.this, "Your Account has been suspended...", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                    finish();
                }else{

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        RootRef.child("UserInfo").child(currentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    if(dataSnapshot.hasChild("nameFirst")){
                        addDetail.setText("Update Detail");
                        updatedProfile = true;
                        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                        String registerDate = dataSnapshot.child("date").getValue().toString();
                        int month = Integer.parseInt(date.substring(5,7)) - (Integer.parseInt(registerDate.substring(5,7)));
                        int days = Integer.parseInt(date.substring(8,date.length())) - (Integer.parseInt(registerDate.substring(8,date.length())));
                        int timeGone = month*60 + days;
                        int plan = Integer.parseInt(dataSnapshot.child("plan").getValue().toString().split(" ")[0]);
                        totalDays = plan*30 - timeGone;
                        dateLeftTv.setText("You have " + totalDays + " left");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}