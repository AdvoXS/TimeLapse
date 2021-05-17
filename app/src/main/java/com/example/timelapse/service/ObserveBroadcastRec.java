package com.example.timelapse.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ObserveBroadcastRec extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        getResultCode();
        throw new UnsupportedOperationException("Not yet implemented");
    }
}