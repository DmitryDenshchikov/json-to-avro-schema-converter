package denshchikov.dmitry.parser;

import java.util.List;
import java.util.Map;

public class ParsingStrategiesRegistry {

    private static final List<ParsingStrategy> strategies = List.of(
            new FileParsingStrategy(),
            new ConsoleContentParsingStrategy()
    );

    public static ParsingStrategy getAppropriateStrategy(Map<String, String> args) {
        return strategies.stream()
                .filter(parsingStrategy -> parsingStrategy.isAppropriate(args))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException("Parsing strategy was not found. Please provide valid arguments."));
    }

}
