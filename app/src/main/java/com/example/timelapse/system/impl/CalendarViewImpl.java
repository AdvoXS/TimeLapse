package com.example.timelapse.system.impl;

import android.graphics.Color;
import android.view.View;

import com.kizitonwose.calendarview.CalendarView;
import com.kizitonwose.calendarview.model.CalendarDay;
import com.kizitonwose.calendarview.model.CalendarMonth;
import com.kizitonwose.calendarview.model.DayOwner;
import com.kizitonwose.calendarview.ui.DayBinder;
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder;

import java.time.DayOfWeek;
import java.time.YearMonth;
import java.time.temporal.WeekFields;
import java.util.Locale;

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
        calendarView.setDayBinder(new DayBinder<DayViewContainer>() {
            @Override
            public DayViewContainer create(View view) {
                return new DayViewContainer(view);
            }

            @Override
            public void bind(DayViewContainer dayViewContainer, CalendarDay calendarDay) {
                dayViewContainer.textView.setText(String.valueOf(calendarDay.getDate().getDayOfMonth()));
                if (calendarDay.getOwner().equals(DayOwner.THIS_MONTH))
                    dayViewContainer.textView.setTextColor(Color.BLACK);
                else
                    dayViewContainer.textView.setTextColor(Color.GRAY);
                //dayViewContainer.textView.setTextColor(ContextCompat.getColor(context, R.color.light_gray));
                //dayViewContainer.layout.setBackground(null);
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
                monthViewContainer.textView.setText(calendarMonth.getYearMonth().getMonth().name() + " " + calendarMonth.getYearMonth().getYear());
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
