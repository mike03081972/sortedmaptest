package org.mma;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        SortedItemMap items = new SortedItemMap();

        UUID uuid1 = UUID.fromString("00000000-0000-0000-0000-000000000001");
        Item item1 = new Item();
        item1.setId(uuid1);
        item1.setTc(0);
        item1.setName("Name 1");
        items.put(item1.getId(), item1);

        UUID uuid2 = UUID.fromString("00000000-0000-0000-0000-000000000002");
        Item item2 = new Item();
        item2.setId(uuid2);
        item2.setTc(50);
        item2.setName("Name 2");
        items.put(item2.getId(), item2);

        UUID uuid3 = UUID.fromString("00000000-0000-0000-0000-000000000003");
        Item item3 = new Item();
        item3.setId(uuid3);
        item3.setTc(260);
        item3.setName("Name 3");
        items.put(item3.getId(), item3);

        UUID uuid4 = UUID.fromString("00000000-0000-0000-0000-000000000004");
        Item item4 = new Item();
        item4.setId(uuid4);
        item4.setTc(100);
        item4.setName("Name 4");
        items.put(item4.getId(), item4);

        UUID uuid5 = UUID.fromString("00000000-0000-0000-0000-000000000005");
        Item item5 = new Item();
        item5.setId(uuid5);
        item5.setTc(25);
        item5.setName("Name 5");
        items.put(item5.getId(), item5);

        System.out.println("USING DEFAULT SERIALIZER =>");
        makeJsonString(items);
        System.out.println("USING CUSTOM SERIALIZER =>");
        makeJsonStringUsingCustomSerializer(items);

        displayMap(items);

        System.out.println("");

        Item item = items.get(uuid4);

        displayItem(item);

        item = items.lowerEntry(uuid2).getValue();

        displayItem(item);

        item = items.higherEntry(uuid2).getValue();

        displayItem(item);

        UUID key = items.lowerKey(uuid1);

        System.out.format("UUID (%s)%n", key);

        key = items.higherKey(uuid3);

        System.out.format("UUID (%s)%n", key);

        items.remove(uuid4);

        System.out.println("");

        displayMap(items);

        System.out.println("");

        incrementAllTc(items, 25);

        displayMap(items);
    }

    private static void displayMap(SortedItemMap map) {

        for (Map.Entry<UUID, Item> entry : map.entrySet()) {

            Item item = entry.getValue();

            System.out.format("UUID (%s) - TC (%04d) - NAME (%s)\n", item.getId().toString(), item.getTc(), item.getName());
        }
    }

    private static void displayItem(Item item) {

        System.out.format("UUID (%s) - TC (%04d) - NAME (%s)\n", item.getId().toString(), item.getTc(), item.getName());
    }

    private static void incrementAllTc(SortedItemMap map, long incrementBy) {

        for (Map.Entry<UUID, Item> entry : map.entrySet()) {

            Item item = entry.getValue();

            item.setTc( item.getTc() + incrementBy );
        }
    }

    private static void makeJsonString(SortedItemMap map) {

        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String jsonText;

        try {

            jsonText = mapper.writeValueAsString(map);

            System.out.println(jsonText);

            //SortedItemMap convertedFromJson = mapper.readValue(jsonText, SortedItemMap.class);

            //displayMap(convertedFromJson);
        }
        catch (IOException ex) {

            System.out.println("Error converting MAP to JSON : " + ex);
        }
    }

    private static void makeJsonStringUsingCustomSerializer(SortedItemMap map) {

        CustomObjectMapper mapper = new CustomObjectMapper();

        String jsonText;

        try {

            jsonText = mapper.writeValueAsString(map);

            System.out.println(jsonText);

            //SortedItemMap convertedFromJson = mapper.readValue(jsonText, SortedItemMap.class);
        }
        catch (IOException ex) {

            System.out.println("Error converting MAP to JSON : " + ex);
        }
    }

}
