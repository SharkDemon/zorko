package com.simco.zorko.model;

import java.util.List;

public class Container implements Triggerable {

    private String name;
    private String status;
    private String accept;
    private List<String> items;
    private List<Trigger> triggers;

    public Container() {
        super();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccept() {
        return accept;
    }
    public void setAccept(String accept) {
        this.accept = accept;
    }

    public List<String> getItems() {
        return items;
    }
    public void setItems(List<String> items) {
        this.items = items;
    }

    @Override
    public List<Trigger> getTriggers() {
        return triggers;
    }
    public void setTriggers(List<Trigger> triggers) {
        this.triggers = triggers;
    }

}
