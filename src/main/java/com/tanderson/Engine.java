package com.tanderson;

import com.tanderson.command.CommandDispatcher;
import com.tanderson.systems.craftingSystem.CraftingManager;
import com.tanderson.display.Screen;
import com.tanderson.player.Player;
import com.tanderson.systems.rds.RDSRandom;

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

    /**
     * Initializes the game by creating a new Player, crafting manager, RDSRandom, context,
     * command dispatcher, and screen objects.
     */
    public void gameInit() {
        this.player = new Player("Player 1");
        this.craftingManager = new CraftingManager(this.player.getInventory());
        this.rand = new RDSRandom();
        this.context = new GameContext(this.player, this.craftingManager, this.rand, false);
        this.dispatcher = new CommandDispatcher(this.context);

        this.screen = new Screen(this.dispatcher);

        System.out.println("[INFO] - Game initialized");
    }
}
