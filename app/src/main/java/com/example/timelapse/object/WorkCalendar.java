package com.example.timelapse.object;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Рабочий календарь
 */
@AllArgsConstructor
@EqualsAndHashCode
public class WorkCalendar {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private Date date;

    @Getter
    @Setter
    private List<WorkShift> workShifts;
}
