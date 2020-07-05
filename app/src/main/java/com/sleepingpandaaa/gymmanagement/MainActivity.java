package com.sleepingpandaaa.gymmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView ivSplashScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivSplashScreen = findViewById(R.id.ivSplashScreen);

        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        ivSplashScreen.startAnimation(animation1);


        Handler h = new Handler();

        h.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(MainActivity.this,StartActivity.class);
                startActivity(intent);
                finish();

            }
        },1200);
    }

}