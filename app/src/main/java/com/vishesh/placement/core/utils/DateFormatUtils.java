package com.vishesh.placement.core.utils;

import com.fernandocejas.arrow.checks.Preconditions;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Locale;

public class DateFormatUtils {

    private static final String DATE_PATTERN = "MMM dd, YYYY";

    private DateFormatUtils() {
        throw new AssertionError("Can't instantiate DateFormatUtils");
    }

    /**
     * Returns a string in the date format "MMM dd, YYYY"
     * @param localDate localDate instance which needs to be converted.
     *                  Must be non null.
     * @return date formatted string.
     * @throws NullPointerException if localDate==null
     */
    public static String toDateFormattedString(LocalDate localDate) {
        Preconditions.checkNotNull(localDate);

        DateTimeFormatter dateTimeFormatter = DateTimeFormat
                .forPattern(DATE_PATTERN)
                .withLocale(Locale.US);
        return dateTimeFormatter.print(localDate);
    }
}
