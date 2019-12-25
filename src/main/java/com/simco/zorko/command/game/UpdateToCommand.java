package com.simco.zorko.command.game;

import com.simco.zorko.ZorkoGame;
import com.simco.zorko.command.BaseCommand;

public class UpdateToCommand extends BaseCommand {

    public UpdateToCommand(ZorkoGame game, String command) {
        super(game, command);
    }

    @Override
    public void execute() {

        String command = getCommand();

        String expression = extractCommand("update", command);
        String[] tokens = expression.split(" to ");
        String itemName = tokens[0];
        String newStatus = tokens[1];

        log.debug("updating item=[{}] from oldStatus=[{}] to newStatus=[{}]"
                , itemName
                , getGame().getItem(itemName).getStatus()
                , newStatus);

        // update the item's status
        getGame().getItem(itemName).setStatus(newStatus);

        message("TODO: implement command UPDATE TO");
    }

}
