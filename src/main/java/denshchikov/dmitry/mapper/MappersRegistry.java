package denshchikov.dmitry.mapper;

import denshchikov.dmitry.model.JsonType;

import javax.inject.Inject;
import java.util.Map;

/**
 * Mappers container. Just stores and distributes {@link Mapper} objects associated with appropriate json types.
 * See {@link Mapper} for details.
 */
public record MappersRegistry(Map<JsonType, Mapper> mappers) {

    @Inject
    public MappersRegistry(Map<JsonType, Mapper> mappers) {
        this.mappers = mappers;
    }

    public Mapper getMapper(JsonType type) {
        return mappers.get(type);
    }

    public <T> T getMapper(JsonType type, Class<T> clazz) throws ClassCastException {
        Mapper mapper = mappers.get(type);

        if (clazz.isInstance(mapper)) {
            return clazz.cast(mapper);
        } else {
            throw new IllegalArgumentException("There is no " + clazz + " for json type " + type);
        }
    }

}
