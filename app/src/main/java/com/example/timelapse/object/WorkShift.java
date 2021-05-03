package com.example.timelapse.object;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Time;


/**
 * Рабочая смена
 */
@Entity
public class WorkShift {
    @PrimaryKey
    private Long id;
    @ColumnInfo
    private Time startTime;
    @ColumnInfo
    private Time endTime;
    @ColumnInfo
    private DayType dayType;
    private long calendarId;

    public WorkShift(Long id, Time startTime, Time endTime, DayType dayType) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayType = dayType;
    }

    public long getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(long calendarId) {
        this.calendarId = calendarId;
    }

    public DayType getDayType() {
        return dayType;
    }

    public void setDayType(DayType dayType) {
        this.dayType = dayType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}
