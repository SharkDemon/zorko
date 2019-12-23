package com.simco.zorko.command;

import com.simco.zorko.ZorkoGame;

public class AttackCommand extends BaseCommand {

    public AttackCommand(ZorkoGame game, String command) {
        super(game, command);
    }

    @Override
    public void execute() {

        String command = getCommand();

        message("TODO: implement command ATTACK");
    }

}
