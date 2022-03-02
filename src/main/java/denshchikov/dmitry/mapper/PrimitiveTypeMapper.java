package denshchikov.dmitry.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedList;

public abstract class PrimitiveTypeMapper extends Mapper {

    public PrimitiveTypeMapper(LinkedList<Mapper> nextMappers, ObjectMapper objectMapper) {
        super(nextMappers, objectMapper);

        if (!nextMappers.isEmpty()) {
            throw new IllegalStateException("Primitive mapper could not have nested mappers");
        }
    }

}
