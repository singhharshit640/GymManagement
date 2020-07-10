package com.sleepingpandaaa.gymmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PaymentActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String currentUserId;
    private RecyclerView payment_list;
    private DatabaseReference mDatabase;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Tools.setSystemBarLight(PaymentActivity.this);
        Tools.setSystemBarColor(PaymentActivity.this, R.color.colorPrimaryDark);

        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("UserInfo");

        payment_list = findViewById(R.id.paymentRecyclerView);
        payment_list.setLayoutManager(new LinearLayoutManager(this));

    }
    @Override
    protected void onStart() {
        super.onStart();
        loadingBar = new ProgressDialog(this);
        loadingBar.setTitle("Loading data");
        loadingBar.setMessage("Please wait...");
        loadingBar.setCanceledOnTouchOutside(true);
        loadingBar.show();

        FirebaseRecyclerOptions<User> options =
                new FirebaseRecyclerOptions.Builder<User>()
                        .setQuery(mDatabase,User.class)
                        .build();

        FirebaseRecyclerAdapter<User, UserListViewHolder> adapter
                = new FirebaseRecyclerAdapter<User, UserListViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final UserListViewHolder holder, int position, @NonNull final User model) {
                holder.idTV.setText(model.getEmailId());
                holder.name.setText(model.getNameFirst());
                holder.plan.setText(model.getPlan());
                holder.dof.setText(model.getDate());
                final String userIds = getRef(position).getKey();

                FirebaseDatabase.getInstance().getReference().child("UserInfo").child(userIds).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                            String registerDate = dataSnapshot.child("date").getValue().toString();
                            int month = Integer.parseInt(date.substring(5,7)) - (Integer.parseInt(registerDate.substring(5,7)));
                            int days = Integer.parseInt(date.substring(8,date.length())) - (Integer.parseInt(registerDate.substring(8,date.length())));
                            int timeGone = month*60 + days;
                            int plan = Integer.parseInt(dataSnapshot.child("plan").getValue().toString().split(" ")[0]);
                            int totalDays = plan*30 - timeGone;
                            holder.payment.setText(totalDays + " days left");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @NonNull
            @Override
            public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.payment, viewGroup,false);
                UserListViewHolder viewHolder = new UserListViewHolder(view);
                return viewHolder;
            }

            @Override
            public void onDataChanged() {
                super.onDataChanged();
                loadingBar.dismiss();
            }

        };

        payment_list.setAdapter(adapter);
        adapter.startListening();
    }

    private void AskAdmin(){

    }


    public static class UserListViewHolder extends RecyclerView.ViewHolder {
        TextView idTV, name,dof,plan,payment;

        public UserListViewHolder(@NonNull View itemView) {
            super(itemView);
            idTV = itemView.findViewById(R.id.tvIDp);
            name = itemView.findViewById(R.id.tvManageNamep);
            dof = itemView.findViewById(R.id.dateOfJoining);
            plan = itemView.findViewById(R.id.PlanPayment);
            payment = itemView.findViewById(R.id.tvSetPaymentp);
        }
    }


}