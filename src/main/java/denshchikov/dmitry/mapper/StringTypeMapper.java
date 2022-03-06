package denshchikov.dmitry.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.inject.Inject;
import java.util.LinkedList;

class StringTypeMapper extends PrimitiveTypeMapper {

    @Inject
    public StringTypeMapper(LinkedList<Mapper> nextMappers, ObjectMapper objectMapper) {
        super(nextMappers, objectMapper);
    }


    @Override
    public JsonNode mapAsNested(String nodeName, JsonNode nodeContent) {
        return map(nodeName, nodeContent);
    }

    @Override
    public JsonNode mapAsSchema(String nodeName, JsonNode nodeContent) {
        return map(nodeName, nodeContent);
    }

    public JsonNode map(String nodeName, JsonNode nodeContent) {
        ObjectNode avroSchema = objectMapper.createObjectNode();
        avroSchema.put("name", nodeName);
        avroSchema.put("type", "string");
        return avroSchema;
    }

}
