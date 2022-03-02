package denshchikov.dmitry.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import denshchikov.dmitry.utils.NameGenerator;

import java.util.LinkedList;
import java.util.Map;

class ArrayTypeMapper extends Mapper {

    public ArrayTypeMapper(LinkedList<Mapper> nextMappers, ObjectMapper objectMapper) {
        super(nextMappers, objectMapper);

        if (nextMappers.size() > 1) {
            throw new IllegalArgumentException("Array mapper could not have more than one nested mapper");
        }
    }


    @Override
    public JsonNode mapAsNested(Map.Entry<String, JsonNode> jsonNodeEntry) {
        ObjectNode avroNode = objectMapper.createObjectNode();
        avroNode.put("name", jsonNodeEntry.getKey());

        ObjectNode arrayNode = objectMapper.createObjectNode();
        avroNode.set("type", arrayNode);

        arrayNode.put("type", "array");

        String uniqueItemName = NameGenerator.getArrayItemUniqueName();

        JsonNode items = nextMappers.remove().mapAsSchema(Map.entry(uniqueItemName, jsonNodeEntry.getValue().get("items")));

        arrayNode.set("items", items);

        return avroNode;
    }

    @Override
    public JsonNode mapAsSchema(Map.Entry<String, JsonNode> jsonNodeEntry) {
        ObjectNode avroNode = objectMapper.createObjectNode();
        avroNode.put("type", "array");
        avroNode.put("name", jsonNodeEntry.getKey());

        String uniqueItemName = NameGenerator.getArrayItemUniqueName();

        JsonNode items = nextMappers.get(0).mapAsSchema(Map.entry(uniqueItemName, jsonNodeEntry.getValue().get("items")));

        avroNode.set("items", items);

        return avroNode;
    }

}
