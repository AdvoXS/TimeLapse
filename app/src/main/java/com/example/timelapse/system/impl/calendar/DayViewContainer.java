package com.example.timelapse.system.impl.calendar;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.timelapse.R;
import com.kizitonwose.calendarview.model.CalendarDay;
import com.kizitonwose.calendarview.ui.ViewContainer;

public class DayViewContainer extends ViewContainer {
    public TextView textView;
    public FrameLayout layout;
    public CalendarDay day;

    public DayViewContainer(View view) {
        super(view);
        textView = view.findViewById(R.id.exOneDayText);
    }

}
