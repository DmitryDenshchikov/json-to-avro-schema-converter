package denshchikov.dmitry;

import denshchikov.dmitry.parser.ParsingStrategiesRegistry;
import denshchikov.dmitry.parser.ParsingStrategy;
import org.apache.commons.cli.*;

import java.util.HashMap;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        CommandLineArgumentsConfig commandLineArgumentsConfig = new CommandLineArgumentsConfig();

        Map<String, String> argsValues = new HashMap<>();
        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(commandLineArgumentsConfig.getConfig(), args);

            for (Option option : cmd.getOptions()) {
                argsValues.put(option.getArgName(), option.getValue());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ParsingStrategy parsingStrategy = ParsingStrategiesRegistry.getAppropriateStrategy(argsValues);

    }

    public final static class CommandLineArgumentsConfig {
        public static final String PATH_TO_FILE_ARG = "path-to-file";

        private Options getConfig() {
            Options options = new Options();
            options.addOption(PATH_TO_FILE_ARG, true, "path to json file");

            return options;
        }
    }

}
