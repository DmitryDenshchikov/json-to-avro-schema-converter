package denshchikov.dmitry;

import org.apache.commons.cli.*;

public class Application {

    public static void main(String[] args) {
        CommandLineArgumentsConfig commandLineArgumentsConfig = new CommandLineArgumentsConfig();

        CommandLine cmd;
        try {
            CommandLineParser parser = new DefaultParser();
            cmd = parser.parse(commandLineArgumentsConfig.getConfig(), args);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    final static class CommandLineArgumentsConfig {
        private Options getConfig() {
            Options options = new Options();
            options.addOption("path-to-file", true, "path to json file");

            return options;
        }
    }

}
