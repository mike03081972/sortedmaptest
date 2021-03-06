package org.mma;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.mma.dto.SortedItemMap;

import java.io.IOException;

public class SortedItemMapCustomJsonSerializer extends JsonSerializer<SortedItemMap> {

    @Override
    public void serialize(SortedItemMap value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        gen.writeObject( value.getSortedList() );
    }
}
