package com.simco.zorko.builder;

import java.util.HashMap;
import java.util.Map;

import com.simco.zorko.ZorkoGame;
import com.simco.zorko.model.Container;
import com.simco.zorko.model.Creature;
import com.simco.zorko.model.Item;
import com.simco.zorko.model.Room;

public class GameBuilder {

    private static final String STARTING_ROOM_NAME = "Entrance";

    private String name;
    private Map<String, Room> rooms;
    private Map<String, Item> items;
    private Map<String, Container> containers;
    private Map<String, Creature> creatures;

    public GameBuilder() {
        super();
        this.rooms = new HashMap<String, Room>(0);
        this.items = new HashMap<String, Item>(0);
        this.containers = new HashMap<String, Container>(0);
        this.creatures = new HashMap<String, Creature>(0);
    }

    public GameBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public GameBuilder addRoom(Room room) {
        this.rooms.put(room.getName(), room);
        return this;
    }

    public GameBuilder addItem(Item item) {
        this.items.put(item.getName(), item);
        return this;
    }

    public GameBuilder addContainer(Container container) {
        this.containers.put(container.getName(), container);
        return this;
    }

    public GameBuilder addCreature(Creature creature) {
        this.creatures.put(creature.getName(), creature);
        return this;
    }

    public ZorkoGame build() {
        ZorkoGame game = new ZorkoGame();
        game.setName( this.name );
        game.setRooms( this.rooms );
        game.setItems( this.items );
        game.setContainers( this.containers );
        game.setCreatures( this.creatures );
        game.setCurrentRoomName(STARTING_ROOM_NAME);

        // set the game property on all of the conditions in all of the triggers
        this.rooms.entrySet().stream().forEach(e -> {
            e.getValue().getTriggers().stream().forEach(t -> {
                t.getConditions().stream().forEach(c -> {
                    c.setGame(game);
                });
            });
        });
        this.containers.entrySet().stream().forEach(e -> {
            e.getValue().getTriggers().stream().forEach(t -> {
                t.getConditions().stream().forEach(c -> {
                    c.setGame(game);
                });
            });
        });
        this.creatures.entrySet().stream().forEach(e -> {
            e.getValue().getTriggers().stream().forEach(t -> {
                t.getConditions().stream().forEach(c -> {
                    c.setGame(game);
                });
            });
        });

        return game;
    }

}
