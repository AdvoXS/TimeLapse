package com.example.timelapse.system.impl;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.example.timelapse.object.WorkCalendar;
import com.example.timelapse.object.template.WorkCalendarCreator;
import com.kizitonwose.calendarview.CalendarView;
import com.kizitonwose.calendarview.model.CalendarDay;
import com.kizitonwose.calendarview.model.CalendarMonth;
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

    public CalendarViewImpl(CalendarView calendarView) {
        this.calendarView = calendarView;
    }

    public void setup() {
        setDayBinderImpl();
        setMonthHeaderBinderImpl();
    }

    protected void setDayBinderImpl() {
        //TODO: убрать при реализации REST
        List<WorkCalendar> calendar = WorkCalendarCreator.getWorkCalendarList();

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
            }
        });
    }

    protected void setMonthHeaderBinderImpl() {
        YearMonth currentMonth = YearMonth.now();
        YearMonth firstMonth = currentMonth.minusMonths(2);
        YearMonth lastMonth = currentMonth.plusMonths(12);
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

    public CalendarView get() {
        return calendarView;
    }
}
