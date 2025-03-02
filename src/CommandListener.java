import craftingSystem.CraftingManager;
import craftingSystem.CraftingRepo;
import items.CraftableItem;
import items.ItemRecord;
import items.ingots.Ingot_Copper;
import org.apache.commons.lang3.StringUtils;
import player.Player;
import rds.RDSRandom;
import rds.tables.OreTable;

import java.util.Scanner;

public class CommandListener {

    private final Player player;
    private final Scanner scanner;

    private boolean isRunning;

    private final RDSRandom rand;

    public CommandListener(Player player, Scanner scanner) {
        this.player = player;
        this.scanner = scanner;
        this.isRunning = true;
        this.rand = new RDSRandom();
    }

    public CommandListener(Player player, Scanner scanner, int randSeed) {
        this.player = player;
        this.scanner = scanner;
        this.isRunning = true;
        this.rand = new RDSRandom(randSeed);
    }

    public void update() {
        while (isRunning) {
            System.out.print("> ");
            String cmd = scanner.nextLine();
            handleInput(cmd);
        }
    }

    private void handleInput(String input) {
        String[] words = input.split(" ");

        switch (words[0]) {
            case "hello":
                System.out.println("Hi!");
                break;
            case "exit":
                isRunning = false;
                scanner.close();
                System.exit(0);
                break;
            case "help":
                printHelp();
                break;
            case "level":
                player.getPlayerSkills().getMiningSkill().printMiningLevel();
                break;
            case "mine":
                player.mine();
                break;
            case "inv":
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
                CraftingRepo repo = new CraftingRepo();
                repo.printCraftableItemList();
                break;
            default:
                System.out.println("Invalid command");
        }
    }

    private void printHelp() {
        System.out.println("Available commands:");
        System.out.println("help - shows this help message");
        System.out.println("hello - prints 'Hi!'");
        System.out.println("mine - mines some ore!");
        System.out.println("inv - shows inventory");
        System.out.println("rand - shows random number");
        System.out.println("loot - runs the ore table for debug testing");
        System.out.println("exit - exits the game");
    }

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

    private void handleRand(String[] words){
        double max = 20.0;

        if(words.length > 1){
            if(StringUtils.isNumeric(words[1])){
                max = Double.parseDouble(words[1]);
            }
        }
        System.out.println("Rand (max possible: " + max + "):\n" + rand.genDouble(max));
    }

    private void handleCraft(String[] words){
        CraftingManager manager = new CraftingManager(this.player.getInventory());
        CraftingRepo repo = new CraftingRepo();

        CraftableItem itemToCraft = repo.getCraftableItem(Integer.parseInt(words[1]));

        boolean didCraft = manager.craftItem(itemToCraft);

        if(didCraft){
            System.out.println("Successfully crafted!");
        }else{
            System.out.println("Failed to craft item!");
        }
    }
}