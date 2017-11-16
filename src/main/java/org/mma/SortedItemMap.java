package org.mma;

import com.google.common.collect.Ordering;

import java.util.*;

class SortedItemMap extends ValueComparableMap<UUID,Item> {

    SortedItemMap() {

        super(Ordering.from((o1, o2) -> {

            if (o1 != null && o2 != null) {
                Long tc1 = o1.getTc();
                Long tc2 = o2.getTc();

                return tc1.compareTo(tc2);
            }
            if (o1 == null && o2 == null)
                return 0;
            if (o1 == null)
                return -1;

            return 1;
        }), new HashMap<>());
    }

    List<Item> getSortedList() {

        Collection<Item> objectValues = values();

        if ( objectValues instanceof  List )
            return (List<Item>)objectValues;

        return new ArrayList<>(objectValues);
    }
}
