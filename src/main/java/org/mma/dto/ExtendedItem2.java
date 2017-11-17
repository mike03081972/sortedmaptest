package org.mma.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class ExtendedItem2 extends Item {

    private long value = 0;

    public long getValue() { return value; }
    public void setValue(long value) { this.value = value; }
}
