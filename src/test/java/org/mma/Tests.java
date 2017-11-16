package org.mma;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mma.dto.Item;
import org.mma.dto.SortedItemMap;
import org.mma.dto.Timeline;
import org.mma.dto.Track;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class Tests {

    private Timeline timeline;

    private static void displayMap(SortedItemMap map) {

        for (Map.Entry<UUID, Item> entry : map.entrySet()) {

            Item item = entry.getValue();

            System.out.format("UUID (%s) - IN (%04d) - DUR (%s)\n", item.getId().toString(), item.getIn(), item.getDuration());
        }
    }

    private static void displayItem(Item item) {

        System.out.format("UUID (%s) - IN (%04d) - DUR (%s)\n", item.getId().toString(), item.getIn(), item.getDuration());
    }

    @Before
    public void setup() {

        SortedItemMap items = new SortedItemMap();

        UUID uuid1 = TestUUID.getItemId(1);
        Item item1 = new Item();
        item1.setId(uuid1);
        item1.setIn(0);
        items.put(item1.getId(), item1);

        UUID uuid2 = TestUUID.getItemId(2);
        Item item2 = new Item();
        item2.setId(uuid2);
        item2.setIn(50);
        items.put(item2.getId(), item2);

        UUID uuid3 = TestUUID.getItemId(3);
        Item item3 = new Item();
        item3.setId(uuid3);
        item3.setIn(260);
        items.put(item3.getId(), item3);

        UUID uuid4 = TestUUID.getItemId(4);
        Item item4 = new Item();
        item4.setId(uuid4);
        item4.setIn(100);
        items.put(item4.getId(), item4);

        UUID uuid5 = TestUUID.getItemId(5);
        Item item5 = new Item();
        item5.setId(uuid5);
        item5.setIn(25);
        items.put(item5.getId(), item5);

        // TRACK

        Track track = new Track();

        track.setId( TestUUID.getTrackId(1) );

        track.setItems(items);

        // TIMELINE

        timeline = new Timeline();

        timeline.setId( TestUUID.getTimelineId(1) );

        timeline.getTracks().add(track);
    }

    @Test
    public void checkThatItemsAreCorrectlySortededTest() {

        SortedItemMap itemsMap = timeline.getTracks().get(0).getItems();

        //displayMap(itemsMap);

        Set<Map.Entry<UUID,Item>> itemsSet = itemsMap.entrySet();

        Assert.assertEquals( 5 , itemsMap.size() );

        UUID itemId = ((Map.Entry<UUID,Item>)itemsSet.toArray()[0]).getKey();
        Assert.assertEquals( TestUUID.getItemId(1) , itemId );

        itemId = ((Map.Entry<UUID,Item>)itemsSet.toArray()[1]).getKey();
        Assert.assertEquals( TestUUID.getItemId(5) , itemId );

        itemId = ((Map.Entry<UUID,Item>)itemsSet.toArray()[2]).getKey();
        Assert.assertEquals( TestUUID.getItemId(2) , itemId );

        itemId = ((Map.Entry<UUID,Item>)itemsSet.toArray()[3]).getKey();
        Assert.assertEquals( TestUUID.getItemId(4) , itemId );

        itemId = ((Map.Entry<UUID,Item>)itemsSet.toArray()[4]).getKey();
        Assert.assertEquals( TestUUID.getItemId(3) , itemId );
    }

    @Test
    public void addItemTest() {

        SortedItemMap itemsMap = timeline.getTracks().get(0).getItems();

        UUID uuid = TestUUID.getItemId(6);
        Item item = new Item();
        item.setId(uuid);
        item.setIn(10);
        itemsMap.put(item.getId(), item);

        //displayMap(itemsMap);

        Assert.assertEquals( 6 , itemsMap.size() );

        Set<Map.Entry<UUID,Item>> itemsSet = itemsMap.entrySet();
        UUID itemId = ((Map.Entry<UUID,Item>)itemsSet.toArray()[1]).getKey();
        Assert.assertEquals( TestUUID.getItemId(6) , itemId );
    }

    @Test
    public void removeItemTest() {

        SortedItemMap itemsMap = timeline.getTracks().get(0).getItems();

        itemsMap.remove( TestUUID.getItemId(5) );

        //displayMap(itemsMap);

        Assert.assertEquals( 4 , itemsMap.size() );
    }

    @Test
    public void incrementAllTcTest() {

        SortedItemMap itemsMap = timeline.getTracks().get(0).getItems();

        long incrementBy = 25;

        for (Map.Entry<UUID, Item> entry : itemsMap.entrySet()) {

            Item item = entry.getValue();

            item.setIn( item.getIn() + incrementBy );
        }

        //displayMap(itemsMap);
    }

    @Test
    public void serializeTest() {

        String rawText = TimelineSerializer.serialize(timeline);

        System.out.println(rawText);
    }

    @Test
    public void customSerializeTest() {

        String rawText = TimelineSerializer.customSerialize(timeline);

        System.out.println(rawText);
    }
}
