package com.example.timelapse.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timelapse.R;
import com.example.timelapse.view.CalendarView;

public class CalendarActivity extends AppCompatActivity implements CalendarView {


    @Override
    public void disableButtonsForNonWork() {
        ImageView notifButton = findViewById(R.id.time_shift_notification_image);
        notifButton.setEnabled(false);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_shift_view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView dateText = findViewById(R.id.time_shift_date_text);
        dateText.setText(getIntent().getStringExtra("DATE"));
    }
}
