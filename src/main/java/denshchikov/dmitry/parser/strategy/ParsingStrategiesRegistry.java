package denshchikov.dmitry.parser.strategy;

import javax.inject.Inject;
import java.util.Set;
import java.util.Map;

public class ParsingStrategiesRegistry {

    private final Set<ParsingStrategy> strategies;

    @Inject
    public ParsingStrategiesRegistry(Set<ParsingStrategy> strategies) {
        this.strategies = strategies;
    }

    public ParsingStrategy getAppropriateStrategy(Map<String, String> args) {
        return strategies.stream()
                .filter(parsingStrategy -> parsingStrategy.isAppropriate(args))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException("Parsing strategy was not found. Please provide valid arguments."));
    }

}
