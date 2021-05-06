package com.example.timelapse.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timelapse.R;
import com.example.timelapse.object.DayType;
import com.example.timelapse.object.WorkCalendarWithShift;
import com.example.timelapse.object.WorkShift;
import com.example.timelapse.presenter.TimeShiftMainPresenter;
import com.example.timelapse.system.util.DateUtils;
import com.example.timelapse.view.TimeShiftMainView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class TimeShiftMainActivity extends AppCompatActivity implements TimeShiftMainView {
    TimeShiftMainPresenter presenter;
    LocalDate localDate;

    @Override
    public void visibleElementsForWork(boolean flag) {
        TextView startTime = findViewById(R.id.time_shift_time_start);
        TextView endTime = findViewById(R.id.time_shift_time_end);
        TextView timeSeparator = findViewById(R.id.time_shift_time_separator);
        TextView typeDay = findViewById(R.id.time_shift_type_day);

        if (!flag) {
            startTime.setVisibility(View.INVISIBLE);
            endTime.setVisibility(View.INVISIBLE);
            timeSeparator.setVisibility(View.INVISIBLE);
            typeDay.setTextColor(Color.parseColor("#F44336"));
        } else {
            startTime.setVisibility(View.VISIBLE);
            endTime.setVisibility(View.VISIBLE);
            timeSeparator.setVisibility(View.VISIBLE);
            typeDay.setTextColor(Color.parseColor("#03A9F4"));
        }
    }

    @Override
    public void putInfoElementsDayOff(WorkCalendarWithShift calendar) {
        TextView dayTypeView = findViewById(R.id.time_shift_type_day);
        WorkShift shift = calendar.getWorkShift();
        if (shift != null) {
            String dayTypeExtra = shift.getDayType().getAbout();
            if (dayTypeExtra != null)
                dayTypeView.setText(dayTypeExtra);
            else dayTypeView.setText(DayType.DAY_OFF.getAbout());
        }
    }

    @Override
    public void putInfoElementsWorkDay(WorkCalendarWithShift calendar) {
        TextView dayTypeView = findViewById(R.id.time_shift_type_day);
        WorkShift shift = calendar.getWorkShift();
        String dayTypeExtra = shift.getDayType().getAbout();
        if (dayTypeExtra != null)
            dayTypeView.setText(dayTypeExtra);
        else dayTypeView.setText(DayType.DAY_OFF.getAbout());

        TextView startTime = findViewById(R.id.time_shift_time_start);
        TextView endTime = findViewById(R.id.time_shift_time_end);

        String startTimeExtra = new SimpleDateFormat("HH:mm").format(shift.getStartTime());
        startTime.setText(startTimeExtra);

        String endTimeExtra = new SimpleDateFormat("HH:mm").format(shift.getEndTime());
        endTime.setText(endTimeExtra);
    }

    @Override
    public void fillDate() {
        TextView date = findViewById(R.id.time_shift_date_text);
        localDate = (LocalDate) getIntent().getSerializableExtra("DATE");
        date.setText(DateUtils.dateToDisplayString(DateUtils.localDateToDate(localDate)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_shift_view);
        presenter = new TimeShiftMainPresenter(this, this);
        presenter.fillElements(getIntent());
        ImageView edit = findViewById(R.id.time_shift_edit_image);
        edit.setOnClickListener(l -> {
            Intent intent = new Intent(edit.getContext(), TimeShiftChangeActivity.class);
            intent.putExtra("DATE", localDate);
            intent.putExtra("CALENDAR", intent.getStringExtra("CALENDAR"));
            startActivity(intent);
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        presenter.fillElements(getIntent());
    }
}
