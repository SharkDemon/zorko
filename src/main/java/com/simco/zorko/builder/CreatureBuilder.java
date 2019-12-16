package com.simco.zorko.builder;

import java.util.ArrayList;
import java.util.List;

import com.simco.zorko.model.Creature;
import com.simco.zorko.model.Trigger;

public class CreatureBuilder {

    private String name;
    private String vulnerability;
    // TODO: attack property
    private List<Trigger> triggers;

    public CreatureBuilder() {
        super();
        this.triggers = new ArrayList<Trigger>(0);
    }

    public CreatureBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CreatureBuilder setVulnerability(String vulnerability) {
        this.vulnerability = vulnerability;
        return this;
    }

    public CreatureBuilder addTrigger(Trigger trigger) {
        this.triggers.add(trigger);
        return this;
    }

    public Creature build() {
        Creature creature = new Creature();
        creature.setName( this.name );
        creature.setVulnerability( this.vulnerability );
        creature.setTriggers( this.triggers );
        return creature;
    }

}
