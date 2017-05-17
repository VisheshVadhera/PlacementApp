package com.vishesh.placement.core.utils;

import android.support.test.runner.AndroidJUnit4;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class DateFormatUtilsTest {

    @Test
    public void testToDateFormattedString_nonNullInput() throws Exception {

        LocalDate localDate = new LocalDate().withYear(2017)
                .withMonthOfYear(1)
                .withDayOfMonth(24);

        String actual = DateFormatUtils.toDateFormattedString(localDate);
        String expected = "Jan 24, 2017";

        assertEquals(expected, actual);
    }

    @SuppressWarnings("ConstantConditions")
    @Test(expected = NullPointerException.class)
    public void testToDateFormattedString_nullInput() throws Exception {
        LocalDate localDate = null;

        DateFormatUtils.toDateFormattedString(localDate);
    }

}