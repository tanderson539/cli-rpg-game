package com.tanderson;

import com.tanderson.command.CommandDispatcher;
import com.tanderson.command.RegisteredCommand;
import com.tanderson.command.RegisteredSlashCommand;
import com.tanderson.command.commands.Command;
import com.tanderson.items.Item;
import com.tanderson.items.ItemFactory;
import com.tanderson.items.RegisteredItem;
import com.tanderson.log.LogLevel;
import com.tanderson.log.Logger;
import com.tanderson.systems.crafting.CraftingManager;
import com.tanderson.display.Screen;
import com.tanderson.player.Player;
import com.tanderson.systems.rds.RDSRandom;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Set;

/**
 * Main game Engine, handles initialization of the game and display elements. Creates the GameContext object
 * for use elsewhere with other components.
 * TODO: Think about a main game loop, split into update and render methods?
 */
public class Engine {

    private GameContext context;
    private CommandDispatcher dispatcher;
    private Player player;
    private CraftingManager craftingManager;
    private RDSRandom rand;
    private Screen screen;
    private Logger logger;

    public Engine(Logger logger) {
        this.logger = logger;
    }

    /**
     * Initializes the game by creating a new Player, crafting manager, RDSRandom, context,
     * command dispatcher, and screen objects.
     */
    public void gameInit() {
        this.itemInit();
        this.player = new Player("Player 1");
        this.craftingManager = new CraftingManager(this.player.getInventory());
        this.rand = new RDSRandom();
        this.context = new GameContext(this.player, this.craftingManager, this.rand, logger,false);
        this.dispatcher = new CommandDispatcher(this.context);

        this.screen = new Screen(this.dispatcher);

        this.commandsInit();

        logger.log("Game Initialized", LogLevel.INFO);
    }

    /**
     * Takes every item in the items package marked with the @RegisteredItem annotation and
     * adds it to the ItemFactory hashmap. This way we can easily and automatically add new items
     * to the map without needing to manually define the map.
     */
    public void itemInit() {
        Reflections reflections = new Reflections("com.tanderson.items");

        Set<Class<?>> itemClasses = reflections.getTypesAnnotatedWith(RegisteredItem.class);

        this.logger.log("Found " + itemClasses.size() + " item classes", LogLevel.INFO);

        for(Class<?> itemClass : itemClasses) {
            if(Item.class.isAssignableFrom(itemClass)) {

                Class<? extends Item> item = itemClass.asSubclass(Item.class);

                long id = itemClass.getAnnotation(RegisteredItem.class).id();

                ItemFactory.addItem(id, item);
            }
        }
    }

    private void commandsInit() {
        Reflections reflections = new Reflections("com.tanderson.command.commands");

        Set<Class<?>> commandClasses = reflections.getTypesAnnotatedWith(RegisteredCommand.class);
        Set<Class<?>> slashCommandClasses = reflections.getTypesAnnotatedWith(RegisteredSlashCommand.class);

        this.logger.log("Found " + commandClasses.size() + " command classes", LogLevel.INFO);
        this.logger.log("Found " + slashCommandClasses.size() + " slash command classes", LogLevel.INFO);

        for (Class<?> commandClass : commandClasses) {
            try {
                Command instance = (Command) commandClass.getDeclaredConstructor().newInstance();

                RegisteredCommand annotation = commandClass.getAnnotation(RegisteredCommand.class);

                if (annotation != null) {
                    String[] aliases = annotation.aliases();

                    for (String alias : aliases) {
                        dispatcher.registerCommand(alias.toLowerCase(), instance);
                        this.logger.log("Registered command: " + alias, LogLevel.INFO);
                    }
                }
            } catch (NoSuchMethodException e) {
                this.logger.log("Class " + commandClass.getSimpleName() + " is missing a no-args constructor!", LogLevel.ERROR);
            } catch (Exception e) {
                this.logger.log("Failed to instantiate " + commandClass.getSimpleName() + ": " + e.getMessage(), LogLevel.ERROR);
            }
        }

        for (Class<?> slashCommandClass : slashCommandClasses) {
            try {
                Command instance = (Command) slashCommandClass.getDeclaredConstructor().newInstance();

                RegisteredSlashCommand annotation = slashCommandClass.getAnnotation(RegisteredSlashCommand.class);

                if (annotation != null) {
                    String[] aliases = annotation.aliases();

                    for (String alias : aliases) {
                        dispatcher.registerSlashCommand(alias.toLowerCase(), instance);
                        this.logger.log("Registered command: " + alias, LogLevel.INFO);
                    }
                } else {
                    this.logger.log("Command " + slashCommandClass.getSimpleName() + " has no aliases. Skipping.", LogLevel.WARN);
                }
            } catch (NoSuchMethodException e) {
                this.logger.log("Class " + slashCommandClass.getSimpleName() + " is missing a no-args constructor!", LogLevel.ERROR);
            } catch (Exception e) {
                this.logger.log("Failed to instantiate " + slashCommandClass.getSimpleName() + ": " + e.getMessage(), LogLevel.ERROR);
            }
        }
    }
}
