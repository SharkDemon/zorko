package com.simco.zorko;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.simco.zorko.builder.BorderBuilder;
import com.simco.zorko.builder.ConditionHasBuilder;
import com.simco.zorko.builder.ConditionStatusBuilder;
import com.simco.zorko.builder.ContainerBuilder;
import com.simco.zorko.builder.CreatureBuilder;
import com.simco.zorko.builder.GameBuilder;
import com.simco.zorko.builder.ItemBuilder;
import com.simco.zorko.builder.RoomBuilder;
import com.simco.zorko.builder.TriggerBuilder;
import com.simco.zorko.builder.TurnOnBuilder;
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
                                .addCondition( new ConditionHasBuilder()
                                        .setHas(Boolean.FALSE)
                                        .setObjectName("torch")
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
                                .addCondition( new ConditionStatusBuilder()
                                        .setObjectName("lock")
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
                .addItem( new ItemBuilder()
                        .setName("torch")
                        .setWriting("next to a small button it reads \"push for big flame\"")
                        .setStatus("lit")
                        .setTurnOn( new TurnOnBuilder()
                                .setPrint("the torch has erupted into a menacing inferno")
                                .setAction("Update torch to inferno")
                                .build())
                        .build())
                .addItem( new ItemBuilder()
                        .setName("explosive")
                        .setWriting("turn on for boom :-).  Warning!  Keep away from gnomes!")
                        .setStatus("idle")
                        .setTurnOn( new TurnOnBuilder()
                                .setPrint("you hear ticking...")
                                .setAction("Update explosive to ticking")
                                .build())
                        .build())
                .addItem( new ItemBuilder()
                        .setName("key")
                        .setWriting("Exit")
                        .build())
                .addContainer( new ContainerBuilder()
                        .setName("chest")
                        .addItem("explosive")
                        .build())
                .addContainer( new ContainerBuilder()
                        .setName("lock")
                        .setStatus("locked")
                        .setAccept("key")
                        .addTrigger( new TriggerBuilder()
                                .addCondition( new ConditionHasBuilder()
                                        .setHas(Boolean.TRUE)
                                        .setObjectName("key")
                                        .setOwner("lock")
                                        .build() )
                                .addPrint("The lock drops off and the door opens")
                                .addAction("Update lock to unlocked")
                                .build())
                        .build())
                .addCreature( new CreatureBuilder()
                        .setName("gnome")
                        .setVulnerability("explosive")
                        .addTrigger( new TriggerBuilder()
                                .addCondition( new ConditionStatusBuilder()
                                        .setObjectName("torch")
                                        .setStatus("inferno")
                                        .build() )
                                .addPrint("You see a gnome in the dark corner...watching you with its super pointy hat...")
                                .build())
                        .build())
                .build();

        game.begin();
        log.info("Game stopped, exiting");
        System.exit(0);
    }

}
