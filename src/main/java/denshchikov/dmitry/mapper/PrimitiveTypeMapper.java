package denshchikov.dmitry.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import java.util.Map;

public abstract class PrimitiveTypeMapper extends Mapper {

    @Inject
    public PrimitiveTypeMapper(ObjectMapper objectMapper) {
        super(objectMapper);
    }


    /**
     * Map json node with primitive type to an avro node
     *
     * @param jsonNodeEntry pair of json node name and node content
     * @return avro primitive type node
     */
    public abstract JsonNode map(Map.Entry<String, JsonNode> jsonNodeEntry);

}
