package org.mma.dto;

import java.util.UUID;

public class Track {

    private UUID          id    = null;
    private SortedItemMap items = new SortedItemMap();

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public SortedItemMap getItems() { return items; }
    public void setItems(SortedItemMap items) { this.items = items; }
}
