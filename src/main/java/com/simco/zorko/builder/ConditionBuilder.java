package com.simco.zorko.builder;

import com.simco.zorko.model.Condition;

public class ConditionBuilder {

    private Boolean has;
    // TODO: object can be Item or Container, prob need a superclass...could remain a string, as item key
    private String object;
    private String status;
    // TODO: owner can be a container (could it be an item?), or "inventory"
    private String owner;

    public ConditionBuilder setHas(Boolean has) {
        this.has = has;
        return this;
    }

    public ConditionBuilder setObject(String object) {
        this.object = object;
        return this;
    }

    public ConditionBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public ConditionBuilder setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public Condition build() {
        Condition condition = new Condition();
        condition.setHas( this.has );
        condition.setObject( this.object );
        condition.setStatus( this.status );
        condition.setOwner( this.owner );
        return condition;
    }

}
