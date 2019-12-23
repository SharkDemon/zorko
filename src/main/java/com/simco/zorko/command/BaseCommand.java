package com.simco.zorko.command;

import com.simco.zorko.ZorkoGame;

public abstract class BaseCommand implements Command {

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
