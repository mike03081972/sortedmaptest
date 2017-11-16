package org.mma;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mma.dto.*;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class Tests {

    private Timeline timeline;

    private void displayTimeline(Timeline timelineToDisplay) {

        System.out.format("<========================================================================\n");

        System.out.format("id         = %s\n", timelineToDisplay.getId());
        System.out.format("trackCount = %d\n", timelineToDisplay.getTracks().size());

        for (Track track : timelineToDisplay.getTracks()) {

            displayTrack(track);
        }

        System.out.format("========================================================================>\n");
    }

    private void displayTrack(Track trackToDisplay) {

        System.out.format("\tid        = %s\n", trackToDisplay.getId());
        System.out.format("\titemCount = %d\n", trackToDisplay.getItems().size());

        for (Map.Entry<UUID, Item> entry : trackToDisplay.getItems().entrySet()) {

            Item item = entry.getValue();

            displayItem(item);
        }
    }

    private void displayItem(Item item) {

        System.out.format("\t\tid       = %s\n", item.getId());
        System.out.format("\t\tin       = %s\n", item.getIn());
        System.out.format("\t\tduration = %s\n", item.getDuration());

        if (item instanceof ExtendedItem1) {

            ExtendedItem1 extendedItem = (ExtendedItem1)item;

            System.out.format("\t\tname     = %s\n", extendedItem.getName());
        }

        if (item instanceof ExtendedItem2) {

            ExtendedItem2 extendedItem = (ExtendedItem2)item;

            System.out.format("\t\tvalue    = %s\n", extendedItem.getValue());
        }
    }

    @Before
    public void setup() {

        SortedItemMap items = new SortedItemMap();

        UUID uuid1 = TestUUID.getItemId(1);
        ExtendedItem1 item1 = new ExtendedItem1();
        item1.setId(uuid1);
        item1.setIn(0);
        items.put(item1.getId(), item1);

        UUID uuid2 = TestUUID.getItemId(2);
        ExtendedItem1 item2 = new ExtendedItem1();
        item2.setId(uuid2);
        item2.setIn(50);
        items.put(item2.getId(), item2);

        UUID uuid3 = TestUUID.getItemId(3);
        ExtendedItem1 item3 = new ExtendedItem1();
        item3.setId(uuid3);
        item3.setIn(260);
        items.put(item3.getId(), item3);

        UUID uuid4 = TestUUID.getItemId(4);
        ExtendedItem2 item4 = new ExtendedItem2();
        item4.setId(uuid4);
        item4.setIn(100);
        items.put(item4.getId(), item4);

        UUID uuid5 = TestUUID.getItemId(5);
        ExtendedItem2 item5 = new ExtendedItem2();
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

        //displayTimeline(timeline);
    }

    @Test
    public void checkThatItemsAreCorrectlySortededTest() {

        SortedItemMap itemsMap = timeline.getTracks().get(0).getItems();

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
        ExtendedItem1 item = new ExtendedItem1();
        item.setId(uuid);
        item.setIn(10);
        itemsMap.put(item.getId(), item);

        //displayTimeline(timeline);

        Assert.assertEquals( 6 , itemsMap.size() );

        Set<Map.Entry<UUID,Item>> itemsSet = itemsMap.entrySet();
        UUID itemId = ((Map.Entry<UUID,Item>)itemsSet.toArray()[1]).getKey();
        Assert.assertEquals( TestUUID.getItemId(6) , itemId );
    }

    @Test
    public void removeItemTest() {

        SortedItemMap itemsMap = timeline.getTracks().get(0).getItems();

        itemsMap.remove( TestUUID.getItemId(5) );

        //displayTimeline(timeline);

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

        //displayTimeline(timeline);
    }

    @Test
    public void serializeDeserializeTest() {

        String rawText = TimelineSerializer.serialize(timeline);

        //System.out.println(rawText);

        Timeline newTimeline = TimelineDeserializer.deserialize(rawText);

        //displayTimeline(newTimeline);
    }

    @Test
    public void customSerializeTest() {

        String rawText = TimelineSerializer.customSerialize(timeline);

        System.out.println(rawText);
    }

}
