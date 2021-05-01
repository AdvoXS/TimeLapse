package com.example.timelapse.system.util;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {

    @Test
    public void getRussianMonth() {
        Assert.assertEquals(DateUtils.getRussianMonth("january"), "Январь");
        Assert.assertEquals(DateUtils.getRussianMonth("March"), "Март");
        Assert.assertEquals(DateUtils.getRussianMonth(" ApRiL"), "Апрель");
    }
}