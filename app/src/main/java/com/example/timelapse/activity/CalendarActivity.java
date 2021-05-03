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
import com.example.timelapse.presenter.CalendarPresenter;
import com.example.timelapse.system.util.DateUtils;
import com.example.timelapse.view.CalendarView;

import java.util.List;

public class CalendarActivity extends AppCompatActivity implements CalendarView {
    CalendarPresenter presenter;

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
        List<WorkShift> shifts = calendar.getWorkShifts();
        if (shifts != null && shifts.size() != 0) {
            String dayTypeExtra = shifts.get(0).getDayType().getAbout();
            if (dayTypeExtra != null)
                dayTypeView.setText(dayTypeExtra);
            else dayTypeView.setText(DayType.DAY_OFF.getAbout());
        }
    }

    @Override
    public void putInfoElementsWorkDay(WorkCalendarWithShift calendar) {
        TextView dayTypeView = findViewById(R.id.time_shift_type_day);
        WorkShift shift = calendar.getWorkShifts().get(0);
        String dayTypeExtra = shift.getDayType().getAbout();
        if (dayTypeExtra != null)
            dayTypeView.setText(dayTypeExtra);
        else dayTypeView.setText(DayType.DAY_OFF.getAbout());

        TextView startTime = findViewById(R.id.time_shift_time_start);
        TextView endTime = findViewById(R.id.time_shift_time_end);

        String startTimeExtra = DateUtils.getFormattedStringTime(shift.getStartTime());
        startTime.setText(startTimeExtra);

        String endTimeExtra = DateUtils.getFormattedStringTime(shift.getEndTime());
        endTime.setText(endTimeExtra);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_shift_view);
        presenter = new CalendarPresenter(this);
        presenter.fillElements(getIntent());
        ImageView edit = findViewById(R.id.time_shift_edit_image);
        edit.setOnClickListener(l -> {
            Intent intent = new Intent(edit.getContext(), TimeShiftChangeActivity.class);
            intent.putExtra("DATE", intent.getStringExtra("DATE"));
            intent.putExtra("CALENDAR", intent.getStringExtra("CALENDAR"));
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView dateText = findViewById(R.id.time_shift_date_text);
    }
}
