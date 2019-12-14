package com.simco.zorko.model;

public class Border {

    private String direction;
    // border.name maps to room.name
    private String name;

    public Border() {
        super();
    }

    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
