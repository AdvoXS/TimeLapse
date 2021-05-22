package com.example.timelapse.object;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;


/**
 * Рабочая смена
 */
@Entity
public class WorkShift {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo
    private Date startTime;
    @ColumnInfo
    private Date endTime;
    @ColumnInfo
    private DayType dayType;
    private long calendarId;

    public WorkShift() {
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

    public WorkShift(Long id, Date startTime, Date endTime, DayType dayType, long calendarId) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayType = dayType;
        this.calendarId = calendarId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDayType(String dayType) {
        for (DayType dayType1 : DayType.values()) {
            if (dayType1.getAbout().equals(dayType)) {
                this.dayType = dayType1;
                break;
            }
        }
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
