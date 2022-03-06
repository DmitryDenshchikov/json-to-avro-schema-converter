package denshchikov.dmitry.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import denshchikov.dmitry.utils.NameGenerator;

import java.util.LinkedList;

class ArrayTypeMapper extends Mapper {

    public ArrayTypeMapper(LinkedList<Mapper> nextMappers, ObjectMapper objectMapper) {
        super(nextMappers, objectMapper);

        if (nextMappers.size() > 1) {
            throw new IllegalArgumentException("Array mapper could not have more than one nested mapper");
        }
    }


    @Override
    public JsonNode mapAsNested(String nodeName, JsonNode nodeContent) {
        ObjectNode avroNode = objectMapper.createObjectNode();
        avroNode.put("name", nodeName);

        ObjectNode arrayNode = objectMapper.createObjectNode();
        avroNode.set("type", arrayNode);

        arrayNode.put("type", "array");

        String uniqueItemName = NameGenerator.getArrayItemUniqueName();

        JsonNode items = nextMappers.remove().mapAsSchema(uniqueItemName, nodeContent.get("items"));

        arrayNode.set("items", items);

        return avroNode;
    }

    @Override
    public JsonNode mapAsSchema(String nodeName, JsonNode nodeContent) {
        ObjectNode avroNode = objectMapper.createObjectNode();
        avroNode.put("type", "array");
        avroNode.put("name", nodeName);

        String uniqueItemName = NameGenerator.getArrayItemUniqueName();

        JsonNode items = nextMappers.get(0).mapAsSchema(uniqueItemName, nodeContent.get("items"));

        avroNode.set("items", items);

        return avroNode;
    }

}
