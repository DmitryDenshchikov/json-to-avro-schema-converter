package denshchikov.dmitry.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedList;

import static denshchikov.dmitry.config.AppConfig.OBJECT_MAPPER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ArrayTypeMapperTest {

    LinkedList<Mapper> nestedMappers = new LinkedList<>();
    ArrayTypeMapper mapper = new ArrayTypeMapper(nestedMappers, OBJECT_MAPPER);

    @BeforeEach
    void init() {
        nestedMappers.clear();
    }

    @ParameterizedTest
    @MethodSource("denshchikov.dmitry.samples.ArrayTypeMapperTestArgumentsProvider#mapAsNestedSamples")
    void mapAsNestedSuccess(JsonNode input, JsonNode nestedMapperMockAnswer, JsonNode expected) {
        Mapper mapperMock = mock(Mapper.class);
        nestedMappers.add(mapperMock);

        when(mapperMock.mapAsSchema(anyString(), eq(input.get("items"))))
                .thenReturn(nestedMapperMockAnswer);

        JsonNode resultAvroNode = mapper.mapAsNested("TestSchemaName", input);

        assertEquals(expected, resultAvroNode);
    }

    @ParameterizedTest
    @MethodSource("denshchikov.dmitry.samples.ArrayTypeMapperTestArgumentsProvider#mapAsSchemaSamples")
    void mapAsSchemaSuccess(JsonNode input, JsonNode nestedMapperMockAnswer, JsonNode expected) {
        Mapper mapperMock = mock(Mapper.class);
        nestedMappers.add(mapperMock);

        when(mapperMock.mapAsSchema(anyString(), eq(input.get("items"))))
                .thenReturn(nestedMapperMockAnswer);

        JsonNode resultAvroNode = mapper.mapAsSchema("TestSchemaName", input);

        assertEquals(expected, resultAvroNode);
    }

}