package io.github.victorrot44.forge.utils.core.cast.temporal;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public final class LocalDateCast {

    private LocalDateCast() {
    }

    public static LocalDate toLocalDate(Instant instant) {
        return toLocalDate(instant, ZoneOffset.UTC);
    }

    public static LocalDate toLocalDate(Instant instant, ZoneId zoneId) {
        Objects.requireNonNull(instant, "instant must not be null");
        Objects.requireNonNull(zoneId, "zoneId must not be null");
        return instant.atZone(zoneId).toLocalDate();
    }

    public static LocalDate toLocalDate(LocalDateTime dateTime) {
        Objects.requireNonNull(dateTime, "dateTime must not be null");
        return dateTime.toLocalDate();
    }

    public static LocalDate toLocalDate(Date date) {
        return toLocalDate(date, ZoneOffset.UTC);
    }

    public static LocalDate toLocalDate(Date date, ZoneId zoneId) {
        Objects.requireNonNull(date, "date must not be null");
        Objects.requireNonNull(zoneId, "zoneId must not be null");
        return date.toInstant().atZone(zoneId).toLocalDate();
    }

    public static LocalDate toLocalDate(String date) {
        Objects.requireNonNull(date, "date must not be null");
        return LocalDate.parse(date);
    }
    
    public static LocalDate toLocalDate(String date, String pattern) {
        Objects.requireNonNull(date, "date must not be null");
        Objects.requireNonNull(pattern, "pattern must not be null");
        return toLocalDate(date, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDate toLocalDate(String date, DateTimeFormatter formatter) {
        Objects.requireNonNull(date, "date must not be null");
        Objects.requireNonNull(formatter, "formatter must not be null");
        return LocalDate.from(formatter.parse(date));
    }

    public static LocalDate toLocalDate(String date, String pattern, Locale locale) {
        Objects.requireNonNull(date, "date must not be null");
        Objects.requireNonNull(pattern, "pattern must not be null");
        Objects.requireNonNull(locale, "locale must not be null");
        return toLocalDate(date, DateTimeFormatter.ofPattern(pattern, locale));
    }

}
