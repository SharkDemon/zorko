package com.simco.zorko.command;

import java.util.Arrays;
import java.util.List;

import com.simco.zorko.ZorkoGame;
import com.simco.zorko.command.game.AddToCommand;
import com.simco.zorko.command.game.DeleteCommand;
import com.simco.zorko.command.game.GameOverCommand;
import com.simco.zorko.command.game.UpdateToCommand;

public class CommandFactory {

    // management commands
    private static final List<String> COMMANDS_QUIT = Arrays.asList("quit", "exit");
    private static final List<String> COMMANDS_HELP = Arrays.asList("help", "h");
    private static final List<String> COMMANDS_INVENTORY = Arrays.asList("inventory", "inv", "i");

    // directional commands
    private static final List<String> COMMANDS_MOVE = Arrays.asList("north", "east", "south", "west", "n", "e", "s", "w", "up", "down", "u", "d");

    // action commands
    private static final List<String> COMMANDS_ATTACK = Arrays.asList("attack", "fight");
    private static final List<String> COMMANDS_DROP = Arrays.asList("drop");
    private static final List<String> COMMANDS_LOOK = Arrays.asList("look");
    private static final List<String> COMMANDS_PUT = Arrays.asList("put");
    private static final List<String> COMMANDS_READ = Arrays.asList("read");
    private static final List<String> COMMANDS_TAKE = Arrays.asList("take", "get", "grab");
    private static final List<String> COMMANDS_TURN_ON = Arrays.asList("turn on", "turnon", "enable");

    private ZorkoGame game;

    public CommandFactory(ZorkoGame game) {
        super();
        this.game = game;
    }

    // determines if the user-entered command matches any of the synonyms in the
    // list of related game commands
    public boolean match(String command, List<String> gameCommands, boolean exactMatch) {
        for (String gc : gameCommands) {
            if (exactMatch) {
                if (command.equals(gc)) {
                    return true;
                }
            }
            else {
                if (command.startsWith(gc)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean match(String command, List<String> gameCommands) {
        return match(command, gameCommands, false);
    }

    public Command buildCommand(String command) {

        Command c = null;

        // handle two-word action commands first (e.g., we don't want to match
        // "turn" to a "turn on" command)
        if (match(command, COMMANDS_TURN_ON)) {
            c = new TurnOnCommand(game, command);
        }

        // handle one-word management commands
        else if (match(command, COMMANDS_QUIT)) {
            c = new QuitCommand(game, command);
        }
        else if (match(command, COMMANDS_HELP)) {
            c = new HelpCommand(game, command);
        }
        else if (match(command, COMMANDS_INVENTORY)) {
            c = new InventoryCommand(game, command);
        }

        // handle one-word directional commands
        else if (match(command, COMMANDS_MOVE, true)) {
            c = new MoveCommand(game, command);
        }

        // handle one-word action commands
        else if (match(command, COMMANDS_ATTACK)) {
            c = new AttackCommand(game, command);
        }
        else if (match(command, COMMANDS_DROP)) {
            c = new DropCommand(game, command);
        }
        else if (match(command, COMMANDS_LOOK)) {
            c = new LookCommand(game, command);
        }
        else if (match(command, COMMANDS_PUT)) {
            c = new PutCommand(game, command);
        }
        else if (match(command, COMMANDS_READ)) {
            c = new ReadCommand(game, command);
        }
        else if (match(command, COMMANDS_TAKE)) {
            c = new TakeCommand(game, command);
        }

        // handle the scenario where we can't create a valid command based on
        // that user input
        else {
            c = new InvalidCommand(game, command);
        }

        return c;
    }

    public Command buildGameCommand(String command) {
        Command c = null;
        // TODO: startsWith add needs to be more sophisticated
        if (command.startsWith("add ")) {
            c = new AddToCommand(game, command);
        }
        else if (command.startsWith("delete ")) {
            c = new DeleteCommand(game, command);
        }
        else if (command.startsWith("update ") && command.contains(" to ")) {
            c = new UpdateToCommand(game, command);
        }
        else if (command.startsWith("game over")) {
            c = new GameOverCommand(game, command);
        }
        return c;
    }

}
