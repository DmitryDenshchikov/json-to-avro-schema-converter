package denshchikov.dmitry.parser.strategy;

import com.fasterxml.jackson.databind.JsonNode;

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

    @Override
    public JsonNode getJson(Map<String, String> args) {
        System.out.println("Please provide JSON content: ");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            return om.readTree(br);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    boolean isAppropriate(Map<String, String> args) {
        return Boolean.parseBoolean(args.get(CONTENT_VIA_CLI.getName()));
    }

}
