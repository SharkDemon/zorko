package com.simco.zorko.builder;

import com.simco.zorko.ZorkoGameEventHandler;
import com.simco.zorko.model.TurnOn;

public class TurnOnBuilder {

    private ZorkoGameEventHandler listener;
    private String print;
    private String action;

    public TurnOnBuilder() {
        super();
    }

    public TurnOnBuilder setGameEventHandler(ZorkoGameEventHandler listener) {
        this.listener = listener;
        return this;
    }

    public TurnOnBuilder setPrint(String print) {
        this.print = print;
        return this;
    }

    public TurnOnBuilder setAction(String action) {
        this.action = action;
        return this;
    }

    public TurnOn build() {
        TurnOn turnOn = new TurnOn();
        turnOn.setGameEventHandler( listener );
        turnOn.setPrint( this.print );
        turnOn.setAction( this.action );
        return turnOn;
    }

}
