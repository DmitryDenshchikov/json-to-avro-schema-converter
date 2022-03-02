package denshchikov.dmitry.config;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import denshchikov.dmitry.converter.Converter;
import denshchikov.dmitry.converter.NodesAnalyzer;

public class BasicModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(NodesAnalyzer.class).in(Scopes.SINGLETON);
        bind(Converter.class).in(Scopes.SINGLETON);
    }

}