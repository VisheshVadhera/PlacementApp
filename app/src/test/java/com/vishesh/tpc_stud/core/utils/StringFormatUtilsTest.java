package com.vishesh.tpc_stud.core.utils;

import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;


public class StringFormatUtilsTest {

    @Test
    public void testToRupeeFormattedString_positiveInput() {
        BigDecimal bigDecimal = BigDecimal.valueOf(20);

        assertEquals("Rs. 20", StringFormatUtils.toRupeeFormattedString(bigDecimal));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testToRupeeFormattedString_negativeInput() throws Exception {
        BigDecimal bigDecimal = BigDecimal.valueOf(-20);

        StringFormatUtils.toRupeeFormattedString(bigDecimal);
    }

    @Test(expected = NullPointerException.class)
    public void testToRupeeFormattedString_nullInput() throws Exception {
        BigDecimal bigDecimal = null;

        StringFormatUtils.toRupeeFormattedString(bigDecimal);
    }

    @Test
    public void testCreateSpacedString_nonEmptyInputs() throws Exception {
        String s1 = "abc";
        String s2 = "def";

        assertEquals("abc def", StringFormatUtils.createSpacedString(s1, s2));
    }

    @Test
    public void testCreateSpacedString_oneEmptyInput() throws Exception {
        String s1 = "abc";
        String s2 = "";

        assertEquals("abc ", StringFormatUtils.createSpacedString(s1, s2));
    }

    @Test
    public void testCreateSpacedString_bothEmptyInputs() {
        String s1 = "";
        String s2 = "";

        assertEquals(" ", StringFormatUtils.createSpacedString(s1, s2));
    }

    @Test(expected = NullPointerException.class)
    public void testCreateSpacedString_bothNullInputs_expectNullPointerException() {
        String s1 = null;
        String s2 = null;

        StringFormatUtils.createSpacedString(s1, s2);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateSpacedString_oneNullInput_expectNullPointerException() {
        String s1 = null;
        String s2 = "abc";

        StringFormatUtils.createSpacedString(s1, s2);
    }
}