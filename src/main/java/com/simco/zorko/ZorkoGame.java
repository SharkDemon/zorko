package com.simco.zorko;

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
import static com.simco.zorko.model.Commands.CMD_UP;
import static com.simco.zorko.model.Commands.CMD_WEST;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simco.zorko.model.Border;
import com.simco.zorko.model.Commands;
import com.simco.zorko.model.Container;
import com.simco.zorko.model.Creature;
import com.simco.zorko.model.Item;
import com.simco.zorko.model.Room;
import com.simco.zorko.model.Trigger;

public class ZorkoGame implements ZorkoGameEventHandler {

    private static Logger log = LoggerFactory.getLogger(ZorkoGame.class);

    private static final String GAME_USER_PROMPT = "> ";
    // this is the message to print when the user tries to perform an invalid
    // move from a room
    private static final String MSG_INVALID_MOVE = "Can't go that way.";

    private String name = null;
    private Map<String, Room> rooms = new HashMap<String, Room>(0);
    private Map<String, Item> items = new HashMap<String, Item>(0);
    private Map<String, Container> containers = new HashMap<String, Container>(0);
    private Map<String, Creature> creatures = new HashMap<String, Creature>(0);
    private String currentRoomName = null;

    private Map<String, Item> inventoryItems = new HashMap<String, Item>(0);

    public ZorkoGame() {
        super();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // basic Room functionality
    public void setRooms(Map<String, Room> rooms) {
        this.rooms = rooms;
    }
    public Room getRoom(String roomName) {
        return this.rooms.get(roomName);
    }

    // basic Item functionality
    public void setItems(Map<String, Item> items) {
        this.items = items;
    }
    public Item getItem(String itemName) {
        return this.items.get(itemName);
    }

    // basic Container functionality
    public void setContainers(Map<String, Container> containers) {
        this.containers = containers;
    }
    public Container getContainer(String containerName) {
        return this.containers.get(containerName);
    }

    // basic Creature functionality
    public void setCreatures(Map<String, Creature> creatures) {
        this.creatures = creatures;
    }
    public Creature getCreature(String creatureName) {
        return this.creatures.get(creatureName);
    }

    public String getCurrentRoomName() {
        return currentRoomName;
    }
    public void setCurrentRoomName(String currentRoomName) {
        this.currentRoomName = currentRoomName;
    }

    // basic Inventory functionality (Items)
    public Item getInventoryItem(String itemName) {
        return this.inventoryItems.get(itemName);
    }

    // starts the game loop
    public void begin() {
        Scanner input = new Scanner(System.in);
        boolean gameRunning = true;
        String command = "";

        System.out.println(String.format("Welcome to %s.", getName()));
        printCurrentRoomDescription();

        while (gameRunning) {
            try {
                System.out.print(GAME_USER_PROMPT);
                command = input.nextLine().trim().toLowerCase();
                log.info("user input command={}", command);

                if (0 == command.length()) {
                    System.out.println("You need to enter something!");
                    continue;
                }

                command = mapToPreferredCommand(command);
                log.info("mapped command to={}", command);

                // TODO: (1) check if command should be overwritten by triggers for the
                // room (does this include items, containers that are in the room?)
                boolean skipUserCommand = checkAllTriggers(command);
                log.info("skipUserCommand?={}", skipUserCommand);

                // (2) execute the command
                if (!skipUserCommand) {
                    gameRunning = handleCommand(command);
                }
            }
            catch (Exception e) {
                log.info("Error handling command: {}", command);
            }
        }
        input.close();
    }

    private boolean checkAllTriggers(String userCommand) {

        boolean skipUserCommand = false;

        // check current room triggers
        for (Trigger t : getCurrentRoom().getTriggers()) {
            // we only check the trigger if the command matches what the user
            // entered, or if no command was specified
            if (null != t.getCommand() && t.getCommand().equals(userCommand)) {
                if (t.evaluate()) {
                    log.info("Trigger eval true, firing!");
                    // print each of the trigger prints
                    t.getPrints().stream().forEach(System.out::println);
                    // perform each of the trigger actions
                    t.getActions().stream().forEach(action -> handleCommand(action));
                    skipUserCommand = true;
                    // remove the trigger if this was a one-time thing
                    if (t.isSingle()) {
                        getCurrentRoom().getTriggers().remove(t);
                    }
                }
            }
        }

        // check container triggers in the current room
        for (String containerName : getCurrentRoom().getContainers()) {
            Container c = getContainer(containerName);
            for (Trigger t : c.getTriggers()) {

            }
        }

        // check creature triggers in the current room

        return skipUserCommand;
    }

    // handles user-input commands
    @Override
    public boolean handleCommand(String command) {

        // handle management commands
        if (command.equals(CMD_EXIT) || command.equals(CMD_QUIT)) {
            return false;
        }
        else if (command.equals(CMD_HELP)) {
            handleHelp();
        }
        else if (command.equals(CMD_INVENTORY)) {
            handleInventory();
        }
        // handle directional commands
        else if (command.equals(CMD_NORTH) || command.equals(CMD_EAST)
                || command.equals(CMD_SOUTH) || command.equals(CMD_WEST)
                || command.equals(CMD_UP) || command.equals(CMD_DOWN)) {
            handleMove(command);
        }
        // handle action commands
        else if (command.equals(CMD_ATTACK)) {
            System.out.println("TODO: implement command ATTACK");
        }
        else if (command.equals(CMD_DROP)) {
            System.out.println("TODO: implement command DROP");
        }
        else if (command.equals(CMD_LOOK)) {
            printCurrentRoomDescription();
        }
        else if (command.equals(CMD_PUT)) {
            System.out.println("TODO: implement command PUT");
        }
        else if (command.equals(CMD_READ)) {
            System.out.println("TODO: implement command READ");
        }
        else if (command.equals(CMD_TAKE)) {
            System.out.println("TODO: implement command TAKE");
        }

        // (3) check if the effects of the command activate a trigger

        return true;
    }

    private Room getCurrentRoom() {
        return this.rooms.get(this.currentRoomName);
    }

    private void handleHelp() {
        System.out.println("TODO: print help here");
    }

    private void handleInventory() {
        System.out.println(String.format("Inventory: %s",
                0 == inventoryItems.size() ? "nothing" : inventoryItems.keySet().stream().collect(Collectors.joining(", "))));
    }

    private void handleMove(String direction) {
        Room currentRoom = getCurrentRoom();
        for (Border b : currentRoom.getBorders()) {
            if (b.getDirection().equals(direction)) {
                this.currentRoomName = b.getName();
                printCurrentRoomDescription();
                return;
            }
        }
        // at this point, a Border with the desired direction was not found
        System.out.println(MSG_INVALID_MOVE);
    }

    private void printCurrentRoomDescription() {
        //Room currentRoom = getRoom(currentRoomName);
        System.out.println(String.format("%s\n", getCurrentRoom().getDescription()));
    }

    private String mapToPreferredCommand(String c) {

        // tokenize the command string
        List<String> commandTokens = Collections.list(new StringTokenizer(c, " "))
                .stream()
                .map(token -> (String)token)
                .collect(Collectors.toList());

        // attempt to map user-input command (first token) to the
        // preferred version of the command
        commandTokens.set(0, Commands.getCommand(commandTokens.get(0)));

        // return the rebuilt command string
        return commandTokens
                .stream()
                .collect(Collectors.joining(" "));
    }

}
