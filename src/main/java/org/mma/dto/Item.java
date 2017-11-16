package org.mma.dto;

import java.util.UUID;

public class Item {

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
