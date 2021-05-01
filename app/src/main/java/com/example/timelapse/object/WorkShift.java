package com.example.timelapse.object;

import java.sql.Time;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Рабочая смена
 */
@EqualsAndHashCode
public class WorkShift {
    public WorkShift(Long id, Person person, Time startTime, Time endTime, DayType dayType) {
        this.id = id;
        this.person = person;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayType = dayType;
    }

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
