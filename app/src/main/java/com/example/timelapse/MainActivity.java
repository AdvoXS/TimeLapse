package com.example.timelapse;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timelapse.system.impl.CalendarViewImpl;
import com.kizitonwose.calendarview.CalendarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalendarView calendarView = findViewById(R.id.calendarViewer);
        CalendarViewImpl calendarView1 = new CalendarViewImpl(calendarView);
        calendarView1.setup();

       /* CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener((CalendarView view, int year, int month,
                                              int dayOfMonth) -> {
                    Intent intent = new Intent(view.getContext(), CalendarActivity.class);
                    intent.putExtra("DATE", DateUtils.dateToDisplayString(new GregorianCalendar(2021, 1, 2).getTime()));
                    startActivity(intent);
                }
        );*/
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
