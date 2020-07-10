package com.sleepingpandaaa.gymmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManageActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String currentUserId;
    private RecyclerView User_list;
    private DatabaseReference mDatabase;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        Tools.setSystemBarLight(ManageActivity.this);
        Tools.setSystemBarColor(ManageActivity.this, R.color.colorPrimaryDark);

        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("UserInfo");

        User_list = findViewById(R.id.user_list);
        User_list.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadingBar = new ProgressDialog(this);
        loadingBar.setTitle("Loading data");
        loadingBar.setMessage("Please wait...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        FirebaseRecyclerOptions<User> options =
                new FirebaseRecyclerOptions.Builder<User>()
                        .setQuery(mDatabase,User.class)
                        .build();

        FirebaseRecyclerAdapter<User,UserListViewHolder> adapter
                = new FirebaseRecyclerAdapter<User, UserListViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull UserListViewHolder holder, int position, @NonNull User model) {
                final String userIds = getRef(position).getKey();
                holder.idTV.setText(model.getEmailId());
                holder.currentPlanTV.setText(model.getPlan());
                holder.firstNameTV.setText(model.getNameFirst());
                holder.lastNameTV.setText(model.getLastName());
                holder.removeUser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        removeUser(userIds);
                    }
                });
            }

            @NonNull
            @Override
            public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.manage, viewGroup, false);
                UserListViewHolder viewHolder = new UserListViewHolder(view);
                return viewHolder;
            }

            @Override
            public void onDataChanged() {
                super.onDataChanged();
                loadingBar.dismiss();
            }
        };

        User_list.setAdapter(adapter);
        adapter.startListening();
    }

    private void removeUser(String userId) {
        DatabaseReference del = FirebaseDatabase.getInstance().getReference().child("UserInfo");
        del.child(userId).removeValue();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
        ref.child(userId).child("suspended").setValue("");

    }


    public static class UserListViewHolder extends RecyclerView.ViewHolder {
        TextView idTV, firstNameTV, lastNameTV,currentPlanTV,removeUser,userDiet;

        public UserListViewHolder(@NonNull View itemView) {
            super(itemView);
            idTV = itemView.findViewById(R.id.tvID);
            firstNameTV = itemView.findViewById(R.id.tvManageFirstName);
            lastNameTV = itemView.findViewById(R.id.tvManageSecondName);
            currentPlanTV = itemView.findViewById(R.id.tvManagePlan);
            removeUser = itemView.findViewById(R.id.tvRemoveUser);

        }
    }
}