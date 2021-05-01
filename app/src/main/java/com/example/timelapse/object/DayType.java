package com.example.timelapse.object;

public enum DayType {
    WORK("Рабочий день"),
    DAY_OFF("Выходной день"),
    NON_WORKING("Нерабочий день"),
    HOLIDAY("Праздничный день"),
    SICK_LEAVE("Больничный"),
    VACATION("Отпуск");

    private final String daySubscription;

    DayType(String daySubscription) {
        this.daySubscription = daySubscription;
    }

    public String getAbout() {
        return daySubscription;
    }
}
