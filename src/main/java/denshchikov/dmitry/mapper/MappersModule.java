package denshchikov.dmitry.mapper;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.multibindings.MapBinder;
import denshchikov.dmitry.model.JsonType;

import static denshchikov.dmitry.model.JsonType.INTEGER;
import static denshchikov.dmitry.model.JsonType.NUMBER;

public class MappersModule extends AbstractModule {

    @Override
    protected void configure() {
        MapBinder<JsonType, Mapper> mapBinder
                = MapBinder.newMapBinder(binder(), JsonType.class, Mapper.class);
        mapBinder.addBinding(NUMBER).to(NumberTypeMapper.class).in(Scopes.SINGLETON);
        mapBinder.addBinding(INTEGER).to(IntegerTypeMapper.class).in(Scopes.SINGLETON);

        bind(MappersRegistry.class).in(Scopes.SINGLETON);
    }

}