package org.mma;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.mma.dto.SortedItemMap;

class SortedItemMapModule extends SimpleModule {

    private static final String NAME = "CustomSortedItemMapModule";

    SortedItemMapModule() {

        super(NAME);

        addSerializer(SortedItemMap.class, new SortedItemMapCustomJsonSerializer());

        addDeserializer(SortedItemMap.class, new SortedItemMapCustomJsonDeserializer());
    }
}
