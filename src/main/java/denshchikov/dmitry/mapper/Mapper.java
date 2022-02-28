package denshchikov.dmitry.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;

/**
 * Mapper for json node to avro node transformation.
 * <p>
 * Important: Please don't forget to register implementation in {@link MappersRegistry}.
 */
public abstract class Mapper {

    final ObjectMapper objectMapper;

    @Inject
    public Mapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

}
