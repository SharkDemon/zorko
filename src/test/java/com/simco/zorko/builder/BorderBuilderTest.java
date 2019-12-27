package com.simco.zorko.builder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.simco.zorko.model.Border;

@DisplayName("BorderBuilder Test")
@TestInstance(Lifecycle.PER_CLASS)
public class BorderBuilderTest {

    private final String DATA_BORDER_DIRECTION = "north";
    private final String DATA_BORDER_NAME = "Foyer";

    @BeforeAll
    void beforeAll() {
    }

    @Test
    void testEmptyBorder() {
        Border border = new BorderBuilder()
                .build();
        assertNotNull( border );
        assertNull( border.getDirection() );
        assertNull( border.getName() );
    }

    @Test
    void testBorderWithDirection() {
        Border border = new BorderBuilder()
                .setDirection(DATA_BORDER_DIRECTION)
                .build();
        assertNotNull( border );
        assertTrue( DATA_BORDER_DIRECTION.equals(border.getDirection()) );
        assertNull( border.getName() );
    }

    @Test
    void testBorderWithName() {
        Border border = new BorderBuilder()
                .setName(DATA_BORDER_NAME)
                .build();
        assertNotNull( border );
        assertNull( border.getDirection() );
        assertTrue( DATA_BORDER_NAME.equals(border.getName()) );
    }

    @Test
    void testCompleteBorder() {
        Border border = new BorderBuilder()
                .setDirection(DATA_BORDER_DIRECTION)
                .setName(DATA_BORDER_NAME)
                .build();
        assertNotNull( border );
        assertTrue( DATA_BORDER_DIRECTION.equals(border.getDirection()) );
        assertTrue( DATA_BORDER_NAME.equals(border.getName()) );
    }

}
