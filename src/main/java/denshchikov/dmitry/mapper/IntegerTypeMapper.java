package denshchikov.dmitry.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class IntegerTypeMapper implements Mapper {

    @Override
    public void map(JsonNode jsonNode, JsonNode avroNode) {
        ((ObjectNode) avroNode).put("type", "string");

        // TODO: Add more implementation details
    }

}
