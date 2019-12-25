package com.simco.zorko.command;

import java.util.HashMap;
import java.util.Map;

public final class Commands {

    // not only does this map hold all possible valid commands, it helps
    // to map alternate versions of a command to the preferred command
    // (i.e., "north" is preferred, but "n" is acceptable as well)
    private static final Map<String, String> commandsMap;

    // management commands
    public static final String CMD_EXIT = "exit";
    public static final String CMD_HELP = "help";
    public static final String CMD_INVENTORY = "inventory";
    public static final String CMD_QUIT = "quit";

    // directional commands
    public static final String CMD_NORTH = "north";
    public static final String CMD_EAST = "east";
    public static final String CMD_SOUTH = "south";
    public static final String CMD_WEST = "west";
    public static final String CMD_UP = "up";
    public static final String CMD_DOWN = "down";

    // action commands
    public static final String CMD_ATTACK = "attack";
    public static final String CMD_DROP = "drop";
    public static final String CMD_LOOK = "look";
    public static final String CMD_PUT = "put";
    public static final String CMD_READ = "read";
    public static final String CMD_TAKE = "take";
    public static final String CMD_TURN_ON = "turn on";

    static {
        commandsMap = new HashMap<String, String>(0);

        // management commands
        add(CMD_EXIT);
        add(CMD_HELP, "h");
        add(CMD_INVENTORY, "inv", "i");
        add(CMD_QUIT, "q");

        // directional commands
        add(CMD_NORTH, "n");
        add(CMD_EAST, "e");
        add(CMD_SOUTH, "s");
        add(CMD_WEST, "w");
        add(CMD_UP, "u");
        add(CMD_DOWN, "d");

        // action commands
        add(CMD_ATTACK);
        add(CMD_DROP);
        add(CMD_LOOK);
        add(CMD_PUT);
        add(CMD_READ);
        add(CMD_TAKE, "get", "grab");
        add(CMD_TURN_ON, "turnon", "enable");
    }

    private static void add(String preferredCommand, String... cmds) {
        commandsMap.put(preferredCommand, preferredCommand);
        for (String cmd : cmds) {
            commandsMap.put(cmd, preferredCommand);
        }
    }

    public static String getCommand(String command) {
        return commandsMap.get(command);
    }

}
