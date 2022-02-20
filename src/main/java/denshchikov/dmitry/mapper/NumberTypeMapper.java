package denshchikov.dmitry.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

class NumberTypeMapper implements Mapper {

    @Override
    public void map(JsonNode jsonNode, JsonNode avroNode) {
        ((ObjectNode) avroNode).put("type", "double");

        // TODO: Add more implementation details
    }

}
