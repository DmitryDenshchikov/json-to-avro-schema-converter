package denshchikov.dmitry.parser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.MappingJsonFactory;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Map;

import static denshchikov.dmitry.config.CLIArgument.PATH_TO_FILE_ARG;

/**
 * Strategy for retrieving and parsing json through the directory file.
 */
class FileParsingStrategy extends ParsingStrategy {

    private static final JsonFactory factory = new MappingJsonFactory();


    @Override
    public JsonParser getJson(Map<String, String> args) {
        String path = args.get(PATH_TO_FILE_ARG.getName());

        JsonParser parser;
        try {
            parser = factory.createParser(new File(path));

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
        return args.get(PATH_TO_FILE_ARG.getName()) != null;
    }

}
