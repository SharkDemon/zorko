package com.simco.zorko.command;

import com.simco.zorko.ZorkoGame;
import com.simco.zorko.model.Border;
import com.simco.zorko.model.Room;

public class MoveCommand extends BaseCommand {

    // this is the message to print when the user tries to perform an invalid
    // move from a room
    private static final String MSG_MOVE_INVALID = "Can't go that way.";

    public MoveCommand(ZorkoGame game, String command) {
        super(game, command);
    }

    @Override
    public void execute() {

        String command = getCommand();

        Room currentRoom = getGame().getCurrentRoom();
        for (Border b : currentRoom.getBorders()) {
            if (b.getDirection().equals(command)) {
                getGame().setCurrentRoomName( b.getName() );
                message(String.format("%s\n", getGame().getCurrentRoom().getDescription()));
                return;
            }
        }
        // at this point, a Border with the desired direction was not found
        message(MSG_MOVE_INVALID);
    }

}
