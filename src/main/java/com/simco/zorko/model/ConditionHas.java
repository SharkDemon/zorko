package com.simco.zorko.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConditionHas extends Condition {

    private static Logger log = LoggerFactory.getLogger(ConditionHas.class);

    private Boolean has;
    // TODO: owner can be a container (could it be an item?), or "inventory"
    private String owner;

    public ConditionHas() {
        super();
    }

    public Boolean getHas() {
        return has;
    }
    public void setHas(Boolean has) {
        this.has = has;
    }

    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public boolean evaluate() {

        log.info("evaluating has condition: {} has {}", getOwner(), getObjectName());

        // game entities that might "have" something include the player
        // inventory (Items), Rooms, and Containers.  try checking the player
        // inventory first...
        if ("inventory".equals(owner)) {

            Item inventoryItem = getGame().getInventoryItem( getObjectName() );

            // this condition evaluates to true, if (1) the player has the item
            // and is supposed to have the item, or (2) if the player doesn't
            // have the item and isn't supposed to have the item
            return (null != inventoryItem && has) || (null == inventoryItem && !has);
        }

        // ...it wasn't in the inventory, so it should be a Room or Container...
        // try checking the Rooms first...
        Room room = getGame().getRoom(getOwner());
        if (null != room) {

            boolean roomHasItem = room.getItems().contains( getObjectName() );

            // this condition evaluates to true, if (1) the room has the item
            // and is supposed to have the item, or (2) if the room doesn't
            // have the item and isn't supposed to have the item
            return (roomHasItem && has) || (!roomHasItem && !has);
        }

        // ...it's not a Room, let's try checking the Containers...
        Container container = getGame().getContainer(getOwner());
        if (null != container) {

            boolean containerHasItem = container.getItems().contains( getObjectName() );

            // this condition evaluates to true, if (1) the container has the
            // item and is supposed to have the item, or (2) if the container
            // doesn't have the item and isn't supposed to have the item
            return (containerHasItem && has) || (!containerHasItem && !has);
        }

        // we couldn't find the game object that the objectName is referring to,
        // so we'll return false, since the condition can never be satisfied
        return false;
    }

}
