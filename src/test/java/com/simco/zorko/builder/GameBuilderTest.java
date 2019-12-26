package com.simco.zorko.builder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.simco.zorko.ZorkoGame;
import com.simco.zorko.model.Item;
import com.simco.zorko.model.Room;

@DisplayName("GameBuilder Test")
@TestInstance(Lifecycle.PER_CLASS)
public class GameBuilderTest {

    private final String DATA_GAME_NAME = "GameOne";

    private final String DATA_ROOM_NAME = "Kitchen";
    private final String DATA_ROOM_DESCRIPTION = "A simple kitchen.";
    private final String DATA_ROOM_TYPE = "regular";

    private final String DATA_ITEM_NAME = "Knife";
    private final String DATA_ITEM_STATUS = "dry";
    private final String DATA_ITEM_WRITING = "Caution: very very sharp";

    private Room DATA_ROOM;
    private Item DATA_ITEM;

    @BeforeAll
    void beforeAll() {
        DATA_ROOM = new RoomBuilder()
                .setName(DATA_ROOM_NAME)
                .setDescription(DATA_ROOM_DESCRIPTION)
                .setType(DATA_ROOM_TYPE)
                .build();
        DATA_ITEM = new ItemBuilder()
                .setName(DATA_ITEM_NAME)
                .setStatus(DATA_ITEM_STATUS)
                .setWriting(DATA_ITEM_WRITING)
                .build();
    }

    @Test
    void testSetName() {
        ZorkoGame game = new GameBuilder()
                .setName(DATA_GAME_NAME)
                .build();
        assertTrue( DATA_GAME_NAME.equals(game.getName()) );
    }

    @Test
    void testAddRoom() {
        ZorkoGame game = new GameBuilder()
                .addRoom(DATA_ROOM)
                .build();
        Room room = game.getRoom(DATA_ROOM_NAME);
        assertNotNull( room );
        assertTrue( DATA_ROOM_NAME.equals( room.getName() ) );
        assertTrue( DATA_ROOM_DESCRIPTION.equals( room.getDescription() ) );
        assertTrue( DATA_ROOM_TYPE.equals( room.getType() ) );
    }

    @Test
    void testAddItem() {
        ZorkoGame game = new GameBuilder()
                .addItem(DATA_ITEM)
                .build();
        Item item = game.getItem(DATA_ITEM_NAME);
        assertNotNull( item );
        assertTrue( DATA_ITEM_NAME.equals( item.getName() ) );
        assertTrue( DATA_ITEM_STATUS.equals( item.getStatus() ) );
        assertTrue( DATA_ITEM_WRITING.equals( item.getWriting() ) );
    }

}
