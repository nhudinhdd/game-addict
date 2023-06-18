package com.player.data.gameaddict.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static LocalDate stringToLocalDate(String dateStr) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            return LocalDate.parse(dateStr, formatter);
    }

    public static String localDateToString(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy/MM/dd");
        return localDate.format(formatter);
    }
}
