package denshchikov.dmitry.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.inject.Inject;
import java.util.Map;

class NumberTypeMapper extends PrimitiveTypeMapper {

    @Inject
    public NumberTypeMapper(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    public JsonNode map(Map.Entry<String, JsonNode> jsonNodeEntry) {
        ObjectNode avroSchema = objectMapper.createObjectNode();
        avroSchema.put("name", jsonNodeEntry.getKey());
        avroSchema.put("type", "double");
        return avroSchema;
    }

}
