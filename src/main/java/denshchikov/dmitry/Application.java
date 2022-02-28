package denshchikov.dmitry;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Guice;
import com.google.inject.Injector;
import denshchikov.dmitry.config.BasicModule;
import denshchikov.dmitry.converter.Converter;
import denshchikov.dmitry.mapper.MappersModule;
import denshchikov.dmitry.parser.strategy.ParsingStrategiesRegistry;
import denshchikov.dmitry.parser.strategy.ParsingStrategy;
import denshchikov.dmitry.parser.cli.CLIArgumentsParser;
import denshchikov.dmitry.parser.strategy.StrategiesModule;

import java.util.Map;

public class Application {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new BasicModule(), new StrategiesModule(), new MappersModule());

        ParsingStrategiesRegistry parsingStrategiesRegistry = injector.getInstance(ParsingStrategiesRegistry.class);

        Map<String, String> argsMap = CLIArgumentsParser.getAsMap(args);

        ParsingStrategy parsingStrategy = parsingStrategiesRegistry.getAppropriateStrategy(argsMap);
        JsonNode json = parsingStrategy.getJson(argsMap);

        Converter converter = injector.getInstance(Converter.class);
        ObjectNode avroRoot = (ObjectNode) converter.convert(json);

        String avro = avroRoot.toPrettyString();
        System.out.println(avro);
    }

}
