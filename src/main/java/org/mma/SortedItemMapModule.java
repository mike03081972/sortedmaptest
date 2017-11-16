package org.mma;

import com.fasterxml.jackson.databind.module.SimpleModule;

class SortedItemMapModule extends SimpleModule {

    private static final String NAME = "CustomSortedItemMapModule";

    SortedItemMapModule() {

        super(NAME);

        addSerializer(SortedItemMap.class, new SortedItemMapCustomJsonSerializer());
    }
}
