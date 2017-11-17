package org.mma;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.mma.dto.ExtendedItem1;
import org.mma.dto.ExtendedItem2;
import org.mma.dto.Item;
import org.mma.dto.SortedItemMap;

import java.io.IOException;
import java.util.UUID;

public class SortedItemMapCustomJsonDeserializer extends JsonDeserializer<SortedItemMap> {

    @Override
    public SortedItemMap deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {

        SortedItemMap sortedItemMap = new SortedItemMap();
        ObjectCodec   codec         = parser.getCodec();
        TreeNode      treeNode      = codec.readTree(parser);

        if ( treeNode.isArray() ) {

            for (JsonNode node : (ArrayNode)treeNode ) {

                JsonNode id    = node.get("id");
                JsonNode name  = node.get("name");
                JsonNode value = node.get("value");

                if (id != null) {

                    Item item = name != null ? new ExtendedItem1() : new ExtendedItem2();

                    item.setId( UUID.fromString( id.asText() ) );
                    item.setIn( node.get("in").asLong() );
                    item.setDuration( node.get("duration").asLong() );

                    if (name != null)
                        ((ExtendedItem1)item).setName( name.asText() );
                    else
                        ((ExtendedItem2)item).setValue( value.asLong() );

                    sortedItemMap.put( item.getId() , item );
                }
            }
        }

        return sortedItemMap;
    }
}
