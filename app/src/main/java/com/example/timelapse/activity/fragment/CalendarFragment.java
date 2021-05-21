package com.example.timelapse.activity.fragment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.example.timelapse.R;
import com.example.timelapse.db.database.AbstractDataBase;
import com.example.timelapse.db.database.DBHelper;
import com.example.timelapse.object.WorkCalendarWithShift;
import com.example.timelapse.object.settings.AppSettings;
import com.example.timelapse.service.ObserveBroadcastReceiver;
import com.example.timelapse.service.ObserveTimeLapseService;
import com.example.timelapse.system.impl.calendar.CalendarViewImpl;
import com.example.timelapse.system.util.thread.AsyncCallObject;
import com.kizitonwose.calendarview.CalendarView;

import java.util.List;

public class CalendarFragment extends Fragment {
    public final static String BROADCAST_OBSERVE = "android.intent.action.CALENDAR";
    private CalendarViewImpl calendarView1 = null;
    private AbstractDataBase db = null;
    private NotificationManagerCompat notificationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_fragment, container, false);
        CalendarView calendarView = view.findViewById(R.id.calenar_view_fragment);
        calendarView1 = new CalendarViewImpl(getActivity(), calendarView);
        db = DBHelper.getDB(getContext(), DBHelper.LOCAL_BASE);
        new AsyncCallObject<List<AppSettings>>() {

            @Override
            protected List<AppSettings> run() {
                return db.appSettingsDao().getAll();
            }

            @Override
            protected void postExecute(List<AppSettings> object) {
                super.postExecute(object);
            }
        }.execute();
        registerObserverService();
        registerNotificationChannel();
        updateCalendar(db, calendarView1);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        updateCalendar(db, calendarView1);
    }

    private void registerObserverService() {
        Intent intentObserveService = new Intent(getActivity(), ObserveTimeLapseService.class);
        ObserveBroadcastReceiver broadcastRec = new ObserveBroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                updateCalendar(db, calendarView1);
                notificationManager.notify(3000, getObserveNotification().build());
                Intent broadIntent = new Intent(NotificationFragment.BROADCAST_OBSERVE);
                getActivity().sendBroadcast(broadIntent);
            }
        };
        IntentFilter intFilt = new IntentFilter(BROADCAST_OBSERVE);
        getActivity().registerReceiver(broadcastRec, intFilt);
        ObserveTimeLapseService.enqueueWork(getContext(), intentObserveService);
    }

    private void registerNotificationChannel() {
        notificationManager = NotificationManagerCompat.from(getActivity());
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
        PendingIntent notifyIntent = PendingIntent.getActivity(getActivity(), 0, getActivity().getIntent(), 0);
        return new NotificationCompat.Builder(getActivity(), "channelID")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Изменения в графике")
                .setContentText("График работы изменился. Нажмите, чтобы просмотреть!")
                .setContentIntent(notifyIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
    }

    public void updateCalendar(AbstractDataBase db, CalendarViewImpl calendarView1) {
        new AsyncCallObject<List<WorkCalendarWithShift>>() {
            @Override
            protected List<WorkCalendarWithShift> run() {
                return db.workCalendarWithShiftDao().getAll(); //получаем асинхронно данные из бд
            }

            @Override
            protected void postExecute(List<WorkCalendarWithShift> object) {
                calendarView1.setup(getContext(), object); //после получения данных в другом потоке, возвращаемся и заполняем все в интерфейсе
                super.postExecute(object);
            }
        }.execute();
    }
}
