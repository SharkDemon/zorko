package com.simco.zorko.command;

import com.simco.zorko.ZorkoGame;

public class QuitCommand extends BaseCommand {

    public QuitCommand(ZorkoGame game, String command) {
        super(game, command);
    }

    @Override
    public void execute() {
        getGame().setRunning(false);
    }

}
