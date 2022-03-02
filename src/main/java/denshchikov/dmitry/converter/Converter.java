package denshchikov.dmitry.converter;

import com.fasterxml.jackson.databind.JsonNode;

import javax.inject.Inject;

import denshchikov.dmitry.mapper.Mapper;

import java.util.Map;

/**
 * Main converter between json and avro schemas.
 */
// TODO: Add more implementation details
public record Converter(NodesAnalyzer nodesAnalyzer) {

    @Inject
    public Converter {
    }

    /**
     * Converts json content to avro scheme.
     * <p>
     * Firstly, {@link NodesAnalyzer} is called for create chain of responsibility (mappers chaing).
     * After that, root chain mapper is called. After passing the chain, root avro node is returned.
     *
     * @param jsonRoot json root node
     * @return avro root node
     */
    public JsonNode convert(JsonNode jsonRoot) {
        String rootName = "MyAvroSchema";
        if (jsonRoot.get("title") != null) {
            rootName = jsonRoot.get("title").asText();
        }

        Mapper mapperChainRoot = nodesAnalyzer.analyze(Map.entry(rootName, jsonRoot));

        return mapperChainRoot.mapAsSchema(Map.entry(rootName, jsonRoot));
    }
//
//    /**
//     * This function is needed because object and array mappings should be also present in separate classes (like
//     * primitive type mappers {@link PrimitiveTypeMapper}). But for object and array mappings we need
//     * to map nested nodes. To avoid injecting {@link MappersRegistry} in mappers (it would cause circular dependency),
//     * was decided to send handling as callback parameter.
//     * This function represents mapping when in Avro node must be present in next view (e.g. as record field):
//     * <pre><code>
//     * {
//     *     "type" : {
//     *         "name" : "some name"
//     *         "type" : "some type"
//     *         ...
//     *     }
//     * }
//     * </code></pre>
//     */
//    private final Function<Map.Entry<String, JsonNode>, JsonNode> mapAsNested = new Function<>() {
//        @Override
//        public JsonNode apply(Map.Entry<String, JsonNode> jsonEntry) {
//            JsonNode avroNode;
//
//            JsonType type = JsonType.of(jsonEntry.getValue().get("type").asText());
//            if (JsonType.OBJECT == type) {
//                avroNode = mappersRegistry.getMapper(type, ComplexTypeMapper.class)
//                        .mapAsNested(jsonEntry, mapAsNested);
//            } else if (JsonType.ARRAY == type) {
//                avroNode = mappersRegistry.getMapper(type, ComplexTypeMapper.class)
//                        .mapAsNested(jsonEntry, mapAsSchema);
//            } else {
//                avroNode = mappersRegistry.getMapper(type, PrimitiveTypeMapper.class).map(jsonEntry);
//            }
//
//            return avroNode;
//        }
//    };
//
//    /**
//     * This function is needed because object and array mappings should be also present in separate classes (like
//     * primitive type mappers {@link PrimitiveTypeMapper}). But for object and array mappings we need
//     * to map nested nodes. To avoid injecting {@link MappersRegistry} in mappers (it would cause circular dependency),
//     * was decided to send handling as callback parameter.
//     * This function represents mapping when in Avro node must be present in next view (e.g. as array item):
//     * <pre><code>
//     * {
//     *      "name" : "some name"
//     *      "type" : "some type"
//     * }
//     * </code></pre>
//     */
//    private final Function<Map.Entry<String, JsonNode>, JsonNode> mapAsSchema = new Function<>() {
//        @Override
//        public JsonNode apply(Map.Entry<String, JsonNode> jsonEntry) {
//            JsonNode avroNode;
//
//            JsonType type = JsonType.of(jsonEntry.getValue().get("type").asText());
//            if (JsonType.OBJECT == type) {
//                avroNode = mappersRegistry.getMapper(type, ComplexTypeMapper.class)
//                        .mapAsSchema(jsonEntry, mapAsNested);
//            } else if (JsonType.ARRAY == type) {
//                avroNode = mappersRegistry.getMapper(type, ComplexTypeMapper.class)
//                        .mapAsSchema(jsonEntry, mapAsSchema);
//            } else {
//                avroNode = mappersRegistry.getMapper(type, PrimitiveTypeMapper.class).map(jsonEntry);
//            }
//
//            return avroNode;
//        }
//    };
//

}
