package com.simco.zorko.model;

import java.util.List;

public class Trigger {

    public static final String TYPE_SINGLE = "single";
    public static final String TYPE_PERMANENT = "permanent";

    // example shows "single" versus "permanent"
    private String type;
    // user-input command causing this trigger to fire
    private String command;
    private List<Condition> conditions;
    // display strings to print if the trigger executes
    private List<String> prints;
    // "behind-the-scenes" actions to take if the trigger executes
    private List<String> actions;

    public Trigger() {
        super();
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getCommand() {
        return command;
    }
    public void setCommand(String command) {
        this.command = command;
    }

    public List<Condition> getConditions() {
        return conditions;
    }
    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public List<String> getPrints() {
        return prints;
    }
    public void setPrints(List<String> prints) {
        this.prints = prints;
    }

    public List<String> getActions() {
        return actions;
    }
    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    public boolean isSingle() {
        return TYPE_SINGLE.equals(this.type);
    }

    public boolean evaluate() {
        for (Condition c : this.conditions) {
            if (!c.evaluate()) {
                // this condition failed, therefore trigger eval to false
                return false;
            }
        }
        // all the conditions have been met, therefore trigger eval to true
        return true;
    }

}
