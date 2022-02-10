package denshchikov.dmitry.parser.strategy;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Map;

import static denshchikov.dmitry.config.CLIArgument.PATH_TO_FILE_ARG;

/**
 * Strategy for retrieving and parsing json content through the directory file.
 */
class FileParsingStrategy extends ParsingStrategy {

    @Override
    public JsonNode getJson(Map<String, String> args) {
        String path = args.get(PATH_TO_FILE_ARG.getName());

        try {
            return om.readTree(new File(path));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    boolean isAppropriate(Map<String, String> args) {
        return args.get(PATH_TO_FILE_ARG.getName()) != null;
    }

}
