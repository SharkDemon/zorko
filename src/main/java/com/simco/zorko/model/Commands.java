package com.simco.zorko.model;

import java.util.HashMap;
import java.util.Map;

public final class Commands {

    // not only does this map hold all possible valid commands, it helps
    // to map alternate versions of a command to the preferred command
    // (i.e., "north" is preferred, but "n" is acceptable as well)
    private final Map<String, String> commandsMap;

    // management commands
    public static final String CMD_EXIT = "exit";
    public static final String CMD_HELP = "help";
    public static final String CMD_INVENTORY = "inventory";
    public static final String CMD_QUIT = "q";

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

    private Commands() {
        super();
        // restrict instantiation

        this.commandsMap = new HashMap<String, String>(0);

        // management commands
        add(CMD_INVENTORY, "inv", "i");

        // directional commands
        add(CMD_NORTH, "n");
        add(CMD_EAST, "e");
        add(CMD_SOUTH, "s");
        add(CMD_WEST, "w");
        add(CMD_UP, "u");
        add(CMD_DOWN, "d");
    }

    private void add(String preferredCommand, String... cmds) {
        commandsMap.put(preferredCommand, preferredCommand);
        for (String cmd : cmds) {
            commandsMap.put(cmd, preferredCommand);
        }
    }

    public String getCommand(String command) {
        return this.commandsMap.get(command);
    }

}
