package io.github.victorrot44.forge.utils.core.cast;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import java.util.function.Function;

/**
 * Conversión numérica segura ("checked cast") entre tipos {@link Number}, {@link String} u otro {@link Object}
 * representables como número.
 *
 * <p><b>Semántica del "cast":</b> a diferencia del operador {@code (int)} de Java, que trunca en silencio cuando hay
 * pérdida de precisión, los métodos de estrechamiento (narrowing) de esta clase - {@link #toInteger} - son {@link #toLong},
 * {@link #toShort} y {@link #toBigInteger} - son <b>exactos</b>: lanzan {@link ArithmeticException} si el valor de origen
 * no cabe sin pérdida en el tipo destino. Esto es intencional: se prioriza fallar rápido antes que corromper datos
 * silenciosamente.</p>
 *
 * <p>Los métodos de ensanchamiento (widening) - {@link #toDouble}, {@link #toFloat} y {@link #toBigDecimal} - nunca
 * lanzan por precisión, ya que el tipo destino siempre puede representar el valor de origen (con la limitación de
 * precisión binaria inherente a {@code double}).</p>
 *
 * <p>Para casos donde un valor ambiguo no debe interrumpir el flujo (p. ej. parseo de configuración opcional),
 *  use los métodos {@code tryTo...}, que nunca lanzan excepciones y retorna {@code null} en su lugar.</p>
 */
public final class NumberCast {

    private NumberCast() {}

    private static short toShortExact(long value) {
        if (value < Short.MIN_VALUE || value > Short.MAX_VALUE) {
            throw new ArithmeticException("short overflow");
        }
        return (short) value;
    }

    private static byte toByteExact(long value) {
        if (value < Byte.MIN_VALUE || value > Byte.MAX_VALUE) {
            throw new ArithmeticException("byte overflow");
        }
        return (byte) value;
    }

    private static float toFloatChecked(Number value) {
        double asDouble = value.doubleValue();
        float asFloat = (float) asDouble;
        if (Float.isInfinite(asFloat) && !Double.isInfinite(asDouble)) {
            throw new ArithmeticException("float overflow");
        }
        return asFloat;
    }

    private static <R extends Number> R tryTo(Object value, Function<Object, R> function) {
        if (value == null) {
            return null;
        }
        try {
            return function.apply(value);
        } catch (ArithmeticException | NumberFormatException e) {
            return null;
        }
    }

    public static BigDecimal toBigDecimal(Object value) {
        Objects.requireNonNull(value, "value cannot be null");
        return switch (value) {
            case BigDecimal number  -> number;
            case BigInteger number  -> new BigDecimal(number);
            case Double number      -> BigDecimal.valueOf(number);
            case Float number       -> new BigDecimal(Float.toString(number));
            case Number number      -> BigDecimal.valueOf(number.longValue());
            default                 -> new  BigDecimal(value.toString());
        };
    }

    public static Double toDouble(Object value) {
        Objects.requireNonNull(value, "value cannot be null");
        return switch (value) {
            case Double number  -> number;
            case Number number  -> number.doubleValue();
            default             -> Double.valueOf(value.toString());
        };
    }

    public static Float toFloat(Object value) {
        Objects.requireNonNull(value, "value cannot be null");
        return switch (value) {
            case Float number   -> number;
            case Number number  -> toFloatChecked(number);
            default             -> Float.valueOf(value.toString());
        };
    }

    public static BigInteger toBigInteger(Object value) {
        Objects.requireNonNull(value, "value cannot be null");
        return switch (value) {
            case BigInteger number  -> number;
            case BigDecimal number  -> number.toBigIntegerExact();
            case Double number      -> BigDecimal.valueOf(number).toBigIntegerExact();
            case Float number       -> BigDecimal.valueOf(number.doubleValue()).toBigIntegerExact();
            case Number number      -> BigInteger.valueOf(number.longValue());
            default                 -> new BigInteger(value.toString());
        };
    }

    public static Long toLong(Object value) {
        Objects.requireNonNull(value, "value cannot be null");
        return switch (value) {
            case Long number        -> number;
            case BigInteger number  -> number.longValueExact();
            case BigDecimal number  -> number.longValueExact();
            case Double number      -> BigDecimal.valueOf(number).longValueExact();
            case Float number       -> BigDecimal.valueOf(number.doubleValue()).longValueExact();
            case Number number      -> number.longValue();
            default                 -> Long.valueOf(value.toString());
        };
    }

    public static Integer toInteger(Object value) {
        Objects.requireNonNull(value, "value cannot be null");
        return switch (value) {
            case Integer number     -> number;
            case Long number        -> Math.toIntExact(number);
            case BigInteger number  -> number.intValueExact();
            case BigDecimal number  -> number.intValueExact();
            case Double number      -> BigDecimal.valueOf(number).intValueExact();
            case Float number       -> BigDecimal.valueOf(number.doubleValue()).intValueExact();
            case Number number      -> number.intValue();
            default                 -> Integer.valueOf(value.toString());
        };
    }

    public static Short toShort(Object value) {
        Objects.requireNonNull(value, "value cannot be null");
        return switch (value) {
            case Short number       -> number;
            case BigInteger number  -> number.shortValueExact();
            case BigDecimal number  -> number.shortValueExact();
            case Double number      -> BigDecimal.valueOf(number).shortValueExact();
            case Float number       -> BigDecimal.valueOf(number.doubleValue()).shortValueExact();
            case Number number      -> toShortExact(number.longValue());
            default                 -> Short.valueOf(value.toString());
        };
    }

    public static Byte toByte(Object value) {
        Objects.requireNonNull(value, "value cannot be null");
        return switch (value) {
            case Byte number        -> number;
            case BigInteger number  -> number.byteValueExact();
            case BigDecimal number  -> number.byteValueExact();
            case Double number      -> BigDecimal.valueOf(number).byteValueExact();
            case Float number       -> BigDecimal.valueOf(number.doubleValue()).byteValueExact();
            case Number number      -> toByteExact(number.longValue());
            default                 -> Byte.valueOf(value.toString());
        };
    }

    public static Byte tryToByte (Object value) {
        return tryTo(value, NumberCast::toByte);
    }

    public static Short tryToShort(Object value) {
        return tryTo(value, NumberCast::toShort);
    }

    public static Integer tryToInteger(Object value) {
        return tryTo(value, NumberCast::toInteger);
    }

    public static Long tryToLong(Object value) {
        return tryTo(value, NumberCast::toLong);
    }

    public static Float tryToFloat(Object value) {
        return tryTo(value, NumberCast::toFloat);
    }

    public static Double tryToDouble(Object value) {
        return tryTo(value, NumberCast::toDouble);
    }

    public static BigDecimal tryToBigDecimal(Object value) {
        return tryTo(value, NumberCast::toBigDecimal);
    }

    public static BigInteger tryToBigInteger(Object value) {
        return tryTo(value, NumberCast::toBigInteger);
    }

}
