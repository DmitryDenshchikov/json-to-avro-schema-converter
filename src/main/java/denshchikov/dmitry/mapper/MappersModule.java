package denshchikov.dmitry.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import denshchikov.dmitry.model.JsonType;

import java.util.Map;

import static denshchikov.dmitry.model.JsonType.ARRAY;
import static denshchikov.dmitry.model.JsonType.BOOLEAN;
import static denshchikov.dmitry.model.JsonType.INTEGER;
import static denshchikov.dmitry.model.JsonType.NUMBER;
import static denshchikov.dmitry.model.JsonType.OBJECT;
import static denshchikov.dmitry.model.JsonType.STRING;

public class MappersModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ObjectMapper.class).in(Scopes.SINGLETON);
        bind(MappersFactory.class);
    }

    @Provides
    static Map<JsonType, Class<? extends Mapper>> provideMappersMetaMap() {
        return Map.of(
                NUMBER, NumberTypeMapper.class,
                INTEGER, IntegerTypeMapper.class,
                ARRAY, ArrayTypeMapper.class,
                OBJECT, ObjectTypeMapper.class,
                STRING, StringTypeMapper.class,
                BOOLEAN, BooleanTypeMapper.class
        );
    }

}
