package denshchikov.dmitry.mapper;

import denshchikov.dmitry.model.JsonType;

import java.util.Map;

import static denshchikov.dmitry.model.JsonType.INTEGER;
import static denshchikov.dmitry.model.JsonType.NUMBER;

/**
 * Mappers container. Just stores and distributes {@link Mapper} objects associated with appropriate json types.
 * See {@link Mapper} for details.
 */
public class MappersRegistry {

    private final Map<JsonType, Mapper> mappers;

    public MappersRegistry() {
        mappers = Map.of(
                NUMBER, new NumberTypeMapper(),
                INTEGER, new IntegerTypeMapper()
        );

        // TODO: Add more mappers
    }

    public Mapper getMapper(JsonType type) {
        return mappers.get(type);
    }

}
