package com.example.timelapse.activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

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
    private NotificationManagerCompat notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalendarView calendarView = findViewById(R.id.calendarViewer);
        calendarView1 = new CalendarViewImpl(this, calendarView);
        db = DBHelper.getDB(getApplicationContext(), DBHelper.LOCAL_BASE);
        registerObserverService();
        registerNotificationChannel();

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
                notificationManager.notify(3000, getObserveNotification().build());
            }
        };
        IntentFilter intFilt = new IntentFilter(BROADCAST_OBSERVE);
        registerReceiver(broadcastRec, intFilt);
        ObserveTimeLapseService.enqueueWork(getApplicationContext(), intentObserveService);
    }

    private void registerNotificationChannel() {
        notificationManager = NotificationManagerCompat.from(this);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String CHANNEL_ID = "channelID";
            CharSequence name = "channelID";
            String Description = "channelID";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }

    }

    private NotificationCompat.Builder getObserveNotification() {
        PendingIntent notifyIntent = PendingIntent.getActivity(this, 0, getIntent(), 0);
        return new NotificationCompat.Builder(this, "channelID")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Изменения в графике")
                .setContentText("График работы изменился. Нажмите, чтобы просмотреть!")
                .setContentIntent(notifyIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
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
