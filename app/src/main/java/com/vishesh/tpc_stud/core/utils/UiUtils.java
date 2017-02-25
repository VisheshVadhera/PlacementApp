package com.vishesh.tpc_stud.core.utils;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.util.Locale;

/**
 * Created by vishesh on 24/2/17.
 */

public class UiUtils {

    private static final String RUPEE_PREFIX = "Rs.";
    private static final String DATE_PATTERN = "MMM dd, YYYY";

    private UiUtils() {
        throw new AssertionError("Can't instantiate UiUtils");
    }

    public static String toRupeeFormattedString(BigDecimal amount) {
        return createSpacedString(RUPEE_PREFIX, amount.toPlainString());
    }

    public static String toFormattedDate(LocalDate localDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat
                .forPattern(DATE_PATTERN)
                .withLocale(Locale.US);
        return dateTimeFormatter.print(localDate);
    }

    public static String createSpacedString(String s1, String s2) {
        return String.format("%s %s", s1, s2);
    }

}
