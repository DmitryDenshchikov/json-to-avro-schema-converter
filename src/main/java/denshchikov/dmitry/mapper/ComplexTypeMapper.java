package denshchikov.dmitry.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import java.util.Map;
import java.util.function.Function;

public abstract class ComplexTypeMapper extends Mapper {

    @Inject
    public ComplexTypeMapper(ObjectMapper objectMapper) {
        super(objectMapper);
    }


    /**
     * Map json node with complex type as nested avro node
     *
     * @param jsonNodeEntry        pair of json node name and node content
     * @param nestedFieldsHandling action for handling nested content (e.g "items" field)
     * @return avro complex type type node
     */
    public abstract JsonNode mapAsNested(Map.Entry<String, JsonNode> jsonNodeEntry,
                                         Function<Map.Entry<String, JsonNode>, JsonNode> nestedFieldsHandling);

    /**
     * Map json node with complex type as schema avro node
     *
     * @param jsonNodeEntry        pair of json node name and node content
     * @param nestedFieldsHandling action for handling items content (e.g "items" field)
     * @return avro complex type type node
     */
    public abstract JsonNode mapAsSchema(Map.Entry<String, JsonNode> jsonNodeEntry,
                                         Function<Map.Entry<String, JsonNode>, JsonNode> nestedFieldsHandling);

}
