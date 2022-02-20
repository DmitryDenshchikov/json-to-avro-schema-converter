package denshchikov.dmitry.parser.strategy;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.multibindings.Multibinder;

public class StrategiesModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<ParsingStrategy> uriBinder = Multibinder.newSetBinder(binder(), ParsingStrategy.class);
        uriBinder.addBinding().to(ConsoleContentParsingStrategy.class).in(Scopes.SINGLETON);
        uriBinder.addBinding().to(FileParsingStrategy.class).in(Scopes.SINGLETON);

        bind(ParsingStrategiesRegistry.class).in(Scopes.SINGLETON);
    }

}
