package io.github.victorrot44.forge.utils.core.cast.temporal;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public final class InstantCast {

    private InstantCast() {
    }

    public static Instant toInstant(Date date) {
        Objects.requireNonNull(date, "date must not be null");
        return date.toInstant();
    }

    public static Instant toInstant(LocalDate date) {
        return toInstant(date, ZoneOffset.UTC);
    }

    public static Instant toInstant(LocalDate date, ZoneId zone) {
        Objects.requireNonNull(date, "date must not be null");
        Objects.requireNonNull(zone, "zone must not be null");
        return date.atStartOfDay(zone).toInstant();
    }

    public static Instant toInstant(LocalDateTime dateTime) {
        return toInstant(dateTime, ZoneOffset.UTC);
    }

    public static Instant toInstant(LocalDateTime dateTime, ZoneId zone) {
        Objects.requireNonNull(dateTime, "dateTime must not be null");
        Objects.requireNonNull(zone, "zone must not be null");
        return dateTime.atZone(zone).toInstant();
    }

    public static Instant fromEpochMillis(long millis) {
        return Instant.ofEpochMilli(millis);
    }

    public static Instant fromEpochSecond(long seconds) {
        return Instant.ofEpochSecond(seconds);
    }

    public static Instant toInstant(String date) {
        Objects.requireNonNull(date, "date must not be null");
        return Instant.parse(date);
    }

    public static Instant toInstant(String date, String pattern) {
        return toInstant(date, pattern, Locale.ROOT);
    }

    public static Instant toInstant(String date, String pattern, Locale locale) {
        Objects.requireNonNull(date, "date must not be null");
        Objects.requireNonNull(pattern, "pattern must not be null");
        Objects.requireNonNull(locale, "locale must not be null");
        return toInstant(date, DateTimeFormatter.ofPattern(pattern, locale));
    }

    public static Instant toInstant(String date, DateTimeFormatter formatter) {
        Objects.requireNonNull(date, "date must not be null");
        Objects.requireNonNull(formatter, "formatter must not be null");
        return Instant.from(formatter.parse(date));
    }

}
