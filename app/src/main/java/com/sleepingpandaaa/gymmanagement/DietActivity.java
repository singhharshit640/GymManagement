package com.sleepingpandaaa.gymmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;

public class DietActivity extends AppCompatActivity {

    TextView tvBreakfastDiet, tvLunchDiet, tvDinnerDiet;
    Spinner spDietPlan;
    String breakfast;
    String lunch;
    String dinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);
        Tools.setSystemBarLight(DietActivity.this);
        Tools.setSystemBarColor(DietActivity.this, R.color.colorPrimaryDark);

        spDietPlan = findViewById(R.id.spDietPlan);
        tvBreakfastDiet = findViewById(R.id.tvBreakfastDiet);
        tvLunchDiet = findViewById(R.id.tvLunchDiet);
        tvDinnerDiet = findViewById(R.id.tvDinnerDiet);

        spDietPlan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0)
                {
                    breakfast = getResources().getString(R.string.WLbreakfast);
                    lunch = getResources().getString(R.string.WLlunch);
                    dinner = getResources().getString(R.string.WLdinner);
                    tvBreakfastDiet.setText(breakfast);
                    tvLunchDiet.setText(lunch);
                    tvDinnerDiet.setText(dinner);
                }

                if (i == 1)
                {
                    breakfast = getResources().getString(R.string.WGbreakfast);
                    lunch = getResources().getString(R.string.WGlunch);
                    dinner = getResources().getString(R.string.WGdinner);
                    tvBreakfastDiet.setText(breakfast);
                    tvLunchDiet.setText(lunch);
                    tvDinnerDiet.setText(dinner);
                }

                if (i == 2)
                {
                    breakfast = getResources().getString(R.string.MGbreakfast);
                    lunch = getResources().getString(R.string.MGlunch);
                    dinner = getResources().getString(R.string.MGdinner);
                    tvBreakfastDiet.setText(breakfast);
                    tvLunchDiet.setText(lunch);
                    tvDinnerDiet.setText(dinner);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}