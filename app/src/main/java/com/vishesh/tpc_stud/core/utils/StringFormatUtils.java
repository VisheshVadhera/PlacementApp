package com.vishesh.tpc_stud.core.utils;

import com.fernandocejas.arrow.checks.Preconditions;

import org.apache.commons.validator.routines.UrlValidator;

import java.math.BigDecimal;

public class StringFormatUtils {

    private static final String RUPEE_PREFIX = "Rs.";
    private static final String HTTP_SCHEME = "http";
    private static final String HTTPS_SCHEME = "https";


    private StringFormatUtils() {
        throw new AssertionError("Can't instantiate StringFormatUtils");
    }

    /**
     * Returns a String with a "Rs. " prefix added.
     *
     * @param amount The amount in to which Rs. suffix needs to be added.
     *               Must be non null and must be >= 0.
     * @return The Rupee prefixed string.
     * @throws NullPointerException if amount == null
     *                              or {@link IllegalArgumentException} if amount < 0.
     */
    public static String toRupeeFormattedString(BigDecimal amount) {
        Preconditions.checkNotNull(amount);
        Preconditions.checkArgument(amount.compareTo(BigDecimal.ZERO) >= 0);
        return createSpacedString(RUPEE_PREFIX, amount.toPlainString());
    }

    /**
     * Concatenates two strings s1 & s2 with a space in between.
     *
     * @param s1 Should be non null.
     * @param s2 Should be non null.
     * @return the concatenated spaced string.
     * @throws NullPointerException if s1==null or s2==null.
     */
    public static String createSpacedString(String s1, String s2) {
        Preconditions.checkNotNull(s1);
        Preconditions.checkNotNull(s2);
        return String.format("%s %s", s1, s2);
    }

    /**
     * Checks if a given string is a valid url or not.
     *
     * @param url Should be non null.
     * @return whether url is valid or not.
     * @throws NullPointerException if url==null.
     */
    public static boolean isUrlValid(String url) {
        Preconditions.checkNotNull(url);

        String[] schemes = {HTTP_SCHEME, HTTPS_SCHEME};
        UrlValidator urlValidator = new UrlValidator(schemes);

        return urlValidator.isValid(url);
    }
}
