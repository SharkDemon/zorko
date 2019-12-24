package com.simco.zorko.command;

import static com.simco.zorko.model.Commands.CMD_TURN_ON;

import com.simco.zorko.ZorkoGame;
import com.simco.zorko.model.Item;

public class TurnOnCommand extends BaseCommand {

    private static final String MSG_TURN_ON_NOT_SPECIFIED = "What is it that you wish to turn on?";
    private static final String MSG_TURN_ON_NOT_PRESENT = "You don't have %s.";
    private static final String MSG_TURN_ON_INVALID_ITEM = "The %s is unable to be turned on.";

    public TurnOnCommand(ZorkoGame game, String command) {
        super(game, command);
    }

    @Override
    public void execute() {

        String command = getCommand();

        if (CMD_TURN_ON.equals(command)) {
            message(MSG_TURN_ON_NOT_SPECIFIED);
        }
        else {
            String turnOnTarget = extractCommand(CMD_TURN_ON, command);
            // is there an item by that name in the player inventory?
            if (getGame().playerHasItem(turnOnTarget)) {
                Item item = getGame().getItem(turnOnTarget);
                if (null == item.getTurnOn()) {
                    // this item can't be turned on
                    message(String.format(MSG_TURN_ON_INVALID_ITEM, turnOnTarget));
                }
                else {
                    // print the print statements...
                    message(item.getTurnOn().getPrint());
                    // ...and execute the action statements
                    getGame().handleGameCommand(item.getTurnOn().getAction());
                }
            }
            else {
                // the "item to turn on" was not found in inventory
                message(String.format(MSG_TURN_ON_NOT_PRESENT, turnOnTarget));
            }
        }

    }

}
