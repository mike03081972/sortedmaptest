package org.mma;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.mma.dto.Timeline;

import java.io.IOException;

public class TimelineSerializer {

    public static String serialize(Timeline timeline) {

        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String rawText = "";

        try {

            rawText = mapper.writeValueAsString(timeline);

            //Timeline convertedFromJson = mapper.readValue(jsonText, Timeline.class);
        }
        catch (IOException ex) {

            System.out.println("Error converting MAP to JSON : " + ex);
        }

        return rawText;
    }

    public static String customSerialize(Timeline timeline) {

        CustomObjectMapper mapper = new CustomObjectMapper();

        String rawText = "";

        try {

            rawText = mapper.writeValueAsString(timeline);

            //Timeline convertedFromJson = mapper.readValue(rawText, Timeline.class);
        }
        catch (IOException ex) {

            System.out.println("Error converting MAP to JSON : " + ex);
        }

        return rawText;
    }
}
