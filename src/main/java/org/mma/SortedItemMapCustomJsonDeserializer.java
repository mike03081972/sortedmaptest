package org.mma;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.HashMap;

public class SortedItemMapCustomJsonDeserializer extends JsonDeserializer<HashMap<String,String>> {

    @Override
    public HashMap<String,String> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        return null;
    }
}
