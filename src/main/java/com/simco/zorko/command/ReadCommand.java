package com.simco.zorko.command;

import static com.simco.zorko.command.Commands.CMD_READ;

import com.simco.zorko.ZorkoGame;
import com.simco.zorko.model.Item;

public class ReadCommand extends BaseCommand {

    // this is the message to print when the user tries to perform a read action
    // but doesn't indicate what item should be read
    private static final String MSG_READ_NOT_SPECIFIED = "What is it that you wish to read?";
    // this is the message to print when the user tries to perform a read action
    // on an item that is not in inventory
    private static final String MSG_READ_NOT_PRESENT = "There is no %s here.";
    // this is the message to print when the user tries to perform a read action
    // on an item that doesn't have writing
    private static final String MSG_READ_NONE = "Nothing written.";

    public ReadCommand(ZorkoGame game, String command) {
        super(game, command);
    }

    @Override
    public void execute() {

        String command = getCommand();

        if (CMD_READ.equals(command)) {
            message(MSG_READ_NOT_SPECIFIED);
        }
        else {
            String readTarget = extractCommand(CMD_READ, command);
            if (getGame().playerHasItem(readTarget)) {
                Item item = getGame().getItem(readTarget);
                if (null == item.getWriting() || 0 == item.getWriting().length()) {
                    message(MSG_READ_NONE);
                }
                else {
                    message(item.getWriting());
                }
            }
            else {
                // the "item to read" was not found in inventory
                message(String.format(MSG_READ_NOT_PRESENT, readTarget));
            }
        }
    }

}
