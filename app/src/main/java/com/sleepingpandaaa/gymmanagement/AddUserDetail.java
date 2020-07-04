package com.sleepingpandaaa.gymmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AddUserDetail extends AppCompatActivity {

    private TextInputEditText fName,sName,age,height,weight;
    private Spinner bloodGrp,gender,plan;
    String nameF,nameL,userAge,userHeight,userWeight,userBloodGrp = "A+",userGender="Male",userPlan="1 Month";
    private TextView saveBtn;
    private ProgressDialog loadingBar;
    private FirebaseAuth mAuth;
    private String currentUserId;
    private DatabaseReference userInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_detail);
        Tools.setSystemBarLight(AddUserDetail.this);
        Tools.setSystemBarColor(AddUserDetail.this, R.color.colorPrimaryDark);

        InitializeAll();

        saveBtn.setOnClickListener(new View.OnClickListener() {
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

        nameF = fName.getText().toString();
        nameL = sName.getText().toString();
        userAge = age.getText().toString();
        userHeight = height.getText().toString();
        userWeight = weight.getText().toString();
        userBloodGrp = bloodGrp.getSelectedItem().toString();
        userGender = gender.getSelectedItem().toString();
        userPlan = plan.getSelectedItem().toString();

        /*bloodGrp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });*/

/*
        gender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });
*/

/*
        plan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });
*/

        if(TextUtils.isEmpty(nameF)){
            Toast.makeText(this, "Please enter your first name", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(nameL)){
            Toast.makeText(this, "Please enter your last name", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(userAge)){
            Toast.makeText(this, "Please enter your age", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(userHeight)){
            Toast.makeText(this, "Please enter your height", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(userWeight)){
            Toast.makeText(this, "Please enter your weight", Toast.LENGTH_SHORT).show();
        }else{
            HashMap<String, Object> userData = new HashMap<>();
            userData.put("nameFirst",nameF);
            userData.put("lastName",nameL);
            userData.put("userAge",userAge);
            userData.put("userHeight",userHeight);
            userData.put("userWeight",userWeight);
            userData.put("bloodGrp",userBloodGrp);
            userData.put("gender",userGender);
            userData.put("plan",userPlan);
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
        fName = findViewById(R.id.fName);
        sName = findViewById(R.id.sName);
        age = findViewById(R.id.age);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        bloodGrp = findViewById(R.id.bloofGrpS);
        gender = findViewById(R.id.genderS);
        plan = findViewById(R.id.planS);
        saveBtn = findViewById(R.id.saveBtn);
        loadingBar = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        userInfo = FirebaseDatabase.getInstance().getReference().child("UserInfo");

    }


}