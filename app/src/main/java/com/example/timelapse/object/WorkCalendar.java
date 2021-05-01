package com.example.timelapse.object;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Рабочий календарь
 */

@EqualsAndHashCode
public class WorkCalendar {
    public WorkCalendar(Long id, Date date, List<WorkShift> workShifts) {
        this.id = id;
        this.date = date;
        this.workShifts = workShifts;
    }

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private Date date;

    @Getter
    @Setter
    private List<WorkShift> workShifts;

    public LocalDate getLocalDate() {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
