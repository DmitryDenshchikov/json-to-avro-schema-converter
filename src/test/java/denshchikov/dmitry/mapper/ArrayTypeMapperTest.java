package denshchikov.dmitry.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ArrayTypeMapperTest {

    LinkedList<Mapper> nestedMappers = new LinkedList<>();
    ObjectMapper om = new ObjectMapper();
    ArrayTypeMapper mapper = new ArrayTypeMapper(nestedMappers, om);

    @BeforeEach
    void init() {
        nestedMappers.clear();
    }

    @Test
    void mapAsNested_simpleJsonStringsArray_Success() throws JsonProcessingException {
        StringTypeMapper stringTypeMapperMock = mock(StringTypeMapper.class);
        nestedMappers.add(stringTypeMapperMock);

        String testJsonArray = """
                {
                    "type": "array",
                    "items": {
                        "type": "string"
                    }
                }
                """;
        JsonNode jsonNode = om.readTree(testJsonArray);

        JsonNode mockAnswer = om.readTree(
                """
                            {
                                "name" : "arrayItem",
                                "type" : "string"
                            }
                        """
        );
        when(stringTypeMapperMock.mapAsSchema(anyString(), eq(om.readTree("{\"type\": \"string\"}"))))
                .thenReturn(mockAnswer);

        JsonNode resultAvroNode = mapper.mapAsNested("TestSchemaName", jsonNode);


        JsonNode expectedAvroNode = om.readTree(
                """
                            {
                                "name" : "TestSchemaName",
                                "type" : {
                                  "type" : "array",
                                  "items" : {
                                    "name" : "arrayItem",
                                    "type" : "string"
                                  }
                                }
                              }
                        """
        );

        assertEquals(expectedAvroNode, resultAvroNode);
    }

    @Test
    void mapAsSchema_simpleJsonStringsArray_Success() throws JsonProcessingException {
        StringTypeMapper stringTypeMapperMock = mock(StringTypeMapper.class);
        nestedMappers.add(stringTypeMapperMock);

        String testJsonArray = """
                {
                    "type": "array",
                    "items": {
                        "type": "string"
                    }
                }
                """;
        JsonNode jsonNode = om.readTree(testJsonArray);

        JsonNode mockAnswer = om.readTree(
                """
                            {
                                "name" : "arrayItem",
                                "type" : "string"
                            }
                        """
        );
        when(stringTypeMapperMock.mapAsSchema(anyString(), eq(om.readTree("{\"type\": \"string\"}"))))
                .thenReturn(mockAnswer);

        JsonNode resultAvroNode = mapper.mapAsSchema("TestSchemaName", jsonNode);


        JsonNode expectedAvroNode = om.readTree(
                """
                            {
                                "name" : "TestSchemaName",
                                "type" : "array",
                                  "items" : {
                                    "name" : "arrayItem",
                                    "type" : "string"
                                   }
                              }
                        """
        );

        assertEquals(expectedAvroNode, resultAvroNode);
    }

}