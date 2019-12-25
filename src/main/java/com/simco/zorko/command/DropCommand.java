package com.simco.zorko.command;

import static com.simco.zorko.command.Commands.CMD_DROP;

import com.simco.zorko.ZorkoGame;

public class DropCommand extends BaseCommand {

    private static final String MSG_DROP_NOT_SPECIFIED = "What is it that you wish to drop?";
    private static final String MSG_DROP_NOT_CARRIED = "You don't have %s in your inventory.";
    private static final String MSG_DROP_ITEM = "%s dropped.";

    public DropCommand(ZorkoGame game, String command) {
        super(game, command);
    }

    @Override
    public void execute() {

        String command = getCommand();

        if (CMD_DROP.equals(command)) {
            message(MSG_DROP_NOT_SPECIFIED);
        }
        else {
            String dropTarget = extractCommand(CMD_DROP, command);

            if (getGame().playerHasItem(dropTarget)) {
                getGame().removeInventoryItem(dropTarget);
                getGame().getCurrentRoom().addItem(dropTarget);
                message(String.format(MSG_DROP_ITEM, dropTarget));
            }
            else {
                // player isn't carrying the item
                message(String.format(MSG_DROP_NOT_CARRIED, dropTarget));
            }
        }
    }

}
