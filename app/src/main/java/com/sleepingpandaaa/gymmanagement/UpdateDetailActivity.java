package com.sleepingpandaaa.gymmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class UpdateDetailActivity extends AppCompatActivity {


    private TextInputEditText fNameU,sNameU,ageU,heightU,weightU;
    private Spinner bloodGrpU,genderU,planU;
    String nameFU,nameLU,userAgeU,userHeightU,userWeightU,userBloodGrpU = "A+",userGenderU="Male",userPlanU="1 Month";
    private TextView saveBtnU,bg,gd,sp;
    private ProgressDialog loadingBar;
    private FirebaseAuth mAuth;
    private String currentUserId;
    private DatabaseReference userInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_detail);
        Tools.setSystemBarLight(UpdateDetailActivity.this);
        Tools.setSystemBarColor(UpdateDetailActivity.this, R.color.colorPrimaryDark);

        InitializeAll();

        bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bg.setText("BloodGrp : ");
                bloodGrpU.setVisibility(View.VISIBLE);
            }
        });

        gd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gd.setText("Gender : ");
                genderU.setVisibility(View.VISIBLE);
            }
        });

        sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.setText("Select Plan : ");
                planU.setVisibility(View.VISIBLE);
            }
        });
        saveBtnU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveData();
            }
        });
    }

    private void SaveData() {
        loadingBar.setTitle("Please wait");
        loadingBar.setMessage("While we are saving your data...");
        loadingBar.setCanceledOnTouchOutside(true);
        loadingBar.show();

        nameFU = fNameU.getText().toString();
        nameLU = sNameU.getText().toString();
        userAgeU = ageU.getText().toString();
        userHeightU = heightU.getText().toString();
        userWeightU = weightU.getText().toString();
        userBloodGrpU = bloodGrpU.getSelectedItem().toString();
        userGenderU = genderU.getSelectedItem().toString();
        userPlanU = planU.getSelectedItem().toString();


        if(TextUtils.isEmpty(nameFU)){
            Toast.makeText(this, "Please enter your first name", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(nameLU)){
            Toast.makeText(this, "Please enter your last name", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(userAgeU)){
            Toast.makeText(this, "Please enter your age", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(userHeightU)){
            Toast.makeText(this, "Please enter your height", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(userWeightU)){
            Toast.makeText(this, "Please enter your weight", Toast.LENGTH_SHORT).show();
        }else{
            HashMap<String, Object> userData = new HashMap<>();
            userData.put("nameFirst",nameFU);
            userData.put("lastName",nameLU);
            userData.put("userAge",userAgeU);
            userData.put("userHeight",userHeightU);
            userData.put("userWeight",userWeightU);
            userData.put("bloodGrp",userBloodGrpU);
            userData.put("gender",userGenderU);
            userData.put("plan",userPlanU);
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
            userData.put("date",date.split(" ")[0]);
            Log.d("Nice", "SaveData: " + userData);

            userInfo.child(currentUserId.toString()).setValue(userData);
            loadingBar.dismiss();
            startActivity(new Intent(getApplicationContext(),DashboardUser.class));
            finish();

        }
    }

    private void InitializeAll() {
        fNameU = findViewById(R.id.fNameU);
        sNameU = findViewById(R.id.sNameU);
        ageU = findViewById(R.id.ageU);
        heightU = findViewById(R.id.heightU);
        weightU = findViewById(R.id.weightU);
        bloodGrpU = findViewById(R.id.bloofGrpSU);
        genderU = findViewById(R.id.genderSU);
        planU = findViewById(R.id.planSU);
        saveBtnU = findViewById(R.id.saveBtnU);
        loadingBar = new ProgressDialog(this);
        bg = findViewById(R.id.bg);
        sp = findViewById(R.id.sp);
        gd = findViewById(R.id.gd);
        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        userInfo = FirebaseDatabase.getInstance().getReference().child("UserInfo");
        userInfo.child(currentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Log.d("Imp ", "onDataChange: " + dataSnapshot.child("date").getValue().toString());
                    fNameU.setText(dataSnapshot.child("nameFirst").getValue().toString());
                    sNameU.setText(dataSnapshot.child("lastName").getValue().toString());
                    ageU.setText(dataSnapshot.child("userAge").getValue().toString());
                    heightU.setText(dataSnapshot.child("userHeight").getValue().toString());
                    weightU.setText(dataSnapshot.child("userWeight").getValue().toString());
                    sp.setText("Plan Selected : " + dataSnapshot.child("plan").getValue().toString());
                    gd.setText("Gender : " + dataSnapshot.child("gender").getValue().toString());
                    bg.setText("BloodGrp : " + dataSnapshot.child("bloodGrp").getValue().toString());
                    userBloodGrpU = dataSnapshot.child("bloodGrp").getValue().toString();
                    userGenderU = dataSnapshot.child("gender").getValue().toString();
                    userPlanU = dataSnapshot.child("plan").getValue().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
