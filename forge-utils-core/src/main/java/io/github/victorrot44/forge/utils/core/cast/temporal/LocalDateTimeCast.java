package io.github.victorrot44.forge.utils.core.cast.temporal;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public final class LocalDateTimeCast {

    private LocalDateTimeCast() {}

    public static LocalDateTime toLocalDate(Instant instant) {
        Objects.requireNonNull(instant, "instant must not be null");
        return toLocalDateTime(instant, ZoneOffset.UTC);
    }

    public static LocalDateTime toLocalDateTime(Instant instant, ZoneOffset zoneOffset) {
        Objects.requireNonNull(instant, "instant must not be null");
        Objects.requireNonNull(zoneOffset, "zoneOffset must not be null");
        return instant.atZone(zoneOffset).toLocalDateTime();
    }

    public static LocalDateTime toLocalDateTime(LocalDate date) {
        Objects.requireNonNull(date, "date must not be null");
        return date.atStartOfDay();
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        Objects.requireNonNull(date, "date must not be null");
        return toLocalDateTime(date.toInstant(), ZoneOffset.UTC);
    }

    public static LocalDateTime toLocalDateTime(Date date, ZoneId zoneId) {
        Objects.requireNonNull(date, "date must not be null");
        Objects.requireNonNull(zoneId, "zoneId must not be null");
        return date.toInstant().atZone(zoneId).toLocalDateTime();
    }

    public static LocalDateTime toLocalDateTime(String date) {
        Objects.requireNonNull(date, "date must not be null");
        return LocalDateTime.parse(date);
    }

    public static LocalDateTime toLocalDateTime(String date, String pattern) {
        Objects.requireNonNull(date, "date must not be null");
        Objects.requireNonNull(pattern, "pattern must not be null");
        return toLocalDateTime(date, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime toLocalDateTime(String date, DateTimeFormatter formatter) {
        Objects.requireNonNull(date, "date must not be null");
        Objects.requireNonNull(formatter, "formatter must not be null");
        return LocalDateTime.from(formatter.parse(date));
    }

    private static LocalDateTime toLocalDateTime(String date, String pattern, Locale locale) {
        Objects.requireNonNull(date, "date must not be null");
        Objects.requireNonNull(pattern, "pattern must not be null");
        Objects.requireNonNull(locale, "locale must not be null");
        return toLocalDateTime(date, DateTimeFormatter.ofPattern(pattern, locale));
    }

}
