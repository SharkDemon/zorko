package com.simco.zorko.command.game;

import com.simco.zorko.ZorkoGame;
import com.simco.zorko.command.BaseCommand;

public class GameOverCommand extends BaseCommand {

    public GameOverCommand(ZorkoGame game, String command) {
        super(game, command);
    }

    @Override
    public void execute() {

        String command = getCommand();

        message("TODO: implement command GAME OVER");
    }

}
