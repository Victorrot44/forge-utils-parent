package io.github.victorrot44.forge.utils.core.cast.temporal;

import java.text.DateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public final class DateCast {

    private DateCast() {}

    public static Date toDate(Instant instant) {
        Objects.requireNonNull(instant, "instant must not be null");
        return Date.from(instant);
    }

    public static Date toDate(LocalDate localDate) {
        return toDate(localDate, ZoneOffset.UTC);
    }

    public static Date toDate(LocalDate localDate, ZoneId zoneId) {
        return Date.from(InstantCast.toInstant(localDate, zoneId));
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return toDate(localDateTime, ZoneOffset.UTC);
    }

    public static Date toDate(LocalDateTime localDateTime, ZoneId zoneId) {
        return Date.from(InstantCast.toInstant(localDateTime, zoneId));
    }

    public static Date fromEpochMillis(long millis) {
        return new Date(millis);
    }

    public static Date toDate(String date) {
        Objects.requireNonNull(date, "date must not be null");
        return Date.from(InstantCast.toInstant(date));
    }

    public static Date toDate(String date, String pattern) {
        return toDate(date, DateTimeFormatter.ofPattern(pattern));
    }

    public static Date toDate(String date, DateTimeFormatter formatter) {
        return Date.from(InstantCast.toInstant(date, formatter));
    }

    public static Date toDate(String date, String pattern, Locale locale) {
        return Date.from(InstantCast.toInstant(date, pattern, locale));
    }

}
