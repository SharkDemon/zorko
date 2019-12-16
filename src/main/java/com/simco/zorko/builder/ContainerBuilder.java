package com.simco.zorko.builder;

import java.util.ArrayList;
import java.util.List;

import com.simco.zorko.model.Container;
import com.simco.zorko.model.Trigger;

public class ContainerBuilder {

    private String name;
    private String status;
    private String accept;
    private List<String> items;
    private List<Trigger> triggers;

    public ContainerBuilder() {
        super();
        this.items = new ArrayList<String>(0);
        this.triggers = new ArrayList<Trigger>(0);
    }

    public ContainerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ContainerBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public ContainerBuilder setAccept(String accept) {
        this.accept = accept;
        return this;
    }

    public ContainerBuilder addItem(String itemName) {
        this.items.add(itemName);
        return this;
    }

    public ContainerBuilder addTrigger(Trigger trigger) {
        this.triggers.add(trigger);
        return this;
    }

    public Container build() {
        Container container = new Container();
        container.setName( this.name );
        container.setStatus( this.status );
        container.setAccept( this.accept );
        container.setItems( this.items );
        container.setTriggers( this.triggers );
        return container;
    }

}
