package com.simco.zorko.builder;

import com.simco.zorko.model.Item;
import com.simco.zorko.model.TurnOn;

public class ItemBuilder {

    private String name;
    private String writing;
    private String status;
    private TurnOn turnOn;

    public ItemBuilder() {
        super();
    }

    public ItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder setWriting(String writing) {
        this.writing = writing;
        return this;
    }

    public ItemBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public ItemBuilder setTurnOn(TurnOn turnOn) {
        this.turnOn = turnOn;
        return this;
    }

    public Item build() {
        Item item = new Item();
        item.setName( this.name );
        item.setWriting( this.writing );
        item.setStatus( this.status );
        item.setTurnOn( this.turnOn );
        return item;
    }

}
