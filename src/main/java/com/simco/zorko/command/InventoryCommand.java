package com.simco.zorko.command;

import java.util.stream.Collectors;

import com.simco.zorko.ZorkoGame;

public class InventoryCommand extends BaseCommand {

    public InventoryCommand(ZorkoGame game, String command) {
        super(game, command);
    }

    @Override
    public void execute() {

        message(String.format("Inventory: %s",
                0 == getGame().getInventory().size() ? "nothing" :
                    getGame().getInventory().stream().collect(Collectors.joining(", "))));
    }

}
