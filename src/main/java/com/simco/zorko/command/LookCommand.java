package com.simco.zorko.command;

import com.simco.zorko.ZorkoGame;

public class LookCommand extends BaseCommand {

    public LookCommand(ZorkoGame game, String command) {
        super(game, command);
    }

    @Override
    public void execute() {
        message(String.format("%s\n", getGame().getCurrentRoom().getDescription()));
    }

}
