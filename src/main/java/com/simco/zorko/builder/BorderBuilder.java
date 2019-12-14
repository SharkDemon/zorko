package com.simco.zorko.builder;

import com.simco.zorko.model.Border;

public class BorderBuilder {

    private String direction;
    private String name;

    public BorderBuilder() {
        super();
    }

    public BorderBuilder setDirection(String direction) {
        this.direction = direction;
        return this;
    }

    public BorderBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public Border build() {
        Border border = new Border();
        border.setDirection( this.direction );
        border.setName( this.name );
        return border;
    }

}
