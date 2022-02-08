package denshchikov.dmitry.parser;

import com.fasterxml.jackson.core.JsonParser;

import java.util.Map;

/**
 * Abstract strategy for different implementations of retrieving and parsing json file
 */
public abstract class ParsingStrategy {

    public abstract JsonParser getJson(Map<String, String> args);

    /**
     * Qualify whether the strategy is appropriate or not.
     * Has package-private access level because it should not be used in other places except of strategies registry.
     *
     * @param args CLI args
     * @return true if strategy is appropriate, otherwise return false.
     */
    abstract boolean isAppropriate(Map<String, String> args);

}
