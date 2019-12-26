package com.simco.zorko.builder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;

import com.simco.zorko.model.Item;
import com.simco.zorko.model.TurnOn;

@DisplayName("ItemBuilder Test")
@TestInstance(Lifecycle.PER_CLASS)
public class ItemBuilderTest {

    private final String DATA_ITEM_NAME = "torch";
    private final String DATA_ITEM_WRITING = "click button to turn on";
    private final String DATA_ITEM_STATUS = "lit";
    private TurnOn DATA_ITEM_TURNON;

    @BeforeAll
    void beforeAll() {
        DATA_ITEM_TURNON = Mockito.mock(TurnOn.class);
    }

    @Test
    void testEmptyItem() {
        Item item = new ItemBuilder()
                .build();
        assertNotNull( item );
        assertNull( item.getName() );
        assertNull( item.getWriting() );
        assertNull( item.getStatus() );
        assertNull( item.getTurnOn() );
    }

    @Test
    void testItemWithName() {
        Item item = new ItemBuilder()
                .setName(DATA_ITEM_NAME)
                .build();
        assertNotNull( item );
        assertTrue( DATA_ITEM_NAME.equals(item.getName()) );
        assertNull( item.getWriting() );
        assertNull( item.getStatus() );
        assertNull( item.getTurnOn() );
    }

    @Test
    void testItemWithWriting() {
        Item item = new ItemBuilder()
                .setWriting(DATA_ITEM_WRITING)
                .build();
        assertNotNull( item );
        assertNull( item.getName() );
        assertTrue( DATA_ITEM_WRITING.equals(item.getWriting()) );
        assertNull( item.getStatus() );
        assertNull( item.getTurnOn() );
    }

    @Test
    void testItemWithStatus() {
        Item item = new ItemBuilder()
                .setStatus(DATA_ITEM_STATUS)
                .build();
        assertNotNull( item );
        assertNull( item.getName() );
        assertNull( item.getWriting() );
        assertTrue( DATA_ITEM_STATUS.equals(item.getStatus()) );
        assertNull( item.getTurnOn() );
    }

    @Test
    void testItemWithTurnOn() {
        Item item = new ItemBuilder()
                .setTurnOn(DATA_ITEM_TURNON)
                .build();
        assertNotNull( item );
        assertNull( item.getName() );
        assertNull( item.getWriting() );
        assertNull( item.getStatus() );
        assertTrue( DATA_ITEM_TURNON.equals(item.getTurnOn()) );
    }

    @Test
    void testCompleteItem() {
        Item item = new ItemBuilder()
                .setName(DATA_ITEM_NAME)
                .setWriting(DATA_ITEM_WRITING)
                .setStatus(DATA_ITEM_STATUS)
                .setTurnOn(DATA_ITEM_TURNON)
                .build();
        assertNotNull( item );
        assertTrue( DATA_ITEM_NAME.equals(item.getName()) );
        assertTrue( DATA_ITEM_WRITING.equals(item.getWriting()) );
        assertTrue( DATA_ITEM_STATUS.equals(item.getStatus()) );
        assertTrue( DATA_ITEM_TURNON.equals(item.getTurnOn()) );
    }

}
