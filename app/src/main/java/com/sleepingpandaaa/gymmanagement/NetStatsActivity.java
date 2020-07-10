package com.sleepingpandaaa.gymmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NetStatsActivity extends AppCompatActivity {
    private TextView id,name,date;
    private RecyclerView statsRecyclerView;
    private FirebaseAuth mAuth;
    private String currentUserId;
    private DatabaseReference mDatabase;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_stats);
        Tools.setSystemBarLight(NetStatsActivity.this);
        Tools.setSystemBarColor(NetStatsActivity.this, R.color.colorPrimaryDark);

        statsRecyclerView = findViewById(R.id.statsRecyclerView);
        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Stats");

        statsRecyclerView = findViewById(R.id.statsRecyclerView);
        statsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

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

        FirebaseRecyclerAdapter<User, StatsUserListViewHolder> adapter
                = new FirebaseRecyclerAdapter<User,StatsUserListViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull StatsUserListViewHolder holder, int position, @NonNull User model) {
                holder.idTV.setText(model.getEmailId());
                holder.NameTV.setText(model.getNameFirst());
                holder.date.setText(model.getDate());
            }

            @NonNull
            @Override
            public StatsUserListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.netstats, viewGroup, false);
                StatsUserListViewHolder viewHolder = new StatsUserListViewHolder(view);
                return viewHolder;
            }

            @Override
            public void onDataChanged() {
                super.onDataChanged();
                loadingBar.dismiss();
            }
        };

        statsRecyclerView.setAdapter(adapter);
        adapter.startListening();
    }


    public static class StatsUserListViewHolder extends RecyclerView.ViewHolder {
        TextView idTV, NameTV,date;

        public StatsUserListViewHolder(@NonNull View itemView) {
            super(itemView);
            idTV = itemView.findViewById(R.id.tvIDS);
            NameTV = itemView.findViewById(R.id.tvManageNameS);
            date = itemView.findViewById(R.id.dateS);
        }
    }

}