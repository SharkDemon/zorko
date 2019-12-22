package com.simco.zorko.model;

import java.util.List;

public class Room implements Triggerable {

    private String name;
    private String description;
    // example shows "regular" versus "exit"
    private String type;
    // these strings map to item.name, which is considered the item "key"
    private List<String> items;
    // these strings map to container.name, which is considered the container "key"
    private List<String> containers;
    // these strings map to creature.name, which is considered the creature "key"
    private List<String> creatures;
    // these are basically the exits from this room
    private List<Border> borders;
    // these are triggers pertaining to this room
    private List<Trigger> triggers;

    public Room() {
        super();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public List<String> getItems() {
        return items;
    }
    public void setItems(List<String> items) {
        this.items = items;
    }
    public void addItem(String item) {
        this.items.add(item);
    }
    public void removeItem(String item) {
        this.items.remove(item);
    }

    public List<String> getContainers() {
        return containers;
    }
    public void setContainers(List<String> containers) {
        this.containers = containers;
    }

    public List<String> getCreatures() {
        return creatures;
    }
    public void setCreatures(List<String> creatures) {
        this.creatures = creatures;
    }

    public List<Border> getBorders() {
        return borders;
    }
    public void setBorders(List<Border> borders) {
        this.borders = borders;
    }

    @Override
    public List<Trigger> getTriggers() {
        return triggers;
    }
    public void setTriggers(List<Trigger> triggers) {
        this.triggers = triggers;
    }

}
