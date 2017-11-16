package org.mma;

import java.util.UUID;

public class TestUUID {

    private TestUUID() {}

    public static final int INEXISTING = -1;


    public static UUID getTimelineId(int val) {
        return getId("00000000-0000-0000-0000-000000000000", val);
    }

    public static UUID getTrackId(int val) {
        return getId("00000000-0000-0000-0001-000000000000", val);
    }

    public static UUID getItemId(int val) {
        return getId("00000000-0000-0000-0003-000000000000", val);
    }


    private static UUID getId(String pattern, int val) {

        String valString = String.format("%X", val);

        String newPattern = pattern;

        if (val == INEXISTING) {

            newPattern = newPattern.replaceAll("0", "9");

            return UUID.fromString(newPattern);
        }

        return UUID.fromString( newPattern.substring( 0 , 36 - valString.length() ) + valString );
    }

}
