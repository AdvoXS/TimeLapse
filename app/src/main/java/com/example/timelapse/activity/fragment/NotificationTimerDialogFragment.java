package com.example.timelapse.activity.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.timelapse.R;
import com.example.timelapse.db.database.AbstractDataBase;
import com.example.timelapse.db.database.DBHelper;
import com.example.timelapse.object.settings.AppSettings;
import com.example.timelapse.system.setting.DbAppSettings;
import com.example.timelapse.system.util.thread.AsyncCallVoid;

public class NotificationTimerDialogFragment extends DialogFragment {
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.setTitle("Настройка сервиса оповещений").setView(R.layout.notification_settings_dialog).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new AsyncCallVoid() {
                    AbstractDataBase db = DBHelper.getDB(getContext(), DBHelper.LOCAL_BASE);

                    @Override
                    protected Void run() {
                        String timerText = ((EditText) getActivity().findViewById(R.id.edit_timer_notifications_settings_text)).getText().toString();
                        AppSettings setting = new AppSettings(DbAppSettings.TIMER_OBSERVE_CALENDAR.getSettingName(), timerText);
                        db.appSettingsDao().insert(setting);
                        return null;
                    }
                }.execute();
            }
        }).setNegativeButton("Отмена", null).create();
    }
}
