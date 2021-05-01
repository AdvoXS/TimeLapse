package com.example.timelapse.object;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Рабочая смена
 */
@AllArgsConstructor
@EqualsAndHashCode
public class WorkShift {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private Person person;

    @Getter
    @Setter
    private Time startTime;

    @Getter
    @Setter
    private Time endTime;

    @Getter
    @Setter
    private DayType dayType;
}
