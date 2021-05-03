package com.example.timelapse.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.timelapse.R;
import com.example.timelapse.db.database.AbstractDataBase;
import com.example.timelapse.db.database.LocalBase;
import com.example.timelapse.object.WorkCalendarWithShift;
import com.example.timelapse.system.impl.CalendarViewImpl;
import com.example.timelapse.system.util.thread.AsyncCallObject;
import com.kizitonwose.calendarview.CalendarView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalendarView calendarView = findViewById(R.id.calendarViewer);
        CalendarViewImpl calendarView1 = new CalendarViewImpl(this, calendarView);
        AbstractDataBase db = Room.databaseBuilder(getApplicationContext(),
                LocalBase.class, "local-base").build();


        new AsyncCallObject<List<WorkCalendarWithShift>>() {
            @Override
            protected List<WorkCalendarWithShift> run() {
                return db.workCalendarWithShiftDao().getAll(); //получаем асинхронно данные из бд
            }

            @Override
            protected void postExecute(List<WorkCalendarWithShift> object) {
                calendarView1.setup(object); //после получения данных в другом потоке, возвращаемся и заполняем все в интерфейсе
                super.postExecute(object);
            }
        }.execute();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //  new TimeTablesRequestGet().execute();
    }

    /*private class RequestTask extends AsyncCallObject<TimeTable[]> {
        @Override
        protected TimeTable[] run() {
            try {
                final String url = "http://192.168.0.103:8083/";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                TimeTable[] timeTable = restTemplate.getForObject(url, TimeTable[].class);
                return timeTable;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }
            return null;
        }

        @Override
        public void postExecute(TimeTable[] timeTable) {
            TextView greetingIdText = findViewById(R.id.id_value);
            TextView greetingContentText = findViewById(R.id.content_value);
            greetingIdText.setText(timeTable[0].getId().toString());
            greetingContentText.setText(timeTable[0].getStatus());
        }
    }*/


}
