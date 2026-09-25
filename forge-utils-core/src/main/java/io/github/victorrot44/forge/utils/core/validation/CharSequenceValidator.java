package io.github.victorrot44.forge.utils.core.validation;

import java.util.Objects;

public final class CharSequenceValidator {

    private CharSequenceValidator() {}

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.isEmpty();
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    public static boolean isBlank(CharSequence cs) {
        if (isEmpty(cs)) return true;
        for (int i=0; i<cs.length();) {
            int codePoint = Character.codePointAt(cs, i);
            if (!Character.isWhitespace(codePoint) && !Character.isSpaceChar(codePoint)) {
                return false;
            }
            i += Character.charCount(codePoint);
        }
        return true;
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    public static boolean hasMinLength(CharSequence cs, int minLength) {
        Objects.requireNonNull(cs, "value cannot be null");
        if (minLength < 0)
            throw new IllegalArgumentException("minLength cannot be less than 0");
        return cs.length() >= minLength;
    }

    public static boolean hasMaxLength(CharSequence cs, int maxLength) {
        Objects.requireNonNull(cs, "value cannot be null");
        if (maxLength < 0)
            throw new IllegalArgumentException("maxLength cannot be less than 0");
        return cs.length() <= maxLength;
    }

    public static  boolean hasLengthBetween(CharSequence cs, int minLength, int maxLength) {
        Objects.requireNonNull(cs, "value cannot be null");
        if (minLength < 0 || maxLength < 0)
            throw new IllegalArgumentException("minLength or maxLength cannot be less than 0");
        if (minLength > maxLength)
            throw new IllegalArgumentException("minLength cannot be greater than maxLength");
        return cs.length() >= minLength && cs.length() <= maxLength;
    }

    public static boolean hasExactLength(CharSequence cs, int exactLength) {
        Objects.requireNonNull(cs, "value cannot be null");
        if (exactLength < 0)
            throw new IllegalArgumentException("exactLength cannot be less than 0");
        return cs.length() == exactLength;
    }

}
