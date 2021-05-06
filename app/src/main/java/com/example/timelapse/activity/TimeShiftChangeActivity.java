package com.example.timelapse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timelapse.R;
import com.example.timelapse.db.database.AbstractDataBase;
import com.example.timelapse.db.database.DBManager;
import com.example.timelapse.object.DayType;
import com.example.timelapse.object.WorkCalendar;
import com.example.timelapse.object.WorkCalendarWithShift;
import com.example.timelapse.object.WorkShift;
import com.example.timelapse.presenter.TimeShiftMainPresenter;
import com.example.timelapse.system.util.DateUtils;
import com.example.timelapse.system.util.thread.AsyncCallObject;
import com.example.timelapse.system.util.thread.AsyncCallVoid;
import com.example.timelapse.view.TimeShiftMainView;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class TimeShiftChangeActivity extends AppCompatActivity implements TimeShiftMainView {
    TimeShiftMainPresenter presenter;
    Spinner dayTypeView;
    AbstractDataBase db;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_shift_change);
        db = DBManager.getDB(getApplicationContext(), DBManager.LOCAL_BASE);
        date = DateUtils.localDateToDate((LocalDate) getIntent().getSerializableExtra("DATE"));
        dayTypeView = findViewById(R.id.time_shift_change_day_type_spinner);
        presenter = new TimeShiftMainPresenter(this, this);
        presenter.fillElements(getIntent());
        setClickSaveButton();
        setOnSelectedDayType();
        setClickCancelButton();
    }


    private void setOnSelectedDayType() {
        Spinner spinner = findViewById(R.id.time_shift_change_day_type_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                visibleElementsForWork(spinner.getSelectedItem().toString().equals(DayType.WORK.getAbout()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub

            }
        });
    }

    private void setClickCancelButton() {
        Button cancelButton = findViewById(R.id.time_shift_change_cancel_button);
        cancelButton.setOnClickListener(l -> {
            Intent intent = new Intent(dayTypeView.getContext(), TimeShiftMainActivity.class);
            intent.putExtra("DATE", DateUtils.dateToLocalDate(date));
            startActivity(intent);
        });
    }

    private void setClickSaveButton() {
        Button saveButton = findViewById(R.id.time_shift_change_save_button);

        saveButton.setOnClickListener(l -> {

            Date date = DateUtils.localDateToDate((LocalDate) getIntent().getSerializableExtra("DATE"));
            new AsyncCallObject<WorkCalendarWithShift>() {

                @Override
                protected WorkCalendarWithShift run() {
                    return db.workCalendarWithShiftDao().getByDate(date);
                }

                @Override
                protected void postExecute(WorkCalendarWithShift object) {
                    super.postExecute(object);
                    fillWorkCalendarWithSift(object);
                }
            }.execute();
        });
    }

    private void fillWorkCalendarWithSift(WorkCalendarWithShift object) {
        EditText startEdit = findViewById(R.id.time_shift_change_start_edit);
        EditText endEdit = findViewById(R.id.time_shift_change_end_edit);
        WorkShift workShift = null;
        WorkCalendar workCalendar = null;
        boolean isNewShift = false;
        boolean isNewCalendar = false;
        if (object != null && object.getWorkCalendar() != null) {
            workCalendar = object.getWorkCalendar();
            workShift = object.getWorkShift();
            if (workShift != null) {
                String dayTypeChanged = dayTypeView.getSelectedItem().toString();
                workShift.setDayType(dayTypeChanged);
                if (dayTypeChanged.equals(DayType.WORK.getAbout())) {
                    workShift.setStartTime(Time.valueOf(startEdit.getText().toString() + ":00"));
                    workShift.setEndTime(Time.valueOf(endEdit.getText().toString() + ":00"));
                }
            } else {
                workCalendar = object.getWorkCalendar();
                workShift = new WorkShift();
                String dayTypeChanged = dayTypeView.getSelectedItem().toString();
                workShift.setDayType(dayTypeChanged);
                if (dayTypeChanged.equals(DayType.WORK.getAbout())) {
                    workShift.setStartTime(Time.valueOf(startEdit.getText().toString() + ":00"));
                    workShift.setEndTime(Time.valueOf(endEdit.getText().toString() + ":00"));
                }
                isNewShift = true;
            }
        } else {
            workCalendar = new WorkCalendar(new Random().nextLong(), date);
            workShift = new WorkShift();
            workShift.setCalendarId(workCalendar.getId());
            String dayTypeChanged = dayTypeView.getSelectedItem().toString();
            workShift.setDayType(dayTypeChanged);

            if (dayTypeChanged.equals(DayType.WORK.getAbout())) {
                workShift.setStartTime(Time.valueOf(startEdit.getText().toString() + ":00"));
                workShift.setEndTime(Time.valueOf(endEdit.getText().toString() + ":00"));
            }
            isNewShift = true;
            isNewCalendar = true;
        }


        WorkShift finalWorkShift = workShift;
        WorkCalendar finalWorkCalendar = workCalendar;
        boolean finalIsNewShift = isNewShift;
        boolean finalIsNewCalendar = isNewCalendar;
        new AsyncCallVoid() {

            @Override
            protected Void run() {
                if (finalIsNewCalendar)
                    db.workCalendarDao().insert(finalWorkCalendar);
                else db.workCalendarDao().update(finalWorkCalendar);
                if (finalIsNewShift)
                    db.workShiftDao().insert(finalWorkShift);
                else db.workShiftDao().update(finalWorkShift);
                return null;
            }

        }.execute();
        Intent intent = new Intent(startEdit.getContext(), TimeShiftMainActivity.class);
        intent.putExtra("DATE", DateUtils.dateToLocalDate(date));
        //intent.putExtra("CALENDAR", intent.getStringExtra("CALENDAR"));
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.fillElements(getIntent());
    }

    @Override
    public void visibleElementsForWork(boolean t) {
        TextView startText = findViewById(R.id.time_shift_change_start_text);
        EditText startEdit = findViewById(R.id.time_shift_change_start_edit);
        TextView endText = findViewById(R.id.time_shift_change_end_text);
        EditText endEdit = findViewById(R.id.time_shift_change_end_edit);
        if (!t) {
            startText.setVisibility(View.INVISIBLE);
            startEdit.setVisibility(View.INVISIBLE);
            endText.setVisibility(View.INVISIBLE);
            endEdit.setVisibility(View.INVISIBLE);
        } else {
            startText.setVisibility(View.VISIBLE);
            startEdit.setVisibility(View.VISIBLE);
            endText.setVisibility(View.VISIBLE);
            endEdit.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void fillDate() {
        TextView date = findViewById(R.id.time_shift_date_change_text);
        LocalDate localDate = DateUtils.dateToLocalDate(this.date);
        date.setText(DateUtils.dateToDisplayString(DateUtils.localDateToDate(localDate)));
    }

    @Override
    public void putInfoElementsDayOff(WorkCalendarWithShift calendar) {
        WorkShift shift = calendar.getWorkShift();
        if (shift != null) {
            String dayTypeExtra = shift.getDayType().getAbout();
            if (dayTypeExtra != null)
                selectValue(dayTypeView, dayTypeExtra);
            else selectValue(dayTypeView, DayType.DAY_OFF.getAbout());
        } else selectValue(dayTypeView, DayType.DAY_OFF.getAbout());
    }

    @Override
    public void putInfoElementsWorkDay(WorkCalendarWithShift calendar) {

    }

    private void selectValue(Spinner spinner, Object value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(value)) {
                spinner.setSelection(i);
                break;
            }
        }
    }
}
