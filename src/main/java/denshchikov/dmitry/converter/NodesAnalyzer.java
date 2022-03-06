package denshchikov.dmitry.converter;

import com.fasterxml.jackson.databind.JsonNode;
import denshchikov.dmitry.mapper.Mapper;
import denshchikov.dmitry.mapper.MappersFactory;
import denshchikov.dmitry.model.JsonType;

import javax.inject.Inject;
import java.util.LinkedList;

public record NodesAnalyzer(MappersFactory mappersFactory) {

    private static final LinkedList<Mapper> EMPTY_MAPPERS_LIST = new LinkedList<>();

    @Inject
    public NodesAnalyzer {
    }

    /**
     * Analyze json nodes starting from the root and creating mappers chain (chain of responsibilities)
     *
     * @param rootContent json node content
     * @return root chain mapper
     */
    public Mapper analyze(JsonNode rootContent) {
        JsonType type = JsonType.of(rootContent.get("type").asText());

        if (type.isPrimitive()) {
            return mappersFactory.getMapper(type, EMPTY_MAPPERS_LIST);
        } else {
            if (JsonType.OBJECT == type) {
                JsonNode properties = rootContent.get("properties");

                LinkedList<Mapper> fieldsMappers = new LinkedList<>();
                properties.fields().forEachRemaining(jsonNodeEntry -> fieldsMappers.add(analyze(jsonNodeEntry.getValue())));

                return mappersFactory.getMapper(type, fieldsMappers);
            } else if (JsonType.ARRAY == type) {
                Mapper itemsMapper = analyze(rootContent.get("items"));

                LinkedList<Mapper> itemsMappers = new LinkedList<>();
                itemsMappers.add(itemsMapper);

                return mappersFactory.getMapper(type, itemsMappers);
            } else {
                throw new UnsupportedOperationException("At this version there is no possibility for parsing other " +
                        "complex types except of OBJECT and ARRAY");
            }
        }
    }

}