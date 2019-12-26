package com.simco.zorko.builder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.simco.zorko.model.TurnOn;

@DisplayName("TurnOnBuilder Test")
@TestInstance(Lifecycle.PER_CLASS)
public class TurnOnBuilderTest {

    private final String DATA_TURN_ON_PRINT = "you turn on the item";
    private final String DATA_TURN_ON_ACTION = "update status to used";

    @BeforeAll
    void beforeAll() {
    }

    @Test
    void testEmptyTurnOn() {
        TurnOn turnOn = new TurnOnBuilder()
                .build();
        assertNotNull( turnOn );
        assertNull( turnOn.getPrint() );
        assertNull( turnOn.getAction() );
    }

    @Test
    void testTurnOnWithPrint() {
        TurnOn turnOn = new TurnOnBuilder()
                .setPrint(DATA_TURN_ON_PRINT)
                .build();
        assertNotNull( turnOn );
        assertTrue( DATA_TURN_ON_PRINT.equals(turnOn.getPrint()) );
        assertNull( turnOn.getAction() );
    }

    @Test
    void testTurnOnWithAction() {
        TurnOn turnOn = new TurnOnBuilder()
                .setAction(DATA_TURN_ON_ACTION)
                .build();
        assertNotNull( turnOn );
        assertNull( turnOn.getPrint() );
        assertTrue( DATA_TURN_ON_ACTION.equals(turnOn.getAction()) );
    }

    @Test
    void testCompleteTurnOn() {
        TurnOn turnOn = new TurnOnBuilder()
                .setPrint(DATA_TURN_ON_PRINT)
                .setAction(DATA_TURN_ON_ACTION)
                .build();
        assertNotNull( turnOn );
        assertTrue( DATA_TURN_ON_PRINT.equals(turnOn.getPrint()) );
        assertTrue( DATA_TURN_ON_ACTION.equals(turnOn.getAction()) );
    }

}
