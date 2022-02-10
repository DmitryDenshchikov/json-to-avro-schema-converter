package denshchikov.dmitry.parser.strategy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * Abstract strategy for different implementations of retrieving and parsing json content
 */
public abstract class ParsingStrategy {

    /**
     * Mapper for parsing retrieved content. Should be accessible only by
     * implemented strategies
     */
    final ObjectMapper om = new ObjectMapper();

    /**
     * Retrieves json content by different ways, parses it and returns as {@link JsonNode}
     * for next processing.
     *
     * @param args CLI args
     * @return {@link JsonNode} root object
     */
    public abstract JsonNode getJson(Map<String, String> args);

    /**
     * Qualify whether the strategy is appropriate or not.
     * Has package-private access level because it should not be used in other places
     * except of strategies registry (see {@link ParsingStrategiesRegistry}
     *
     * @param args CLI args
     * @return true if strategy is appropriate, otherwise returns false.
     */
    abstract boolean isAppropriate(Map<String, String> args);

}
