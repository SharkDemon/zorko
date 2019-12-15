package com.simco.zorko.builder;

import com.simco.zorko.ZorkoGame;
import com.simco.zorko.model.ConditionStatus;

public class ConditionStatusBuilder {

    private ZorkoGame game;
    // TODO: object can be Item or Container, prob need a superclass...could remain a string, as item key
    private String object;
    private String status;

    public ConditionStatusBuilder() {
        super();
    }

    public ConditionStatusBuilder setGame(ZorkoGame game) {
        this.game = game;
        return this;
    }

    public ConditionStatusBuilder setObjectName(String object) {
        this.object = object;
        return this;
    }

    public ConditionStatusBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public ConditionStatus build() {
        ConditionStatus condition = new ConditionStatus();
        condition.setGame( this.game );
        condition.setObjectName( this.object );
        condition.setStatus( this.status );
        return condition;
    }

}
