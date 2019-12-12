package com.simco.zorko;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZorkoApplication
implements CommandLineRunner {

    private static Logger log = LoggerFactory.getLogger(ZorkoApplication.class);

    public static void main(String[] args) {
        log.info("Starting the application");
        SpringApplication.run(ZorkoApplication.class, args);
        log.info("Application finished");
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Executing: command line runner");
        for (int i = 0; i < args.length; ++i) {
            log.info("args[{}]: {}", i, args[i]);
        }

        ZorkoGame game = new ZorkoGame();
        game.begin();
        System.exit(0);
    }

}
