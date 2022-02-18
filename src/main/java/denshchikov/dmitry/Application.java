package denshchikov.dmitry;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import denshchikov.dmitry.converter.Converter;
import denshchikov.dmitry.mapper.MappersRegistry;
import denshchikov.dmitry.parser.strategy.ParsingStrategiesRegistry;
import denshchikov.dmitry.parser.strategy.ParsingStrategy;
import denshchikov.dmitry.parser.cli.CLIArgumentsParser;

import java.util.Map;

public class Application {

    public static void main(String[] args) {
        Map<String, String> argsMap = CLIArgumentsParser.getAsMap(args);

        ParsingStrategy parsingStrategy = ParsingStrategiesRegistry.getAppropriateStrategy(argsMap);
        JsonNode json = parsingStrategy.getJson(argsMap);

        Converter converter = new Converter(new MappersRegistry(), new ObjectMapper());
        ObjectNode avroRoot = converter.convert(json);

        String avro = avroRoot.toPrettyString();
        System.out.println(avro);
    }

}
