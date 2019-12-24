package com.simco.zorko.command.game;

import com.simco.zorko.ZorkoGame;
import com.simco.zorko.command.BaseCommand;

public class AddToCommand extends BaseCommand {

    public AddToCommand(ZorkoGame game, String command) {
        super(game, command);
    }

    @Override
    public void execute() {

        String command = getCommand();

        message("TODO: implement command ADD TO");
    }

}
