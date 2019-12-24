package com.simco.zorko.command;

import com.simco.zorko.ZorkoGame;

public class InvalidCommand extends BaseCommand {

    public InvalidCommand(ZorkoGame game, String command) {
        super(game, command);
    }

    @Override
    public void execute() {
        message("Invalid command.");
    }

}
