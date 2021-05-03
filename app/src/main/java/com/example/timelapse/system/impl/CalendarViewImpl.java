package com.example.timelapse.system.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.timelapse.activity.CalendarActivity;
import com.example.timelapse.object.WorkCalendarWithShift;
import com.google.gson.Gson;
import com.kizitonwose.calendarview.CalendarView;
import com.kizitonwose.calendarview.model.CalendarDay;
import com.kizitonwose.calendarview.model.CalendarMonth;
import com.kizitonwose.calendarview.model.DayOwner;
import com.kizitonwose.calendarview.ui.DayBinder;
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder;

import java.time.DayOfWeek;
import java.time.YearMonth;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

import static com.example.timelapse.system.util.DateUtils.getRussianMonth;

public class CalendarViewImpl {
    private CalendarView calendarView;
    private Activity activity;

    public CalendarViewImpl(CalendarView calendarView) {
        this.calendarView = calendarView;
    }

    public CalendarViewImpl(Activity activity, CalendarView calendarView) {
        this.calendarView = calendarView;
        this.activity = activity;
    }

    public void setup(List<WorkCalendarWithShift> calendar) {
        setDayBinderImpl(calendar);
        setMonthHeaderBinderImpl();
    }

    protected void setDayBinderImpl(List<WorkCalendarWithShift> calendar) {
        //TODO: убрать при реализации REST
        //List<WorkCalendar> calendar = WorkCalendarCreator.getWorkCalendarList();

        calendarView.setDayBinder(new DayBinder<DayViewContainer>() {
            @Override
            public DayViewContainer create(View view) {
                return new DayViewContainer(view);
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void bind(DayViewContainer dayViewContainer, CalendarDay calendarDay) {
                dayViewContainer.textView.setText(String.valueOf(calendarDay.getDate().getDayOfMonth()));
                DayCalendarBinder.bind(calendar, dayViewContainer, calendarDay);
                setOnClickListener(dayViewContainer.textView, calendarDay, calendar);
            }

        });
    }

    protected void setMonthHeaderBinderImpl() {
        YearMonth currentMonth = YearMonth.now();
        YearMonth firstMonth = currentMonth.minusMonths(3);
        YearMonth lastMonth = currentMonth.plusMonths(3);
        DayOfWeek firstDayOfWeek = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();
        calendarView.setup(firstMonth, lastMonth, firstDayOfWeek);
        calendarView.scrollToMonth(currentMonth);
        calendarView.setMonthHeaderBinder(new MonthHeaderFooterBinder<MonthViewContainer>() {

            @Override
            public void bind(MonthViewContainer monthViewContainer, CalendarMonth calendarMonth) {
                monthViewContainer.textView.setText(getRussianMonth(calendarMonth.getYearMonth().getMonth().name()) + " " + calendarMonth.getYearMonth().getYear());
            }

            @Override
            public MonthViewContainer create(View view) {
                return new MonthViewContainer(view);
            }

        });
    }

    protected void setOnClickListener(TextView view, CalendarDay calendarDay, List<WorkCalendarWithShift> calendar) {
        view.setOnClickListener(v -> {
            if (calendarDay.getOwner() == DayOwner.THIS_MONTH) {
                Intent intent = new Intent(calendarView.getContext(), CalendarActivity.class);
                intent.putExtra("DATE", calendarDay.getDate());
                String calendarJSON = new Gson().toJson(calendar);
                intent.putExtra("CALENDAR", calendarJSON);
                activity.startActivity(intent);
            }
        });
    }

    public CalendarView get() {
        return calendarView;
    }
}
