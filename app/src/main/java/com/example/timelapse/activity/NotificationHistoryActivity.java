package com.example.timelapse.activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timelapse.R;
import com.example.timelapse.activity.adapter.NotificationHistoryListAdapter;
import com.example.timelapse.db.database.AbstractDataBase;
import com.example.timelapse.db.database.DBHelper;
import com.example.timelapse.object.NotificationHistory;
import com.example.timelapse.system.util.thread.AsyncCallObject;

import java.util.List;

public class NotificationHistoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    NotificationHistoryListAdapter adapter;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_history);
        recyclerView = findViewById(R.id.notification_history_list_view);
        activity = this;
    }

    @Override
    protected void onStart() {
        fillData();
        super.onStart();
    }

    private void fillData() {
        AbstractDataBase db = DBHelper.getDB(getApplicationContext(), DBHelper.LOCAL_BASE);
        new AsyncCallObject<List<NotificationHistory>>() {
            @Override
            protected List<NotificationHistory> run() {
                return db.notificationHistoryDao().getAll();
            }

            @Override
            protected void postExecute(List<NotificationHistory> object) {
                adapter = new NotificationHistoryListAdapter(object, activity.getLayoutInflater());
                recyclerView.setAdapter(adapter);
                super.postExecute(object);
            }
        }.execute();
    }
}