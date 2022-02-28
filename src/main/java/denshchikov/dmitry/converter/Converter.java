package denshchikov.dmitry.converter;

import com.fasterxml.jackson.databind.JsonNode;

import javax.inject.Inject;

import denshchikov.dmitry.mapper.ComplexTypeMapper;
import denshchikov.dmitry.mapper.MappersRegistry;
import denshchikov.dmitry.mapper.PrimitiveTypeMapper;
import denshchikov.dmitry.model.JsonType;

import java.util.Map;
import java.util.function.Function;

/**
 * Main converter between json and avro schemas.
 */
// TODO: Add more implementation details
public class Converter {

    private final MappersRegistry mappersRegistry;

    @Inject
    public Converter(MappersRegistry registry) {
        this.mappersRegistry = registry;
    }


    /**
     * Converts json content to avro scheme.
     * <p>
     * First, it initializes avro root node because there are some differences between initializing
     * avro root node and nested nodes.
     *
     * @param jsonRoot json root node
     * @return avro root node
     */
    public JsonNode convert(JsonNode jsonRoot) {
        String rootName = "MyAvroSchema";
        if (jsonRoot.get("title") != null) {
            rootName = jsonRoot.get("title").asText();
        }
        JsonNode avroRoot;

        JsonType type = JsonType.of(jsonRoot.get("type").asText());
        if (JsonType.OBJECT == type || JsonType.ARRAY == type) {
            avroRoot = mappersRegistry.getMapper(type, ComplexTypeMapper.class)
                    .mapAsSchema(Map.entry(rootName, jsonRoot), mapAsNested);
        } else {
            avroRoot = mappersRegistry.getMapper(type, PrimitiveTypeMapper.class).map(Map.entry(rootName, jsonRoot));
        }

        return avroRoot;
    }

    /**
     * This function is needed because object and array mappings should be also present in separate classes (like
     * primitive type mappers {@link PrimitiveTypeMapper}). But for object and array mappings we need
     * to map nested nodes. To avoid injecting {@link MappersRegistry} in mappers (it would cause circular dependency),
     * was decided to send handling as callback parameter.
     * This function represents mapping when in Avro node must be present in next view (e.g. as record field):
     * <pre><code>
     * {
     *     "type" : {
     *         "name" : "some name"
     *         "type" : "some type"
     *         ...
     *     }
     * }
     * </code></pre>
     */
    private final Function<Map.Entry<String, JsonNode>, JsonNode> mapAsNested = new Function<>() {
        @Override
        public JsonNode apply(Map.Entry<String, JsonNode> jsonEntry) {
            JsonNode avroNode;

            JsonType type = JsonType.of(jsonEntry.getValue().get("type").asText());
            if (JsonType.OBJECT == type) {
                avroNode = mappersRegistry.getMapper(type, ComplexTypeMapper.class)
                        .mapAsNested(jsonEntry, mapAsNested);
            } else if (JsonType.ARRAY == type) {
                avroNode = mappersRegistry.getMapper(type, ComplexTypeMapper.class)
                        .mapAsNested(jsonEntry, mapAsSchema);
            } else {
                avroNode = mappersRegistry.getMapper(type, PrimitiveTypeMapper.class).map(jsonEntry);
            }

            return avroNode;
        }
    };

    /**
     * This function is needed because object and array mappings should be also present in separate classes (like
     * primitive type mappers {@link PrimitiveTypeMapper}). But for object and array mappings we need
     * to map nested nodes. To avoid injecting {@link MappersRegistry} in mappers (it would cause circular dependency),
     * was decided to send handling as callback parameter.
     * This function represents mapping when in Avro node must be present in next view (e.g. as array item):
     * <pre><code>
     * {
     *      "name" : "some name"
     *      "type" : "some type"
     * }
     * </code></pre>
     */
    private final Function<Map.Entry<String, JsonNode>, JsonNode> mapAsSchema = new Function<>() {
        @Override
        public JsonNode apply(Map.Entry<String, JsonNode> jsonEntry) {
            JsonNode avroNode;

            JsonType type = JsonType.of(jsonEntry.getValue().get("type").asText());
            if (JsonType.OBJECT == type) {
                avroNode = mappersRegistry.getMapper(type, ComplexTypeMapper.class)
                        .mapAsSchema(jsonEntry, mapAsNested);
            } else if (JsonType.ARRAY == type) {
                avroNode = mappersRegistry.getMapper(type, ComplexTypeMapper.class)
                        .mapAsSchema(jsonEntry, mapAsSchema);
            } else {
                avroNode = mappersRegistry.getMapper(type, PrimitiveTypeMapper.class).map(jsonEntry);
            }

            return avroNode;
        }
    };


}
