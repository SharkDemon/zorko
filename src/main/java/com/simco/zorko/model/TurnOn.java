package com.simco.zorko.model;

import com.simco.zorko.ZorkoGameEventHandler;

public class TurnOn {

    private ZorkoGameEventHandler listener;

    // string to be printed when item is turned on
    private String print;
    // a behind-the-scenes action to perform when item is turned on
    private String action;

    public TurnOn() {
        super();
    }

    public TurnOn(String print, String action) {
        super();
        this.setPrint(print);
        this.setAction(action);
    }

    public void setGameEventHandler(ZorkoGameEventHandler listener) {
        this.listener = listener;
    }

    public String getPrint() {
        return print;
    }
    public void setPrint(String print) {
        this.print = print;
    }

    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }

    public void execute() {
        System.out.println(getPrint());
        listener.handleCommand(action);
    }

}
