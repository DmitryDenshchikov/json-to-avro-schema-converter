package denshchikov.dmitry;

import denshchikov.dmitry.parser.ParsingStrategiesRegistry;
import denshchikov.dmitry.parser.ParsingStrategy;
import denshchikov.dmitry.parser.cli.CLIArgumentsParser;

import java.util.Map;

public class Application {

    public static void main(String[] args) {
        Map<String, String> argsMap = CLIArgumentsParser.getAsMap(args);

        ParsingStrategy parsingStrategy = ParsingStrategiesRegistry.getAppropriateStrategy(argsMap);
        parsingStrategy.parseJson(argsMap);
    }

}
