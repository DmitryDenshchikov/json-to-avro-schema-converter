package denshchikov.dmitry.utils;

import java.util.UUID;

public final class NameGenerator {

    public NameGenerator() {
        throw new UnsupportedOperationException("This is an utility class");
    }

    public static String getArrayItemUniqueName() {
        return ("arrayItem-" + UUID.randomUUID()).replaceAll("-", "_");
    }

}
