package io.github.victorrot44.forge.utils.core.validation;

import java.util.Collection;
import java.util.Objects;

public final class CollectionValidator {

    private CollectionValidator() {}

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean hasExactSize(Collection<?> collection, int exactSize) {
        Objects.requireNonNull(collection, "value cannot be null");
        if (exactSize < 0)
            throw new IllegalArgumentException("exact size cannot be less than zero");
        return collection.size() == exactSize;
    }

    public static boolean hasMinSize(Collection<?> collection, int minSize) {
        Objects.requireNonNull(collection, "value cannot be null");
        if (minSize < 0)
            throw new IllegalArgumentException("min size cannot be less than zero");
        return collection.size() >= minSize;
    }

    public static boolean hasMaxSize(Collection<?> collection, int maxSize) {
        Objects.requireNonNull(collection, "value cannot be null");
        if (maxSize < 0)
            throw new IllegalArgumentException("max size cannot be less than zero");
        return collection.size() <= maxSize;
    }

    public static boolean hasSizeBetween(Collection<?> collection, int minSize, int maxSize) {
        Objects.requireNonNull(collection, "value cannot be null");
        if (minSize < 0 || maxSize < 0)
            throw new IllegalArgumentException("min size or max size cannot be less than zero");
        if (minSize > maxSize)
            throw new IllegalArgumentException("min size cannot be greater than max size");
        int size = collection.size();
        return size >= minSize && size <= maxSize;
    }

}
