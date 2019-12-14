package com.simco.zorko;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.simco.zorko.builder.BorderBuilder;
import com.simco.zorko.builder.ConditionBuilder;
import com.simco.zorko.builder.GameBuilder;
import com.simco.zorko.builder.RoomBuilder;
import com.simco.zorko.builder.TriggerBuilder;
import com.simco.zorko.model.Trigger;

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

        // TODO: load the game config from file
        ZorkoGame game = new GameBuilder()
                .setName("Zorko")
                .addRoom( new RoomBuilder()
                        .setName("Entrance")
                        .setDescription("You find yourself at the mouth of a cave and decide that in spite of common sense and any sense of self preservation that you're going to go exploring north into it.  It's a little dark, but luckily there are some torches on the wall.")
                        .addItem("torch")
                        .addTrigger( new TriggerBuilder()
                                .setType(Trigger.TYPE_PERMANENT)
                                .setCommand("north")
                                .addCondition( new ConditionBuilder()
                                        .setHas(Boolean.FALSE)
                                        .setObject("torch")
                                        .setOwner("inventory")
                                        .build() )
                                .addPrint("*stumble* need some light...")
                                .build())
                        .addBorder( new BorderBuilder().setDirection("north").setName("MainCavern").build() )
                        .build())
                .addRoom( new RoomBuilder()
                        .setName("MainCavern")
                        .setDescription("A huge cavern surrounds you with a locked door to the north, a chest in the center, and a very dark corner...")
                        .addContainer("chest")
                        .addContainer("lock")
                        .addCreature("gnome")
                        .addTrigger( new TriggerBuilder()
                                .setType(Trigger.TYPE_PERMANENT)
                                .setCommand("north")
                                .addCondition( new ConditionBuilder()
                                        .setObject("lock")
                                        .setStatus("locked")
                                        .build() )
                                .addPrint("lock needs a key...not to mention you don't want to get too close to that side of the room...there's something in that corner...")
                                .build())
                        .addBorder( new BorderBuilder().setDirection("south").setName("Entrance").build() )
                        .addBorder( new BorderBuilder().setDirection("north").setName("Staircase").build() )
                        .build())
                .addRoom( new RoomBuilder()
                        .setName("Staircase")
                        .setDescription("You found the exit!")
                        .setType("exit")
                        .addBorder( new BorderBuilder().setDirection("south").setName("MainCavern").build() )
                        .build())
                .build();

        game.begin();
        System.exit(0);
    }

}
