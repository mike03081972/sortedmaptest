package org.mma.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ExtendedItem1.class),
        @JsonSubTypes.Type(value = ExtendedItem2.class)}
)public abstract class Item {

    private UUID id;
    private long in;
    private long duration;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public long getIn() { return in; }
    public void setIn(long in) { this.in = in; }

    public long getDuration() { return duration; }
    public void setDuration(long duration) { this.duration = duration; }
}
