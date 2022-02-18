package denshchikov.dmitry.mapper;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Mapper for json node to avro node transformation.
 * <p>
 * Important: Please don't forget to register implementation in {@link MappersRegistry}.
 */
// TODO: Add explanation about types transformations, because some json type are covered by several
//  avro types
public interface Mapper {

    /**
     * Map json node to given (already instantiated) avro node. It is usual that given avro node has already
     * filled "name" field, but it is not necessary.
     *
     * @param jsonNode not null json node.
     * @param avroNode not null avro node which should have filled "name" field, but it is not necessary.
     */
    void map(JsonNode jsonNode, JsonNode avroNode);

}
