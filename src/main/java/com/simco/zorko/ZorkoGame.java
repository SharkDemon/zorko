package com.simco.zorko;

import static com.simco.zorko.model.Commands.CMD_EXIT;
import static com.simco.zorko.model.Commands.CMD_INVENTORY;
import static com.simco.zorko.model.Commands.CMD_LOOK;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simco.zorko.model.Container;
import com.simco.zorko.model.Creature;
import com.simco.zorko.model.Item;
import com.simco.zorko.model.Room;
import com.simco.zorko.model.Trigger;

public class ZorkoGame implements ZorkoGameEventHandler {

    private static Logger log = LoggerFactory.getLogger(ZorkoGame.class);

    private static final String GAME_USER_PROMPT = "> ";

    private String name = null;
    private Map<String, Room> rooms = new HashMap<String, Room>(0);
    private Map<String, Item> items = new HashMap<String, Item>(0);
    private Map<String, Container> containers = new HashMap<String, Container>(0);
    private Map<String, Creature> creatures = new HashMap<String, Creature>(0);
    private String currentRoomName = null;

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
    public boolean hasRoom(Room room) {
        return this.rooms.containsKey(room.getName());
    }
    public Room getRoom(String roomName) {
        return this.rooms.get(roomName);
    }

    // basic Item functionality
    public void setItems(Map<String, Item> items) {
        this.items = items;
    }
    public boolean hasItem(Item item) {
        return this.items.containsKey(item.getName());
    }
    public Item getItem(String itemName) {
        return this.items.get(itemName);
    }

    // basic Container functionality
    public void setContainers(Map<String, Container> containers) {
        this.containers = containers;
    }

    // basic Creature functionality
    public void setCreatures(Map<String, Creature> creatures) {
        this.creatures = creatures;
    }

    public String getCurrentRoomName() {
        return currentRoomName;
    }
    public void setCurrentRoomName(String currentRoomName) {
        this.currentRoomName = currentRoomName;
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
                command = input.nextLine();

                // TODO: (1) check if command should be overwritten by triggers for the
                // room (does this include items, containers that are in the room?)
                boolean skipUserCommand = checkTriggers();

                // (2) execute the command
                gameRunning = handleCommand(command);
            }
            catch (Exception e) {
                log.info("Error handling command: {}", command);
            }
        }
        input.close();
    }

    private boolean checkTriggers() {
        boolean skipUserCommand = false;
        for (Trigger t : getCurrentRoom().getTriggers()) {
            if (t.evaluate()) {
                // print each of the trigger prints
                t.getPrints().stream().forEach(print -> System.out.println(print));
                // TODO: perform each of the trigger actions
                skipUserCommand = true;
                // remove the trigger if this was a one-time thing
                if (t.isSingle()) {
                    getCurrentRoom().getTriggers().remove(t);
                }
            }
        }
        return skipUserCommand;
    }

    // handles user-input commands
    @Override
    public boolean handleCommand(String userCommand) {
        String command = userCommand.trim().toLowerCase();

        if (command.equals(CMD_EXIT)) {
            return false;
        }
        else if (command.equals(CMD_INVENTORY)) {
            System.out.println("TODO: print inventory string here");
        }
        else if (command.equals(CMD_LOOK)) {
            printCurrentRoomDescription();
        }

        // (3) check if the effects of the command activate a trigger

        return true;
    }

    private Room getCurrentRoom() {
        return this.rooms.get(this.currentRoomName);
    }

    private void printCurrentRoomDescription() {
        Room currentRoom = getRoom(currentRoomName);
        System.out.println(String.format("%s\n", currentRoom.getDescription()));
    }

}
