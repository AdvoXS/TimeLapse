package com.example.timelapse.system.impl;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.timelapse.R;
import com.kizitonwose.calendarview.ui.ViewContainer;

public class DayViewContainer extends ViewContainer {
    public TextView textView;
    public FrameLayout layout;

    public DayViewContainer(View view) {
        super(view);
        textView = view.findViewById(R.id.exOneDayText);
    }
}
