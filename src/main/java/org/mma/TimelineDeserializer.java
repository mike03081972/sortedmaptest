package org.mma;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mma.dto.Timeline;

import java.io.IOException;

public class TimelineDeserializer {

    public static Timeline deserialize(String rawText) {

        ObjectMapper mapper = new ObjectMapper();

        try {

            Timeline timeline = mapper.readValue(rawText, Timeline.class);

            return timeline;
        }
        catch (IOException ex) {

            System.out.println("Error converting JSON to TIMELINE : " + ex);
        }

        return null;
    }

    public static Timeline customDeserialize(String rawText) {

        CustomObjectMapper mapper = new CustomObjectMapper();

        try {

            Timeline timeline = mapper.readValue(rawText, Timeline.class);

            return timeline;
        }
        catch (IOException ex) {

            System.out.println("Error converting JSON top TIMELINE : " + ex);
        }

        return null;
    }
}
