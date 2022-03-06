package denshchikov.dmitry.converter;

import com.fasterxml.jackson.databind.JsonNode;

import javax.inject.Inject;

import denshchikov.dmitry.mapper.Mapper;

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

        Mapper mapperChainRoot = nodesAnalyzer.analyze(jsonRoot);

        return mapperChainRoot.mapAsSchema(rootName, jsonRoot);
    }

}
