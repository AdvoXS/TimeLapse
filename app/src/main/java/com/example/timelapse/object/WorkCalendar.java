package com.example.timelapse.object;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


/**
 * Рабочий календарь
 */
@Entity
public class WorkCalendar {
    @PrimaryKey
    private Long id;
    @ColumnInfo
    private Date date;

    public WorkCalendar() {
    }

    public WorkCalendar(Long id, Date date) {
        this.id = id;
        this.date = date;
    }

    public LocalDate getLocalDate() {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
