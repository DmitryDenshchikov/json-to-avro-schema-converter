package denshchikov.dmitry.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import denshchikov.dmitry.utils.NameGenerator;

import javax.inject.Inject;
import java.util.Map;
import java.util.function.Function;

class ArrayTypeMapper extends ComplexTypeMapper {

    @Inject
    public ArrayTypeMapper(ObjectMapper objectMapper) {
        super(objectMapper);
    }


    @Override
    public JsonNode mapAsNested(Map.Entry<String, JsonNode> jsonNodeEntry,
                                Function<Map.Entry<String, JsonNode>, JsonNode> nestedFieldsHandling) {
        ObjectNode avroNode = objectMapper.createObjectNode();
        avroNode.put("name", jsonNodeEntry.getKey());

        ObjectNode arrayNode = objectMapper.createObjectNode();
        avroNode.set("type", arrayNode);

        arrayNode.put("type", "array");

        String uniqueItemName = NameGenerator.getArrayItemUniqueName();
        JsonNode items = nestedFieldsHandling.apply(Map.entry(uniqueItemName, jsonNodeEntry.getValue().get("items")));
        arrayNode.set("items", items);

        return avroNode;
    }

    @Override
    public JsonNode mapAsSchema(Map.Entry<String, JsonNode> jsonNodeEntry,
                                Function<Map.Entry<String, JsonNode>, JsonNode> nestedFieldsHandling) {
        ObjectNode avroNode = objectMapper.createObjectNode();
        avroNode.put("type", "array");
        avroNode.put("name", jsonNodeEntry.getKey());

        String uniqueItemName = NameGenerator.getArrayItemUniqueName();
        JsonNode items = nestedFieldsHandling.apply(Map.entry(uniqueItemName, jsonNodeEntry.getValue().get("items")));
        avroNode.set("items", items);

        return avroNode;
    }

}
