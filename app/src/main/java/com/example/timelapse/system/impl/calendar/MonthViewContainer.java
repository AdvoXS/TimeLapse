package com.example.timelapse.system.impl.calendar;

import android.view.View;
import android.widget.TextView;

import com.example.timelapse.R;
import com.kizitonwose.calendarview.ui.ViewContainer;

public class MonthViewContainer extends ViewContainer {
    public TextView textView;

    public MonthViewContainer(View view) {
        super(view);
        textView = view.findViewById(R.id.headerTextView);
    }
}
