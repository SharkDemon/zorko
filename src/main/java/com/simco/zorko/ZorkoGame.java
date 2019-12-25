package com.simco.zorko;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simco.zorko.command.Command;
import com.simco.zorko.command.CommandFactory;
import com.simco.zorko.command.Commands;
import com.simco.zorko.model.Container;
import com.simco.zorko.model.Creature;
import com.simco.zorko.model.Item;
import com.simco.zorko.model.Room;
import com.simco.zorko.model.Trigger;

public class ZorkoGame implements ZorkoGameEventHandler {

    private static Logger log = LoggerFactory.getLogger(ZorkoGame.class);

    private static final String GAME_USER_PROMPT = "> ";

    private boolean running = false;

    private String name = null;
    private Map<String, Room> rooms = new HashMap<String, Room>(0);
    private Map<String, Item> items = new HashMap<String, Item>(0);
    private Map<String, Container> containers = new HashMap<String, Container>(0);
    private Map<String, Creature> creatures = new HashMap<String, Creature>(0);
    private String currentRoomName = null;
    private List<String> inventory = new ArrayList<String>(0);

    public ZorkoGame() {
        super();
    }

    public boolean isRunning() {
        return running;
    }
    public void setRunning(boolean running) {
        this.running = running;
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
    public List<String> getInventory() {
        return this.inventory;
    }
    public boolean playerHasItem(String itemName) {
        return inventory.contains(itemName);
    }
    public Item getInventoryItem(String itemName) {
        return inventory.contains(itemName) ? items.get(itemName) : null;
    }
    public void addInventoryItem(String itemName) {
        inventory.add(itemName);
    }
    public void addInventoryItem(Item item) {
        inventory.add(item.getName());
    }
    public void removeInventoryItem(String itemName) {
        inventory.remove(itemName);
    }

    // starts the game loop
    public void begin() {
        Scanner input = new Scanner(System.in);
        this.running = true;
        String userCommand = new String();
        String mappedCommand = new String();

        System.out.println(String.format("Welcome to %s.", getName()));
        System.out.println(String.format("%s\n", getCurrentRoom().getDescription()));

        while (this.running) {
            try {
                System.out.print(GAME_USER_PROMPT);
                userCommand = input.nextLine().trim().toLowerCase();

                if (0 == userCommand.length()) {
                    System.out.println("You need to enter something!");
                    continue;
                }

                mappedCommand = mapToPreferredCommand(userCommand);
                log.info("userCommand=[{}] mappedCommand=[{}]", userCommand, mappedCommand);

                // TODO: (1) check if command should be overwritten by triggers for the
                // room (does this include items, containers that are in the room?)
                boolean skipUserCommand = checkAllTriggers(mappedCommand);
                log.info("skipUserCommand?={}", skipUserCommand);

                // (2) execute the command
                if (!skipUserCommand) {
                    handleCommand(mappedCommand);
                }
            }
            catch (Exception e) {
                log.error("Error handling command=[{}]: {}", userCommand, e.getMessage());
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
    public void handleCommand(String userCommand) {

        Command command = new CommandFactory(this).buildCommand(userCommand);
        log.info("created command type={}", command.getClass().getName());
        command.execute();
        // (3) check if the effects of the command activate a trigger
    }

    public void handleGameCommand(String gameCommand) {
        Command command = new CommandFactory(this).buildGameCommand(gameCommand);
        command.execute();
    }

    public Room getCurrentRoom() {
        return this.rooms.get(this.currentRoomName);
    }

    private String mapToPreferredCommand(String c) {

        // tokenize the command string
        List<String> commandTokens = Collections.list(new StringTokenizer(c, " "))
                .stream()
                .map(token -> (String)token)
                .collect(Collectors.toList());

        // attempt to map user-input command (first token) to the
        // preferred version of the command
        String mapTry = Commands.getCommand(commandTokens.get(0));
        // TODO: need to do 2nd try for 2-word commands?
        if (null != mapTry) {
            commandTokens.set(0, mapTry);
        }

        // return the rebuilt command string
        return commandTokens
                .stream()
                .collect(Collectors.joining(" "));
    }

    private String extractCommandTarget(String action, String command) {
        String target = command.substring(action.length() + 1);
        return target;
    }


}
