package com.kafarson.forecast.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String DEFAULT_SKIP_CHARACTER = "T";

    private DateUtil() {
    }

    public static LocalDateTime convertDateTime(String data) {
        return convertDateTime(data, DATE_TIME_PATTERN, DEFAULT_SKIP_CHARACTER);
    }

    public static LocalDateTime convertDateTime(String data, String pattern, String skip) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.from(formatter.parse(data.replace(skip, " ")));
    }

    public static LocalDate convertDate(String data) {
        return convertDate(data, DATE_PATTERN, DEFAULT_SKIP_CHARACTER);
    }

    public static LocalDate convertDate(String data, String pattern, String skip) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.from(formatter.parse(data.replace(skip, " ")));
    }
}
