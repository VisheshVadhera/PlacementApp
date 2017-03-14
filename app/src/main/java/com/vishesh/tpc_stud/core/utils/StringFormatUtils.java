package com.vishesh.tpc_stud.core.utils;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.util.Locale;

public class StringFormatUtils {

    private static final String RUPEE_PREFIX = "Rs.";
    private static final String DATE_PATTERN = "MMM dd, YYYY";

    private StringFormatUtils(){
        throw new AssertionError("Can't instantiate StringFormatUtils");
    }

    public static String toDateFormattedString(LocalDate localDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat
                .forPattern(DATE_PATTERN)
                .withLocale(Locale.US);
        return dateTimeFormatter.print(localDate);
    }

    public static String toRupeeFormattedString(BigDecimal amount) {
        return createSpacedString(RUPEE_PREFIX, amount.toPlainString());
    }

    public static String createSpacedString(String s1, String s2) {
        return String.format("%s %s", s1, s2);
    }
}
