package com.simco.zorko.builder;

import java.util.ArrayList;
import java.util.List;

import com.simco.zorko.model.Border;
import com.simco.zorko.model.Room;
import com.simco.zorko.model.Trigger;

public class RoomBuilder {

    private static final String DEFAULT_TYPE = "regular";

    private String name;
    private String description;
    private String type;
    private List<String> items;
    private List<String> containers;
    private List<String> creatures;
    private List<Border> borders;
    private List<Trigger> triggers;

    public RoomBuilder() {
        super();
        this.type = DEFAULT_TYPE;
        this.items = new ArrayList<String>(0);
        this.containers = new ArrayList<String>(0);
        this.creatures = new ArrayList<String>(0);
        this.borders = new ArrayList<Border>(0);
        this.triggers = new ArrayList<Trigger>(0);
    }

    public RoomBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public RoomBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public RoomBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public RoomBuilder addItem(String itemName) {
        this.items.add(itemName);
        return this;
    }

    public RoomBuilder addContainer(String containerName) {
        this.containers.add(containerName);
        return this;
    }

    public RoomBuilder addCreature(String creatureName) {
        this.creatures.add(creatureName);
        return this;
    }

    public RoomBuilder addBorder(Border border) {
        this.borders.add(border);
        return this;
    }

    public RoomBuilder addTrigger(Trigger trigger) {
        this.triggers.add(trigger);
        return this;
    }

    public Room build() {
        Room room = new Room();
        room.setName( this.name );
        room.setDescription( this.description );
        room.setType( this.type );
        room.setItems( this.items );
        room.setContainers( this.containers );
        room.setCreatures( this.creatures );
        room.setBorders( this.borders );
        room.setTriggers( this.triggers );
        return room;
    }

}
