package com.example.timelapse.activity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timelapse.R;
import com.example.timelapse.object.NotificationHistory;

import java.util.List;

public class NotificationHistoryListAdapter extends RecyclerView.Adapter<NotificationHistoryListAdapter.ViewHolder> {
    private final List<NotificationHistory> notifications;
    private final LayoutInflater inflater;

    public NotificationHistoryListAdapter(List<NotificationHistory> notifications, LayoutInflater inflater) {
        this.notifications = notifications;
        this.inflater = inflater;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.notification_history_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotificationHistory notificationHistory = notifications.get(position);
        holder.dateView.setText(notificationHistory.getDate().toString());
        holder.shortDescriptionView.setText(notificationHistory.getShortDescription());
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView dateView, shortDescriptionView;

        ViewHolder(View view) {
            super(view);
            dateView = view.findViewById(R.id.date);
            shortDescriptionView = view.findViewById(R.id.shortDescription);
        }
    }
}
