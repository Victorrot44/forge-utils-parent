package io.github.victorrot44.forge.utils.core.validation;

import io.github.victorrot44.forge.utils.core.cast.NumberCast;

import java.math.BigDecimal;
import java.util.Objects;

public final class NumberValidator {

    public static boolean isNumber(Object value) {
        return switch (value) {
            case Float number -> true;
            case Double number -> true;
            default -> {
                try {
                    NumberCast.toBigDecimal(value);
                    yield true;
                } catch (Exception e) {
                    yield false;
                }
            }
        };
    }

    public static boolean isZero(Object value) {
        if (value == null) return false;
        return switch (value) {
            case Float number -> number == 0.0f;
            case Double number -> number == 0.0d;
            default -> NumberCast.toBigDecimal(value).compareTo(BigDecimal.ZERO) == 0;
        };
    }

    public static boolean isNotZero(Object value) {
        return !isZero(value);
    }

    public static boolean isPositive(Object value) {
        if (value == null) return false;
        return switch (value) {
            case Float number -> !Float.isNaN(number) && number > 0.0f;
            case Double number -> !Double.isNaN(number) && number > 0.0d;
            default -> NumberCast.toBigDecimal(value).compareTo(BigDecimal.ZERO) > 0;
        };
    }

    public static boolean isNegative(Object value) {
        if (value == null) return false;
        return switch (value) {
            case Float number -> !Float.isNaN(number) && number < 0.0f;
            case Double number -> !Double.isNaN(number) && number < 0.0d;
            default -> NumberCast.toBigDecimal(value).compareTo(BigDecimal.ZERO) < 0;
        };
    }

    public static boolean isBetween(Number value, Number min, Number max) {
        BigDecimal maximum = NumberCast.toBigDecimal(max);
        BigDecimal minimum = NumberCast.toBigDecimal(min);
        BigDecimal number = NumberCast.toBigDecimal(value);
        if (minimum.compareTo(number) > 0) {
            throw new IllegalArgumentException("minimum cannot be greater than maximum");
        }
        return number.compareTo(minimum) >= 0 && number.compareTo(maximum) <= 0;
    }

}
