package denshchikov.dmitry.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.Map;

class StringTypeMapper extends PrimitiveTypeMapper {

    @Inject
    public StringTypeMapper(LinkedList<Mapper> nextMappers, ObjectMapper objectMapper) {
        super(nextMappers, objectMapper);
    }


    @Override
    public JsonNode mapAsNested(Map.Entry<String, JsonNode> jsonNodeEntry) {
        return map(jsonNodeEntry);
    }

    @Override
    public JsonNode mapAsSchema(Map.Entry<String, JsonNode> jsonNodeEntry) {
        return map(jsonNodeEntry);
    }

    public JsonNode map(Map.Entry<String, JsonNode> jsonNodeEntry) {
        ObjectNode avroSchema = objectMapper.createObjectNode();
        avroSchema.put("name", jsonNodeEntry.getKey());
        avroSchema.put("type", "string");
        return avroSchema;
    }

}
