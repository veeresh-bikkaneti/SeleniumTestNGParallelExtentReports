package com.services;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DateConverterService {

    String convertLocalDateToFormat(LocalDate localDate, String format);

    String usingSimpleDateFormatyyyyMMddhhmmss();

    LocalDateTime convertStringDateTimeToLocalDateTime(String stringDateTime, String stringDateTimeFormat);

    String convertCurrentDateToTformat();

    String getCurrentUtcTime() throws ParseException;

    String convertLocalDateTimeToFormattedString(LocalDateTime localDateTime, String format);
}
