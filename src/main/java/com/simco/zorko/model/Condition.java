package com.simco.zorko.model;

import com.simco.zorko.ZorkoGame;

public abstract class Condition {

    // possible use cases:
    // object "explosive" with status "ticking"
    // object "torch" with status "inferno"
    // has "false" object "torch" owner "inventory", meaning the player hasn't picked up the torch yet
    // has "true" object "key" owner "lock" (container), does this player with key unlocks the lock(???)

    // conditions need reference to Game object, because they operate against
    // other Game objects (i.e., items)
    private ZorkoGame game;

    // TODO: object can be Item or Container, prob need a superclass...could remain a string, as item key
    private String objectName;

    public Condition() {
        super();
    }

    public ZorkoGame getGame() {
        return game;
    }
    public void setGame(ZorkoGame game) {
        this.game = game;
    }

    public String getObjectName() {
        return objectName;
    }
    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public abstract boolean evaluate();

}
