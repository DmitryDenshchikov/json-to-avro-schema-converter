package denshchikov.dmitry.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.LinkedList;
import java.util.Map;

class ObjectTypeMapper extends Mapper {

    public ObjectTypeMapper(LinkedList<Mapper> nextMappers, ObjectMapper objectMapper) {
        super(nextMappers, objectMapper);
    }


    @Override
    public JsonNode mapAsNested(Map.Entry<String, JsonNode> jsonNodeEntry) {
        ObjectNode avroNode = objectMapper.createObjectNode();
        avroNode.put("name", jsonNodeEntry.getKey());

        ObjectNode recordNode = objectMapper.createObjectNode();
        avroNode.set("type", recordNode);

        recordNode.put("name", jsonNodeEntry.getKey() + "Record");
        recordNode.put("type", "record");

        ArrayNode arrayNode = objectMapper.createArrayNode();
        recordNode.set("fields", arrayNode);

        JsonNode properties = jsonNodeEntry.getValue().get("properties");

        properties.fields().forEachRemaining(property -> arrayNode.add(nextMappers.remove().mapAsNested(property)));

        return avroNode;
    }

    @Override
    public JsonNode mapAsSchema(Map.Entry<String, JsonNode> jsonNodeEntry) {
        ObjectNode avroNode = objectMapper.createObjectNode();
        avroNode.put("type", "record");
        avroNode.put("name", jsonNodeEntry.getKey());

        ArrayNode arrayNode = objectMapper.createArrayNode();
        avroNode.set("fields", arrayNode);

        JsonNode properties = jsonNodeEntry.getValue().get("properties");

        properties.fields().forEachRemaining(property -> arrayNode.add(nextMappers.remove().mapAsNested(property)));

        return avroNode;
    }


}
