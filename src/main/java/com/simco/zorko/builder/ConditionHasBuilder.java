package com.simco.zorko.builder;

import com.simco.zorko.ZorkoGame;
import com.simco.zorko.model.Condition;
import com.simco.zorko.model.ConditionHas;

public class ConditionHasBuilder {

    private ZorkoGame game;
    private Boolean has;
    // TODO: object can be Item or Container, prob need a superclass...could remain a string, as item key
    private String object;
    // TODO: owner can be a container (could it be an item?), or "inventory"
    private String owner;

    public ConditionHasBuilder() {
        super();
    }

    public ConditionHasBuilder setGame(ZorkoGame game) {
        this.game = game;
        return this;
    }

    public ConditionHasBuilder setHas(Boolean has) {
        this.has = has;
        return this;
    }

    public ConditionHasBuilder setObjectName(String object) {
        this.object = object;
        return this;
    }

    public ConditionHasBuilder setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public Condition build() {
        ConditionHas condition = new ConditionHas();
        condition.setGame( this.game );
        condition.setHas( this.has );
        condition.setObjectName( this.object );
        condition.setOwner( this.owner );
        return condition;
    }

}
