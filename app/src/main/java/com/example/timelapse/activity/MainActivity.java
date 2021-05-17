package com.example.timelapse.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timelapse.R;
import com.example.timelapse.db.database.AbstractDataBase;
import com.example.timelapse.db.database.DBHelper;
import com.example.timelapse.object.WorkCalendarWithShift;
import com.example.timelapse.service.ObserveBroadcastRec;
import com.example.timelapse.service.ObserveTimeLapseService;
import com.example.timelapse.system.impl.calendar.CalendarViewImpl;
import com.example.timelapse.system.util.thread.AsyncCallObject;
import com.kizitonwose.calendarview.CalendarView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public final static String BROADCAST_OBSERVE = "android.intent.action.MAIN";
    private CalendarViewImpl calendarView1 = null;
    private AbstractDataBase db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalendarView calendarView = findViewById(R.id.calendarViewer);
        calendarView1 = new CalendarViewImpl(this, calendarView);
        db = DBHelper.getDB(getApplicationContext(), DBHelper.LOCAL_BASE);

        registerObserverService();
        updateCalendar(db, calendarView1);

    }

    @Override
    protected void onStart() {
        super.onStart();
        updateCalendar(db, calendarView1);
    }

    private void registerObserverService() {
        Intent intentObserveService = new Intent(this, ObserveTimeLapseService.class);
        ObserveBroadcastRec broadcastRec = new ObserveBroadcastRec() {
            @Override
            public void onReceive(Context context, Intent intent) {
                updateCalendar(db, calendarView1);
            }
        };
        IntentFilter intFilt = new IntentFilter(BROADCAST_OBSERVE);
        registerReceiver(broadcastRec, intFilt);
        ObserveTimeLapseService.enqueueWork(getApplicationContext(), intentObserveService);
    }

    private void updateCalendar(AbstractDataBase db, CalendarViewImpl calendarView1) {
        new AsyncCallObject<List<WorkCalendarWithShift>>() {
            @Override
            protected List<WorkCalendarWithShift> run() {
                return db.workCalendarWithShiftDao().getAll(); //получаем асинхронно данные из бд
            }

            @Override
            protected void postExecute(List<WorkCalendarWithShift> object) {
                calendarView1.setup(getApplicationContext(), object); //после получения данных в другом потоке, возвращаемся и заполняем все в интерфейсе
                super.postExecute(object);
            }
        }.execute();
    }


}
