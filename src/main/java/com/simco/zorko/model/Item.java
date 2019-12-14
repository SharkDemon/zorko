package com.simco.zorko.model;

public class Item {

    private String name;
    private String writing;
    // optional; examples might be: "lit" versus "inferno", "idle" versus "ticking"...
    private String status;
    // optional; usually this causes the status to be updated from one term to
    // another term
    private TurnOn turnOn;

    public Item() {
        super();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getWriting() {
        return writing;
    }
    public void setWriting(String writing) {
        this.writing = writing;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public TurnOn getTurnOn() {
        return turnOn;
    }
    public void setTurnOn(TurnOn turnOn) {
        this.turnOn = turnOn;
    }

}
