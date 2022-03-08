package denshchikov.dmitry.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedList;
import java.util.Map;
import java.util.function.Consumer;

import static denshchikov.dmitry.config.AppConfig.OBJECT_MAPPER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ObjectTypeMapperTest {

    LinkedList<Mapper> nestedMappers = new LinkedList<>();
    ObjectTypeMapper mapper = new ObjectTypeMapper(nestedMappers, OBJECT_MAPPER);

    @BeforeEach
    void init() {
        nestedMappers.clear();
    }

    @ParameterizedTest
    @MethodSource("denshchikov.dmitry.samples.ObjectTypeMapperTestArgumentsProvider#mapAsNestedSamples")
    void mapAsNestedSuccess(JsonNode input, Map<String, JsonNode> nestedMappersMockAnswers, JsonNode expected) {
        input.get("properties").fields().forEachRemaining(new Consumer<Map.Entry<String, JsonNode>>() {
            @Override
            public void accept(Map.Entry<String, JsonNode> jsonNodeEntry) {
                Mapper mapperMock = mock(Mapper.class, jsonNodeEntry.getKey() + "Mapper");
                nestedMappers.add(mapperMock);

                when(mapperMock.mapAsNested(jsonNodeEntry.getKey(), jsonNodeEntry.getValue()))
                        .thenReturn(nestedMappersMockAnswers.get(jsonNodeEntry.getKey()));
            }
        });

        JsonNode resultAvroNode = mapper.mapAsNested("TestSchemaName", input);

        assertEquals(expected, resultAvroNode);
    }

}