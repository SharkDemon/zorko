package com.simco.zorko.command;

import static com.simco.zorko.model.Commands.CMD_ATTACK;
import static com.simco.zorko.model.Commands.CMD_DOWN;
import static com.simco.zorko.model.Commands.CMD_DROP;
import static com.simco.zorko.model.Commands.CMD_EAST;
import static com.simco.zorko.model.Commands.CMD_EXIT;
import static com.simco.zorko.model.Commands.CMD_HELP;
import static com.simco.zorko.model.Commands.CMD_INVENTORY;
import static com.simco.zorko.model.Commands.CMD_LOOK;
import static com.simco.zorko.model.Commands.CMD_NORTH;
import static com.simco.zorko.model.Commands.CMD_PUT;
import static com.simco.zorko.model.Commands.CMD_QUIT;
import static com.simco.zorko.model.Commands.CMD_READ;
import static com.simco.zorko.model.Commands.CMD_SOUTH;
import static com.simco.zorko.model.Commands.CMD_TAKE;
import static com.simco.zorko.model.Commands.CMD_TURN_ON;
import static com.simco.zorko.model.Commands.CMD_UP;
import static com.simco.zorko.model.Commands.CMD_WEST;

import com.simco.zorko.ZorkoGame;
import com.simco.zorko.command.game.AddToCommand;
import com.simco.zorko.command.game.DeleteCommand;
import com.simco.zorko.command.game.GameOverCommand;
import com.simco.zorko.command.game.UpdateToCommand;

public class CommandFactory {

    private ZorkoGame game;

    public CommandFactory(ZorkoGame game) {
        super();
        this.game = game;
    }

    public Command buildCommand(String command) {

        Command c = null;

        // handle management commands
        if (command.equals(CMD_EXIT) || command.equals(CMD_QUIT)) {
            c = new QuitCommand(game, command);
        }
        else if (command.equals(CMD_HELP)) {
            c = new HelpCommand(game, command);
        }
        else if (command.equals(CMD_INVENTORY)) {
            c = new InventoryCommand(game, command);
        }
        // handle directional commands
        else if (command.equals(CMD_NORTH) || command.equals(CMD_EAST)
                || command.equals(CMD_SOUTH) || command.equals(CMD_WEST)
                || command.equals(CMD_UP) || command.equals(CMD_DOWN)) {
            c = new MoveCommand(game, command);
        }
        // handle action commands
        else if (command.startsWith(CMD_ATTACK)) {
            c = new AttackCommand(game, command);
        }
        else if (command.startsWith(CMD_DROP)) {
            c = new DropCommand(game, command);
        }
        else if (command.equals(CMD_LOOK)) {
            c = new LookCommand(game, command);
        }
        else if (command.equals(CMD_PUT)) {
            c = new PutCommand(game, command);
        }
        else if (command.startsWith(CMD_READ)) {
            c = new ReadCommand(game, command);
        }
        else if (command.startsWith(CMD_TAKE)) {
            c = new TakeCommand(game, command);
        }
        else if (command.startsWith(CMD_TURN_ON)) {
            c = new TurnOnCommand(game, command);
        }
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
        else if (command.startsWith("delete")) {
            c = new DeleteCommand(game, command);
        }
        // TODO: startsWith update needs to be more sophisticated
        else if (command.startsWith("update")) {
            c = new UpdateToCommand(game, command);
        }
        else if (command.startsWith("game over")) {
            c = new GameOverCommand(game, command);
        }
        return c;
    }

}
