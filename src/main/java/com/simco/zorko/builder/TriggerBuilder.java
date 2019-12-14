package com.simco.zorko.builder;

import java.util.ArrayList;
import java.util.List;

import com.simco.zorko.model.Condition;
import com.simco.zorko.model.Trigger;

public class TriggerBuilder {

    private static final String DEFAULT_TYPE = Trigger.TYPE_SINGLE;

    private String type;
    private String command;
    private List<Condition> conditions;
    private List<String> prints;
    private List<String> actions;

    public TriggerBuilder() {
        super();
        this.type = DEFAULT_TYPE;
        this.conditions = new ArrayList<Condition>(0);
        this.prints = new ArrayList<String>(0);
        this.actions = new ArrayList<String>(0);
    }

    public TriggerBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public TriggerBuilder setCommand(String command) {
        this.command = command;
        return this;
    }

    public TriggerBuilder addCondition(Condition condition) {
        this.conditions.add(condition);
        return this;
    }

    public TriggerBuilder addPrint(String print) {
        this.prints.add(print);
        return this;
    }

    public TriggerBuilder addAction(String action) {
        this.actions.add(action);
        return this;
    }

    public Trigger build() {
        Trigger trigger = new Trigger();
        trigger.setType( this.type );
        trigger.setCommand( this.command );
        trigger.setConditions( this.conditions );
        trigger.setPrints( this.prints );
        trigger.setActions( this.actions );
        return trigger;
    }

}
