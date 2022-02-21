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

}
