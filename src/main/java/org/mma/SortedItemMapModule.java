package org.mma;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.mma.dto.SortedItemMap;

import java.util.Map;

class SortedItemMapModule extends SimpleModule {

    private static final String NAME = "CustomSortedItemMapModule";

    SortedItemMapModule() {

        super(NAME);

        addSerializer(SortedItemMap.class, new SortedItemMapCustomJsonSerializer());

        //addDeserializer(Map<String, String>.class, new SortedItemMapCustomJsonDeserializer());
    }
}
