package denshchikov.dmitry.samples;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static denshchikov.dmitry.config.AppConfig.OBJECT_MAPPER;

public class ArrayTypeMapperTestArgumentsProvider {

    public static Stream<? extends Arguments> mapAsNestedSamples() throws JsonProcessingException {
        return Stream.of(
                Arguments.of(
                        OBJECT_MAPPER.readTree("""
                                {
                                    "type": "array",
                                    "items": {
                                        "type": "string"
                                    }
                                }
                                """),
                        OBJECT_MAPPER.readTree("""
                                    {
                                        "name" : "arrayItem",
                                        "type" : "string"
                                    }
                                """),
                        OBJECT_MAPPER.readTree("""
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
                                """)
                ),
                Arguments.of(
                        OBJECT_MAPPER.readTree("""
                                {
                                    "type": "array",
                                    "items": {
                                        "type": "number"
                                    }
                                }
                                """),
                        OBJECT_MAPPER.readTree("""
                                    {
                                        "name" : "arrayItem",
                                        "type" : "double"
                                    }
                                """),
                        OBJECT_MAPPER.readTree("""
                                    {
                                        "name" : "TestSchemaName",
                                        "type" : {
                                          "type" : "array",
                                          "items" : {
                                            "name" : "arrayItem",
                                            "type" : "double"
                                          }
                                        }
                                      }
                                """)
                )
        );
    }

    public static Stream<? extends Arguments> mapAsSchemaSamples() throws JsonProcessingException {
        return Stream.of(
                Arguments.of(
                        OBJECT_MAPPER.readTree("""
                                {
                                    "type": "array",
                                    "items": {
                                        "type": "string"
                                    }
                                }
                                """),
                        OBJECT_MAPPER.readTree("""
                                    {
                                        "name" : "arrayItem",
                                        "type" : "string"
                                    }
                                """),
                        OBJECT_MAPPER.readTree("""
                                    {
                                        "name" : "TestSchemaName",
                                        "type" : "array",
                                          "items" : {
                                            "name" : "arrayItem",
                                            "type" : "string"
                                          }
                                      }
                                """)
                ),
                Arguments.of(
                        OBJECT_MAPPER.readTree("""
                                {
                                    "type": "array",
                                    "items": {
                                        "type": "number"
                                    }
                                }
                                """),
                        OBJECT_MAPPER.readTree("""
                                    {
                                        "name" : "arrayItem",
                                        "type" : "double"
                                    }
                                """),
                        OBJECT_MAPPER.readTree("""
                                    {
                                        "name" : "TestSchemaName",
                                        "type" : "array",
                                          "items" : {
                                            "name" : "arrayItem",
                                            "type" : "double"
                                          }
                                      }
                                """)
                )
        );
    }
}