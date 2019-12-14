package com.simco.zorko.model;

public class Condition {

    // uses cases:
    // object "explosive" with status "ticking"
    // object "torch" with status "inferno"
    // has "false" object "torch" owner "inventory", meaning the player hasn't picked up the torch yet
    // has "true" object "key" owner "lock" (container), does this player with key unlocks the lock(???)

    private Boolean has;
    // TODO: object can be Item or Container, prob need a superclass...could remain a string, as item key
    private String object;
    private String status;
    // TODO: owner can be a container (could it be an item?), or "inventory"
    private String owner;

    public Condition() {
        super();
    }

    public Boolean getHas() {
        return has;
    }
    public void setHas(Boolean has) {
        this.has = has;
    }

    public String getObject() {
        return object;
    }
    public void setObject(String object) {
        this.object = object;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }

}
