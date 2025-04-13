package com.tanderson;

import com.tanderson.command.CommandDispatcher;
import com.tanderson.items.Item;
import com.tanderson.items.ItemFactory;
import com.tanderson.items.RegisteredItem;
import com.tanderson.log.LogLevel;
import com.tanderson.log.Logger;
import com.tanderson.systems.craftingSystem.CraftingManager;
import com.tanderson.display.Screen;
import com.tanderson.player.Player;
import com.tanderson.systems.rds.RDSRandom;
import org.reflections.Reflections;

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
}
