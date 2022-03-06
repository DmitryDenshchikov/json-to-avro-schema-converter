package denshchikov.dmitry.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.LinkedList;

class ObjectTypeMapper extends Mapper {

    public ObjectTypeMapper(LinkedList<Mapper> nextMappers, ObjectMapper objectMapper) {
        super(nextMappers, objectMapper);
    }


    @Override
    public JsonNode mapAsNested(String nodeName, JsonNode nodeContent) {
        ObjectNode avroNode = objectMapper.createObjectNode();
        avroNode.put("name", nodeName);

        ObjectNode recordNode = objectMapper.createObjectNode();
        avroNode.set("type", recordNode);

        recordNode.put("name", nodeName + "Record");
        recordNode.put("type", "record");

        ArrayNode arrayNode = objectMapper.createArrayNode();
        recordNode.set("fields", arrayNode);

        JsonNode properties = nodeContent.get("properties");

        properties.fields()
                .forEachRemaining(
                        prop -> arrayNode.add(nextMappers.remove().mapAsNested(prop.getKey(), prop.getValue()))
                );

        return avroNode;
    }

    @Override
    public JsonNode mapAsSchema(String nodeName, JsonNode nodeContent) {
        ObjectNode avroNode = objectMapper.createObjectNode();
        avroNode.put("type", "record");
        avroNode.put("name", nodeName);

        ArrayNode arrayNode = objectMapper.createArrayNode();
        avroNode.set("fields", arrayNode);

        JsonNode properties = nodeContent.get("properties");

        properties.fields()
                .forEachRemaining(
                        prop -> arrayNode.add(nextMappers.remove().mapAsNested(prop.getKey(), prop.getValue()))
                );

        return avroNode;
    }


}
