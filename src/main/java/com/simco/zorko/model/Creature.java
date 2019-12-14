package com.simco.zorko.model;

import java.util.List;

public class Creature implements Triggerable {

    private String name;
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

    @Override
    public List<Trigger> getTriggers() {
        return triggers;
    }
    public void setTriggers(List<Trigger> triggers) {
        this.triggers = triggers;
    }

}
