package evs.com.mma;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

class CustomObjectMapper extends ObjectMapper{

    CustomObjectMapper() {

        registerModule( new SortedItemMapModule() );

        enable(SerializationFeature.INDENT_OUTPUT);
    }
}
