package denshchikov.dmitry.parser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.MappingJsonFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.Map;

import static denshchikov.dmitry.config.CLIArgument.CONTENT_VIA_CLI;

/**
 * Strategy for parsing json content through the CLI.
 */
class ConsoleContentParsingStrategy extends ParsingStrategy {

    private static final JsonFactory factory = new MappingJsonFactory();


    @Override
    public JsonParser getJson(Map<String, String> args) {
        System.out.println("Please provide JSON content: ");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        JsonParser parser;
        try {
            parser = factory.createParser(br);

            JsonToken current = parser.nextToken();
            if (current != JsonToken.START_OBJECT) {
                throw new IllegalArgumentException("Root element should be object");
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return parser;
    }

    @Override
    boolean isAppropriate(Map<String, String> args) {
        return Boolean.parseBoolean(args.get(CONTENT_VIA_CLI.getName()));
    }

}
