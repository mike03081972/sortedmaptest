package org.mma.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class ExtendedItem1 extends Item {

    private String name = "";

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
