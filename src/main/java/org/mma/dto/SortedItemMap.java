package org.mma.dto;

import com.google.common.collect.Ordering;

import java.util.*;

public class SortedItemMap extends ValueComparableMap<UUID,Item> {

    public SortedItemMap() {

        super(Ordering.from((o1, o2) -> {

            if (o1 != null && o2 != null) {
                Long in1 = o1.getIn();
                Long in2 = o2.getIn();

                return in1.compareTo(in2);
            }
            if (o1 == null && o2 == null)
                return 0;
            if (o1 == null)
                return -1;

            return 1;
        }), new HashMap<>());
    }

    public List<Item> getSortedList() {

        Collection<Item> objectValues = values();

        if ( objectValues instanceof  List )
            return (List<Item>)objectValues;

        return new ArrayList<>(objectValues);
    }
}
