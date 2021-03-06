package com.simco.zorko.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simco.zorko.ZorkoGame;

public abstract class BaseCommand implements Command {

    protected static Logger log = LoggerFactory.getLogger(BaseCommand.class);

    private ZorkoGame game;
    private String command;

    public BaseCommand(ZorkoGame game, String command) {
        super();
        this.game = game;
        this.command = command;
    }

    public ZorkoGame getGame() {
        return this.game;
    }

    public String getCommand() {
        return this.command;
    }

    public String extractCommand(String command, String s) {
        return s.substring( command.length() ).trim();
    }

    public void message(String message) {
        // TODO: prob call a message() on Game class
        System.out.println(message);
    }

}
