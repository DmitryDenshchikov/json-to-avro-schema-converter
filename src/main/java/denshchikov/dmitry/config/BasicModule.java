package denshchikov.dmitry.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import denshchikov.dmitry.converter.Converter;

public class BasicModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ObjectMapper.class).in(Scopes.SINGLETON);
        bind(Converter.class).in(Scopes.SINGLETON);
    }

}