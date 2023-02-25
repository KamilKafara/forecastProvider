package com.kafarson.forecast.utils;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RunWith(MockitoJUnitRunner.class)
public class DateUtilTest extends TestCase {

    @Test
    public void testConvertDateTime() {
        //given
        String dateTime = "2023-02-18T07:14";
        String expectedDateTime = "2023-02-18";
        //when
        LocalDateTime result = DateUtil.convertDateTime(dateTime);
        //then
        assertEquals(expectedDateTime, result.toLocalDate().toString());
        assertFalse(expectedDateTime.contains("T"));
    }

    @Test
    public void testConvertDate() {
        //given
        String dateTime = "2023-02-26";
        String expectedDateTime = "2023-02-26";

        //when
        LocalDate result = DateUtil.convertDate(dateTime);
        //then
        assertEquals(expectedDateTime, result.toString());
        assertFalse(expectedDateTime.contains("T"));
    }
}
