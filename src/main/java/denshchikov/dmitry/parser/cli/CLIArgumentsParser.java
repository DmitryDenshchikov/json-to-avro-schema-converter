package denshchikov.dmitry.parser.cli;

import java.util.HashMap;
import java.util.Map;

public final class CLIArgumentsParser {

    private CLIArgumentsParser() {
        throw new UnsupportedOperationException("This is an utility class");
    }

    public static Map<String, String> getAsMap(String[] args) {
        Map<String, String> params = new HashMap<>();

        if (args == null || args.length == 0) {
            return params;
        }

        for (String arg : args) {
            int i = arg.indexOf('=');

            // We start from the second character because of skipping prefix "--"
            String argName = arg.substring(2, i);
            String argValue = arg.substring(i + 1);

            params.put(argName, argValue);
        }

        return params;
    }

}
