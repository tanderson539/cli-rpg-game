package com.tanderson;

import com.tanderson.craftingSystem.CraftingManager;
import com.tanderson.craftingSystem.CraftingRepo;
import com.tanderson.items.CraftableItem;
import com.tanderson.items.ItemRecord;
import com.tanderson.player.Player;
import com.tanderson.rds.RDSRandom;
import com.tanderson.rds.tables.OreTable;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * Object used to handle the main game logic by interpreting input commands from the console.
 */
public class CommandListener {

    private final Player player;
    private final Scanner scanner;

    private boolean isRunning;

    private final RDSRandom rand;

    /**
     * Main Constructor for the CommandListener class. Sets isRunning to true, allowing for clear control
     * of the main game loop
     * @param player The Player object that all commands will interact with
     * @param scanner The Scanner object to be used to provide game input
     */
    public CommandListener(Player player, Scanner scanner) {
        this.player = player;
        this.scanner = scanner;
        this.isRunning = true;
        this.rand = new RDSRandom();
    }

    /**
     * This constructor should only really be used for testing purposes, due to the requirement to add an RNG seed.
     * @param player The Player object that all commands will interact with
     * @param scanner The Scanner object to be used to provide game input
     * @param randSeed An integer seed that can be used to produce the same RNG roles for testing purposes
     */
    public CommandListener(Player player, Scanner scanner, int randSeed) {
        this.player = player;
        this.scanner = scanner;
        this.isRunning = true;
        this.rand = new RDSRandom(randSeed);
    }

    /**
     * Handles the main game loop
     */
    public void update() {
        while (isRunning) {
            System.out.print("> ");
            String cmd = scanner.nextLine();
            handleInput(cmd);
        }
    }

    /**
     * Handles the input from the Scanner object and utilizes a switch statement to perform game actions.
     * @param input A string of the input captured from the Scanner object.
     */
    private void handleInput(String input) {
        String[] words = input.split(" ");

        switch (words[0]) {
            case "hello":
                System.out.println("Hi!");
                break;
            case "exit":
            case "quit":
            case "x":
                isRunning = false;
                scanner.close();
                System.exit(0);
                break;
            case "help":
            case "h":
                printHelp();
                break;
            case "level":
            case "lvl":
                player.getPlayerSkills().getMiningSkill().printMiningLevel();
                break;
            case "mine":
            case "m":
                player.mine();
                break;
            case "inventory":
            case "inv":
            case "i":
                player.getInventory().printInventory();
                break;
            case "drop":
                this.handleDrop(words);
                break;
            case "rand":
                this.handleRand(words);
                break;
            case "loot":
                this.handleLootCommand();
                break;
            case "craft":
                this.handleCraft(words);
                break;
            case "recipes":
            case "rs":
                CraftingRepo repo = new CraftingRepo();
                repo.printCraftableItemList();
                break;
            default:
                System.out.println("Invalid command");
        }
    }

    /**
     * Prints all available commands that the program can perform.
     */
    private void printHelp() {
        System.out.println("Available commands:");
        System.out.println("help - shows this help message");
        System.out.println("hello - prints 'Hi!'");
        System.out.println("mine - mines some ore!");
        System.out.println("inv - shows inventory");
        System.out.println("level - shows current Mining Level");
        System.out.println("rand - shows random number");
        System.out.println("loot - runs the ore table for debug testing");
        System.out.println("drop *inventory index* *amount to drop* - drops an item in your inventory in the amount specified");
        System.out.println("recipes - shows available crafting recipes");
        System.out.println("craft *recipe #* - crafts an item, presuming you have the resources");
        System.out.println("exit / x / quit - exits the game");
    }

    /**
     * Handles the drop command, allowing you to drop, or for now just get rid of, an item.
     * @param words The input text string split into an array to allow for manipulation of individual words.
     */
    private void handleDrop(String[] words){
        if(words.length == 1 || words.length == 2){
            System.out.println("too few arguments, please type an inventory index and an amount");
        }else {
            if(StringUtils.isNumeric(words[1]) && StringUtils.isNumeric(words[2])){
                int idx = Integer.parseInt(words[1]) - 1;
                int amount = Integer.parseInt(words[2]);

                System.out.println("attempting to remove actual index" + idx + " and " + amount);

                player.getInventory().removeItem(idx, amount);
            }else{
                System.out.println("Command format: drop <index> <amount>");
            }
        }
    }

    /**
     *
     */
    private void handleLootCommand(){
        OreTable table = new OreTable();

        ItemRecord[] items;

        items = table.runTable();

        if(items.length == 0){
            System.out.println("Nothing dropped!");
        }else {
            System.out.println("Dropped: " + items[0].getItem().getName());
        }
    }

    /**
     *
     * @param words The input text string split into an array to allow for manipulation of individual words.
     */
    private void handleRand(String[] words){
        double max = 20.0;

        if(words.length > 1){
            if(StringUtils.isNumeric(words[1])){
                max = Double.parseDouble(words[1]);
            }
        }
        System.out.println("Rand (max possible: " + max + "):\n" + rand.genDouble(max));
    }

    /**
     *
     * @param words The input text string split into an array to allow for manipulation of individual words.
     */
    private void handleCraft(String[] words){
        CraftingManager manager = new CraftingManager(this.player.getInventory());
        CraftingRepo repo = new CraftingRepo();

        CraftableItem itemToCraft = repo.getCraftableItem(Integer.parseInt(words[1]));

        if(itemToCraft == null){
            System.out.println("Recipe not found");
            return;
        }

        boolean didCraft = manager.craftItem(itemToCraft);

        if(didCraft){
            System.out.println("Successfully crafted!");
        }else{
            System.out.println("Failed to craft item!");
        }
    }
}