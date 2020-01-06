package com.simco.zorko.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;

import com.simco.zorko.ZorkoGame;
import com.simco.zorko.model.Condition;

@DisplayName("ConditionHasBuilder Test")
@TestInstance(Lifecycle.PER_CLASS)
public class ConditionHasBuilderTest {

    private final String DATA_OBJECT_NAME = "knife";
    private ZorkoGame DATA_GAME;

    @BeforeAll
    void beforeAll() {
        DATA_GAME = Mockito.mock(ZorkoGame.class);
    }

    @Test
    void testEmptyConditionHas() {
        Condition condition = new ConditionHasBuilder()
                .build();
        assertNotNull( condition );
        assertNull( condition.getGame() );
        assertNull( condition.getObjectName() );
    }

    @Test
    void testConditionWithGame() {
        Condition condition = new ConditionHasBuilder()
                .setGame(DATA_GAME)
                .build();
        assertNotNull( condition );
        assertNotNull( condition.getGame() );
        assertNull( condition.getObjectName() );
    }

    @Test
    void testConditionWithObjectName() {
        Condition condition = new ConditionHasBuilder()
                .setObjectName(DATA_OBJECT_NAME)
                .build();
        assertNotNull( condition );
        assertNull( condition.getGame() );
        assertNotNull( condition.getObjectName() );
        assertEquals( DATA_OBJECT_NAME, condition.getObjectName() );
    }

    @Test
    void testEvaluate() {

    }

}
