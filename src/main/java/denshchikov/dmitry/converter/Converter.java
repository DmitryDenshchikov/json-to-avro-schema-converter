package denshchikov.dmitry.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import denshchikov.dmitry.mapper.MappersRegistry;
import denshchikov.dmitry.model.JsonType;

import java.util.Map;

/**
 * Main converter between json and avro schemas.
 */
// TODO: Add more implementation details
public class Converter {

    private final MappersRegistry mappersRegistry;
    private final ObjectMapper mapper;

    public Converter(MappersRegistry registry, ObjectMapper mapper) {
        this.mappersRegistry = registry;
        this.mapper = mapper;
    }


    /**
     * Converts json content to avro scheme.
     * <p>
     * First, it initializes avro root node because there are some differences between initializing
     * avro root node and nested nodes.
     * E.g. if root node is record, other nested nodes must be included in "fields" attribute. But root node
     * must not be included into "fields" attribute. That's why there is
     * other method {@link #map(Map.Entry, ArrayNode)} which maps nested nodes.
     *
     * @param jsonRoot json root node
     * @return avro root node
     */
    public ObjectNode convert(JsonNode jsonRoot) {
        ObjectNode avroRoot = mapper.createObjectNode();
        avroRoot.put("name", jsonRoot.get("title").asText());

        JsonType type = JsonType.of(jsonRoot.get("type").asText());
        if (JsonType.OBJECT == type) {
            avroRoot.put("type", "record");

            ArrayNode arrayNode = mapper.createArrayNode();
            avroRoot.set("fields", arrayNode);

            JsonNode properties = jsonRoot.get("properties");
            properties.fields().forEachRemaining(jsonNodeEntry -> map(jsonNodeEntry, arrayNode));
        } else {
            mappersRegistry.getMapper(type).map(jsonRoot, avroRoot);
        }

        return avroRoot;
    }

    private void map(Map.Entry<String, JsonNode> jsonEntry, ArrayNode currentAvroFieldsBlock) {
        ObjectNode avroNode = mapper.createObjectNode();
        avroNode.put("name", jsonEntry.getKey());

        JsonType type = JsonType.of(jsonEntry.getValue().get("type").asText());
        if (JsonType.OBJECT == type) {
            ObjectNode recordNode = mapper.createObjectNode();
            avroNode.set("type", recordNode);

            recordNode.put("name", jsonEntry.getKey() + "Record");
            recordNode.put("type", "record");

            ArrayNode arrayNode = mapper.createArrayNode();
            recordNode.set("fields", arrayNode);

            JsonNode properties = jsonEntry.getValue().get("properties");
            properties.fields().forEachRemaining(jsonNodeEntry -> map(jsonNodeEntry, arrayNode));
        } else {
            mappersRegistry.getMapper(type).map(jsonEntry.getValue(), avroNode);
        }

        currentAvroFieldsBlock.add(avroNode);
    }

}
