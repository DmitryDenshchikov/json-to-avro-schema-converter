package denshchikov.dmitry.samples;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Map;
import java.util.stream.Stream;

import static denshchikov.dmitry.config.AppConfig.OBJECT_MAPPER;

public class ObjectTypeMapperTestArgumentsProvider {

    public static Stream<? extends Arguments> mapAsNestedSamples() throws JsonProcessingException {
        return Stream.of(
                Arguments.of(
                        OBJECT_MAPPER.readTree("""
                                {
                                    "type": "object",
                                    "properties": {
                                        "prop1": {
                                            "type": "string"
                                        },
                                        "prop2": {
                                            "type": "integer"
                                        },
                                        "prop3": {
                                            "type": "number"
                                        }
                                    }
                                }
                                """),

                        Map.of(
                                "prop1", OBJECT_MAPPER.readTree("""
                                            {
                                                "name" : "prop1",
                                                "type" : "string"
                                            }
                                        """),
                                "prop2", OBJECT_MAPPER.readTree("""
                                            {
                                                "name" : "prop2",
                                                "type" : "integer"
                                            }
                                        """),
                                "prop3", OBJECT_MAPPER.readTree("""
                                            {
                                                "name" : "prop3",
                                                "type" : "double"
                                            }
                                        """)
                        ),

                        OBJECT_MAPPER.readTree("""
                                    {
                                        "name" : "TestSchemaName",
                                        "type" : {
                                              "name" : "TestSchemaNameRecord",
                                              "type" : "record",
                                              "fields" : [
                                                    {
                                                        "name" : "prop1",
                                                        "type" : "string"
                                                    },
                                                    {
                                                        "name" : "prop2",
                                                        "type" : "integer"
                                                    },
                                                    {
                                                        "name" : "prop3",
                                                        "type" : "double"
                                                    }
                                              ]
                                        }
                                      }
                                """)
                )
        );
    }
}