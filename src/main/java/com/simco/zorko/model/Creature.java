package com.simco.zorko.model;

import java.util.List;

public class Creature implements Triggerable {

    private String name;
    // vulnerability is the name of an object that can defeat the creature
    private String vulnerability;
    private List<Trigger> triggers;

    public Creature() {
        super();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getVulnerability() {
        return vulnerability;
    }
    public void setVulnerability(String vulnerability) {
        this.vulnerability = vulnerability;
    }

    @Override
    public List<Trigger> getTriggers() {
        return triggers;
    }
    public void setTriggers(List<Trigger> triggers) {
        this.triggers = triggers;
    }

}
