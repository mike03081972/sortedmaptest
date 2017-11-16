package org.mma.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Timeline {

    private UUID        id     = null;
    private List<Track> tracks = new ArrayList<>();

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public List<Track> getTracks() { return tracks; }
    public void setTracks(List<Track> tracks) { this.tracks = tracks; }
}
