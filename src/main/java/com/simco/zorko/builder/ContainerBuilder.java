package com.simco.zorko.builder;

import com.simco.zorko.model.Container;

public class ContainerBuilder {

    private String name;
    private String status;

    public ContainerBuilder() {
        super();
    }

    public ContainerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ContainerBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public Container build() {
        Container container = new Container();
        container.setName( this.name );
        container.setStatus( this.status );
        return container;
    }

}
