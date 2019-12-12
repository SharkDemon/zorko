package com.simco.zorko;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZorkoGame {

    private static Logger log = LoggerFactory.getLogger(ZorkoGame.class);

    private static final String USER_PROMPT = "> ";

    public void begin() {
        Scanner input = new Scanner(System.in);
        boolean gameRunning = true;
        String command = "";

        while (gameRunning) {
            try {
                System.out.print(USER_PROMPT);
                command = input.nextLine();
                gameRunning = handleCommand(command);
            }
            catch (Exception e) {
                log.info("Error handling command: {}", command);
            }
        }
        input.close();
    }

    public static boolean handleCommand(String command) {
        System.out.println("TODO: handle command here");
        return !command.equals("exit");
    }

}
