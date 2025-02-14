import player.Player;

import java.util.Scanner;

public class CommandListener {

    private Player player;
    private Scanner scanner;

    private boolean isRunning = false;

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
        switch (input) {
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
                player.printMiningLevel();
                break;
            case "mine":
                player.mine();
                break;
            case "inv":
                player.getInventory().printInventory();
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
        System.out.println("exit - exits the game");
    }
}
