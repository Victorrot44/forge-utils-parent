package io.github.victorrot44.forge.utils.core.cast;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

/**
 * Conversión segura de representaciones ambiguas (texto, número) a {@link Boolean}.
 *
 * <p><b>Números:</b> solo {@code 0} y {@code 1} son aceptados de forma exacta (un {@code 0.5} no se trunca a {@code 0};
 * se rechaza).</p>
 *
 * <p><b>Texto</b> (sin distinguir mayúsculas/minúsculas ni espacios): {@code "true"}, {@code "1"}, {@code "yes"},
 * {@code "on"}, {@code "t"} -> {@code true};
 *  {@code "false"}, {@code "0"}, {@code "no"}, {@code "n"}, {@code "off"}, {@code "f"} -> {@code false}. El vocabulario
 *  es deliberadamente acotado a estas variantes; cualquier otro texto se rechaza en vez de intentar adivinar la intención.</p>
 *
 *  <p>Cualquier otro {@link Object} se evalúa a través de su {@code toString()}.</p>
 *
 *  <p>Para casos donde un valor ambiguo no debe interrumpir el flujo (p. ej. parseo de configuración opcional),
 *  use {@link #tryToBoolean(Object)}, que nunca lanzan excepciones y retorna {@code null} en su lugar.</p>
 */
public final class BooleanCast {

    private static final Set<String> TRUE_VALUES = Set.of("true", "1", "yes", "y", "on", "t");
    private static final Set<String> FALSE_VALUES = Set.of("false", "0", "no", "n", "off", "f");

    private BooleanCast() {
    }

    public static Boolean toBoolean(Object value) {
        Objects.requireNonNull(value, "value cannot be null");
        return switch (value) {
            case Boolean bool   -> bool;
            case Number number  -> fromNumeric(NumberCast.toBigDecimal(number));
            default             -> fromText(value.toString());
        };
    }

    public static Boolean tryToBoolean(Object value) {
        if (value == null) {
            return null;
        }
        try {
            return toBoolean(value);
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    private static boolean fromNumeric(BigDecimal number) {
        if (number.compareTo(BigDecimal.ONE) == 0) {
            return true;
        }
        if (number.compareTo(BigDecimal.ZERO) == 0) {
            return false;
        }
        throw new IllegalArgumentException("Ambiguous numeric boolean value: " + number);
    }

    private static boolean fromText(String text) {
        String normalized = text.strip().toLowerCase(Locale.ROOT);
        if (TRUE_VALUES.contains(normalized)) {
            return true;
        }
        if (FALSE_VALUES.contains(normalized)) {
            return false;
        }
        throw new IllegalArgumentException("Ambiguous boolean text value: '" + text + "'");
    }
}
