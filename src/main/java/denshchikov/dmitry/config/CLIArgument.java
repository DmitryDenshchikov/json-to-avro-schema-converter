package denshchikov.dmitry.config;

/**
 * This enum describes possible arguments
 */
public enum CLIArgument {

    PATH_TO_FILE_ARG("path-to-file");


    private final String name;

    CLIArgument(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
