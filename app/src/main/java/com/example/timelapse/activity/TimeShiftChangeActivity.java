package com.example.timelapse.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timelapse.R;
import com.example.timelapse.view.TimeShiftChangeView;

public class TimeShiftChangeActivity extends AppCompatActivity implements TimeShiftChangeView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_shift_change);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
