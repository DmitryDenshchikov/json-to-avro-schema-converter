package denshchikov.dmitry.parser;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

import static denshchikov.dmitry.Application.CommandLineArgumentsConfig.PATH_TO_FILE_ARG;

/**
 * Strategy for retrieving and parsing json through the directory file.
 */
class FileParsingStrategy extends ParsingStrategy {

    @Override
    public JsonNode parseJson(Map<String, String> args) {
        // TODO: Create an implementation
        return null;
    }

    @Override
    boolean isAppropriate(Map<String, String> args) {
        return args.get(PATH_TO_FILE_ARG) != null;
    }

}
