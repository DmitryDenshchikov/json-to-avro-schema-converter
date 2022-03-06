package denshchikov.dmitry.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import java.util.LinkedList;

/**
 * Mapper for json node to avro node transformation.
 * <p>
 * Important: Please don't forget to register implementation in {@link MappersModule}.
 */
public abstract class Mapper {

    final LinkedList<Mapper> nextMappers;

    final ObjectMapper objectMapper;

    @Inject
    public Mapper(LinkedList<Mapper> nextMappers, ObjectMapper objectMapper) {
        this.nextMappers = nextMappers;
        this.objectMapper = objectMapper;
    }

    /**
     * Map json node as nested avro node.
     * <p>
     * This function represents mapping when in Avro format node must be present in next view (e.g. record node
     * nested in other record node):
     * <pre><code>
     * {
     *     "type" : {
     *         "name" : "some name"
     *         "type" : "some type"
     *         ...
     *     }
     * }
     * </code></pre>
     * <p>
     * For primitive type at present there is no differences in processing between {@link #mapAsNested(String, JsonNode)}
     * and {@link #mapAsSchema(String, JsonNode)}.
     *
     *
     * @param nodeName json node name
     * @param nodeContent json node content
     * @return avro node.
     */
    public abstract JsonNode mapAsNested(String nodeName, JsonNode nodeContent);

    /**
     * Map json node as schema avro node.
     * <p>
     * This function represents mapping when in Avro format node must be present in next view
     * (e.g. record node in array item):
     * <pre><code>
     * {
     *      "name" : "some name"
     *      "type" : "some type"
     * }
     * </code></pre>
     * <p>
     * For primitive type at present there is no differences in processing between {@link #mapAsNested(String, JsonNode)}
     * and {@link #mapAsSchema(String, JsonNode)}.
     *
     *
     * @param nodeName json node name
     * @param nodeContent json node content
     * @return avro node.
     */
    public abstract JsonNode mapAsSchema(String nodeName, JsonNode nodeContent);

}
