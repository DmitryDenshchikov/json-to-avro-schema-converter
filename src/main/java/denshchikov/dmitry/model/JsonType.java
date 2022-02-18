package denshchikov.dmitry.model;

/**
 * Possible values in json "type" field.
 */
public enum JsonType {

    NULL("null"),
    BOOLEAN("boolean"),
    OBJECT("object"),
    ARRAY("array"),
    NUMBER("number"),
    STRING("string"),
    INTEGER("integer");


    private final String typeName;

    JsonType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    /**
     * Get enum value from type name (case-sensitive).
     *
     * @param typeName type name as it presents in json content.
     * @return {@link JsonType} enum values.
     * @throws IllegalArgumentException if there is no enum value for given type name.
     */
    public static JsonType of(String typeName) {
        if (typeName == null) {
            return JsonType.NULL;
        }

        for (JsonType rootType : JsonType.values()) {
            if (rootType.typeName.equals(typeName)) {
                return rootType;
            }
        }

        throw new IllegalArgumentException("There is no enum value for type name: " + typeName);
    }

}
