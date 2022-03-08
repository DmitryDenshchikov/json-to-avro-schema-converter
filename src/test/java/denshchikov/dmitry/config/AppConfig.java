package denshchikov.dmitry.config;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class AppConfig {

    public final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private AppConfig() {
        throw new UnsupportedOperationException("This is a factory class");
    }

}
