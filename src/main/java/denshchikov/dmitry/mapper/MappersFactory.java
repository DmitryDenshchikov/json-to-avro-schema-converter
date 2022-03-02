package denshchikov.dmitry.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import denshchikov.dmitry.model.JsonType;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.Map;

/**
 * Mappers container. Just stores and distributes {@link Mapper} objects associated with appropriate json types.
 * See {@link Mapper} for details.
 */
public record MappersFactory(ObjectMapper om, Map<JsonType, Class<? extends Mapper>> mappers) {

    @Inject
    public MappersFactory {
    }

    public Mapper getMapper(JsonType type, LinkedList<Mapper> chainMappers) {
        try {
            return mappers.get(type).getConstructor(LinkedList.class, ObjectMapper.class).newInstance(chainMappers, om);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

}
