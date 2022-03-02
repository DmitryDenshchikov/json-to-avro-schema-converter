package denshchikov.dmitry.converter;

import com.fasterxml.jackson.databind.JsonNode;
import denshchikov.dmitry.mapper.Mapper;
import denshchikov.dmitry.mapper.MappersFactory;
import denshchikov.dmitry.model.JsonType;
import denshchikov.dmitry.utils.NameGenerator;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.Map;

public record NodesAnalyzer(MappersFactory mappersFactory) {

    @Inject
    public NodesAnalyzer {
    }

    /**
     * Analyze json nodes starting from the root and creating mappers chain (chain of responsibilities)
     *
     * @param root pair of root json node name and root json node content
     * @return root chain mapper
     */
    public Mapper analyze(Map.Entry<String, JsonNode> root) {
        final LinkedList<Mapper> emptyList = new LinkedList<>();

        JsonType type = JsonType.of(root.getValue().get("type").asText());
        if (type.isPrimitive()) {
            return mappersFactory.getMapper(type, emptyList);
        } else {
            if (JsonType.OBJECT == type) {
                JsonNode properties = root.getValue().get("properties");

                LinkedList<Mapper> fieldsMappers = new LinkedList<>();
                properties.fields().forEachRemaining(jsonNodeEntry -> fieldsMappers.add(analyze(jsonNodeEntry)));

                return mappersFactory.getMapper(type, fieldsMappers);
            } else if (JsonType.ARRAY == type) {
                String uniqueItemName = NameGenerator.getArrayItemUniqueName();

                Mapper itemsMapper = analyze(Map.entry(uniqueItemName, root.getValue().get("items")));

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