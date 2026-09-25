package io.github.victorrot44.forge.utils.core.validation;

public final class Require {

    private static final String NOT_EMPTY_MESSAGE = "value must not be empty";
    private static final String NOT_BLANK_MESSAGE = "value must not be blank";
    private static final String NOT_ZERO_MESSAGE = "value must not be zero";
    private static final String POSITIVE_MESSAGE = "value must be positive";
    private static final String NOT_NEGATIVE_MESSAGE = "value must not be negative";

    private Require() {
    }

    public static void notEmpty(CharSequence value) {
        notEmpty(value, null);
    }

    public static void notEmpty(CharSequence value, String message) {
        if (!CharSequenceValidator.isNotEmpty(value)) {
            throw new IllegalArgumentException(
                    message != null ? message : NOT_EMPTY_MESSAGE
            );
        }
    }

    public static <T extends CharSequence> T requireNotEmpty(T value) {
        notEmpty(value);
        return value;
    }

    public static <T extends CharSequence> T requireNotEmpty(T value, String message) {
        notEmpty(value, message);
        return value;
    }

    public static void notBlank(CharSequence value) {
        notBlank(value, null);
    }

    public static void notBlank(CharSequence value, String message) {
        if (!CharSequenceValidator.isNotBlank(value)) {
            throw new IllegalArgumentException(
                    message != null ? message : NOT_BLANK_MESSAGE
            );
        }
    }

    public static <T extends CharSequence> T requireNotBlank(T value) {
        notBlank(value);
        return value;
    }

    public static <T extends CharSequence> T requireNotBlank(T value, String message) {
        notBlank(value, message);
        return value;
    }

    public static void positive(Number value) {
        positive(value, null);
    }

    public static void positive(Number value, String message) {
        if (value == null || !NumberValidator.isPositive(value)) {
            throw new IllegalArgumentException(message != null ? message : POSITIVE_MESSAGE);
        }
    }

    public static <T extends Number> T requirePositive(T value) {
        positive(value);
        return value;
    }

    public static <T extends Number> T requirePositive(T value, String message) {
        positive(value, message);
        return value;
    }

    public static void notZero(Number value) {
        notZero(value, null);
    }

    public static void notZero(Number value, String message) {
        if (value == null || !NumberValidator.isNotZero(value)) {
            throw new IllegalArgumentException(message != null ? message : NOT_ZERO_MESSAGE);
        }
    }

    public static <T extends Number> T requireNotZero(T value) {
        notZero(value);
        return value;
    }

    public static <T extends Number> T requireNotZero(T value, String message) {
        notZero(value, message);
        return value;
    }

    public static void negative(Number value) {
        negative(value, null);
    }

    public static void negative(Number value, String message) {
        if (value == null || !NumberValidator.isNegative(value)) {
            throw new IllegalArgumentException(message != null ? message : NOT_NEGATIVE_MESSAGE);
        }
    }

    public static <T extends Number> T requireNotNegative(T value) {
        negative(value);
        return value;
    }

    public static <T extends Number> T requireNotNegative(T value, String message) {
        negative(value, message);
        return value;
    }

    public static void exactLength(CharSequence value, int exactLength) {
        exactLength(value, exactLength, null);
    }

    public static void exactLength(CharSequence value, int exactLength, String message) {
        if (!CharSequenceValidator.hasExactLength(value, exactLength))
            throw new IllegalArgumentException(message != null ? message : "value must have exactly " + exactLength + " characters");
    }

    public static <T extends CharSequence> T requireExactLength(T value, int exactLength) {
        exactLength(value, exactLength);
        return value;
    }

    public static <T extends CharSequence> T requireExactLength(T value, int exactLength, String message) {
        exactLength(value, exactLength, message);
        return value;
    }

    public static void minLength(CharSequence value, int minLength) {
        minLength(value, minLength, null);
    }

    public static void minLength(CharSequence value, int minLength, String message) {
        if (!CharSequenceValidator.hasMinLength(value, minLength))
            throw new IllegalArgumentException(message != null ? message : "value must have minimum length of " + minLength + " characters");
    }

    public static <T extends CharSequence> T requireMinLength(T value, int minLength) {
        minLength(value, minLength);
        return value;
    }

    public static <T extends CharSequence> T requireMinLength(T value, int minLength, String message) {
        minLength(value, minLength, message);
        return value;
    }

    public static void maxLength(CharSequence value, int maxLength) {
        maxLength(value, maxLength, null);
    }

    public static void maxLength(CharSequence value, int maxLength, String message) {
        if (!CharSequenceValidator.hasMaxLength(value, maxLength))
            throw new IllegalArgumentException(message != null ? message : "value must have maximum length of " + maxLength + " characters");
    }

    public static <T extends CharSequence> T requireMaxLength(T value, int maxLength) {
        maxLength(value, maxLength);
        return value;
    }

    public static <T extends CharSequence> T requireMaxLength(T value, int maxLength, String message) {
        maxLength(value, maxLength, message);
        return value;
    }

    public static void lengthBetween(CharSequence value, int minLength, int maxLength) {
        lengthBetween(value, minLength, maxLength, null);
    }

    public static void lengthBetween(CharSequence value, int minLength, int maxLength, String message) {
        if (minLength > maxLength)
            throw new IllegalArgumentException("minLength cannot be greater than maxLength");
        if (!CharSequenceValidator.hasLengthBetween(value, minLength, maxLength))
            throw new IllegalArgumentException(message != null ? message : "value must have length between " + minLength + " and " + maxLength + " characters");
    }

    public static <T extends CharSequence> T requireLengthBetween(T value, int minLength, int maxLength) {
        lengthBetween(value, minLength, maxLength);
        return value;
    }

    public static <T extends CharSequence> T requireLengthBetween(T value, int minLength, int maxLength, String message) {
        lengthBetween(value, minLength, maxLength, message);
        return value;
    }

    public static void between(Number value, Number min, Number max) {
        between(value, min, max, null);
    }

    public static void between(Number value, Number min, Number max, String message) {
        if (!NumberValidator.isBetween(value, min, max))
            throw new IllegalArgumentException(message != null ? message : "value must be between " + min + " and " + max);
    }

    public static <T extends Number> T requireBetween(T value, Number min, Number max) {
        between(value, min, max, null);
        return value;
    }

    public static <T extends Number> T requireBetween(T value, Number min, Number max, String message) {
        between(value, min, max, message);
        return value;
    }

}
