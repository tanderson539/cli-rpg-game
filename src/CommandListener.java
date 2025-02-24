import items.ItemRecord;
import org.apache.commons.lang3.StringUtils;
import player.Player;
import rds.RDSRandom;
import rds.tables.OreTable;

import java.util.Scanner;

public class CommandListener {

    private final Player player;
    private final Scanner scanner;

    private boolean isRunning;

    public CommandListener(Player player, Scanner scanner) {
        this.player = player;
        this.scanner = scanner;
        this.isRunning = true;
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

        RDSRandom rand = new RDSRandom();

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
                break;
            case "rand":
                double max = 20.0;

                if(words.length > 1){
                    max = Double.parseDouble(words[1]);
                }
                System.out.println("" + rand.genDouble(max));
                break;
            case "loot":
                OreTable table = new OreTable();

                ItemRecord[] items;

                items = table.runTable();

                if(items.length == 0){
                    System.out.println("Nothing dropped!");
                }else {
                    System.out.println("Dropped: " + items[0].getItem().getName());
                }
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
}
