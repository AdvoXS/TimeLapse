package com.example.timelapse.activity.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timelapse.R;
import com.example.timelapse.activity.adapter.NotificationHistoryListAdapter;
import com.example.timelapse.db.database.AbstractDataBase;
import com.example.timelapse.db.database.DBHelper;
import com.example.timelapse.object.NotificationHistory;
import com.example.timelapse.service.ObserveBroadcastReceiver;
import com.example.timelapse.service.ObserveTimeLapseService;
import com.example.timelapse.system.util.thread.AsyncCallObject;

import java.util.List;

public class NotificationFragment extends Fragment {
    public final static String BROADCAST_OBSERVE = "android.intent.action.NOTIFICATION";
    RecyclerView recyclerView;
    NotificationHistoryListAdapter adapter;
    Activity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.notifications_fragment, container, false);
        recyclerView = v.findViewById(R.id.notification_history_list);
        activity = getActivity();
        registerObserverService();
        fillData();
        return v;
    }

    private void registerObserverService() {
        Intent intentObserveService = new Intent(getActivity(), ObserveTimeLapseService.class);
        ObserveBroadcastReceiver broadcastRec = new ObserveBroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                fillData();
            }
        };
        IntentFilter intFilt = new IntentFilter(BROADCAST_OBSERVE);
        getActivity().registerReceiver(broadcastRec, intFilt);
        ObserveTimeLapseService.enqueueWork(getContext(), intentObserveService);
    }

    private void fillData() {
        AbstractDataBase db = DBHelper.getDB(getContext(), DBHelper.LOCAL_BASE);
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
