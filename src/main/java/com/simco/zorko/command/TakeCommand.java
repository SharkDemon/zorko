package com.simco.zorko.command;

import static com.simco.zorko.model.Commands.CMD_TAKE;

import com.simco.zorko.ZorkoGame;
import com.simco.zorko.model.Item;

public class TakeCommand extends BaseCommand {

    // this is the message to print when the user tries to perform a take action
    // but doesn't indicate what item should be taken
    public static final String MSG_TAKE_NOT_SPECIFIED = "What is it that you wish to take?";
    // this is the message to print when the user tries to perform a take action
    // and the item to be taken is not present
    public static final String MSG_TAKE_NOT_PRESENT = "There is no %s here.";
    // this is the message to print when the user successfully takes an item
    public static final String MSG_TAKE_SUCCESS = "Item %s added to inventory.";

    public TakeCommand(ZorkoGame game, String command) {
        super(game, command);
    }

    @Override
    public void execute() {

        String command = getCommand();

        if (CMD_TAKE.equals(command)) {
            message(MSG_TAKE_NOT_SPECIFIED);
        }
        else {
            String takeTarget = extractCommand(CMD_TAKE, command);

            // is there an item by that name in the current room?
            for (String item : getGame().getCurrentRoom().getItems()) {
                if (item.equalsIgnoreCase(takeTarget)) {
                    Item takenItem = getGame().getItem(takeTarget);
                    getGame().addInventoryItem(takenItem);
                    getGame().getCurrentRoom().removeItem(takenItem.getName());
                    message(String.format(MSG_TAKE_SUCCESS, takeTarget));
                    return;
                }
            }
            // TODO: need to check for item in open containers that may be in room
            message(String.format(MSG_TAKE_NOT_PRESENT, takeTarget));
        }

    }

}
