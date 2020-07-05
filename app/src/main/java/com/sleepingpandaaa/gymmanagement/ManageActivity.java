package com.sleepingpandaaa.gymmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManageActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String currentUserId;
    private RecyclerView User_list;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        Tools.setSystemBarLight(ManageActivity.this);
        Tools.setSystemBarColor(ManageActivity.this, R.color.colorPrimaryDark);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("UserInfo");
//        mDatabase.keepSynced(true);
        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();

        User_list = findViewById(R.id.User_list);
        User_list.setHasFixedSize(true);
        User_list.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<UserList, UserListViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<UserList, UserListViewHolder>
                        (UserList.class,R.layout.manage, UserListViewHolder.class, mDatabase) {
            @Override
            protected void populateViewHolder(UserListViewHolder userListViewHolder, UserList userList, int i) {

                userListViewHolder.setUid("ID: " + userList.getUid());
                userListViewHolder.setNameFirst("Name: " + userList.getNameFirst());
                userListViewHolder.setNameLast(userList.getLastName());
                userListViewHolder.setPlan("Current Plan: " + userList.getPlan());


            }
        };
        User_list.setAdapter(firebaseRecyclerAdapter);

    }

    public static class UserListViewHolder extends RecyclerView.ViewHolder
    {
        View mView;
        public UserListViewHolder(View view) {
            super(view);
            mView = view;
        }

        public void setUid(String Uid)
        {
            TextView uid = mView.findViewById(R.id.tvID);
            uid.setText(Uid);
        }
        public void setNameFirst(String nameFirst)
        {
            TextView firstname = mView.findViewById(R.id.tvManageFirstName);
            firstname.setText(nameFirst);
        }
        public void setNameLast(String lastName)
        {
            TextView last = mView.findViewById(R.id.tvManageSecondName);
            last.setText(lastName);
        }
        public void setPlan(String plan)
        {
            TextView currentplan = mView.findViewById(R.id.tvManagePlan);
            currentplan.setText(plan);
        }
    }
}