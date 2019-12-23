package com.simco.zorko.command;

import com.simco.zorko.ZorkoGame;

public class HelpCommand extends BaseCommand {

    public HelpCommand(ZorkoGame game, String command) {
        super(game, command);
    }

    @Override
    public void execute() {

        String command = getCommand();

        message("TODO: implement command HELP");
    }

}
