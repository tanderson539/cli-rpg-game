import java.util.Scanner;

public class CommandListener {

    private Player player;
    private Scanner scanner;

    public CommandListener(Player player, Scanner scanner) {
        this.player = player;
        this.scanner = scanner;
    }

    public void update() {
        while (true){
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
                scanner.close();
                System.exit(0);
                break;
            case "help":
                printHelp();
                break;
            case "level":
                System.out.println(player.getPlayerName() + "'s Mining Level: " + player.getMiningLevel());
                System.out.println("Total XP: " + player.getMiningXp());
                System.out.println("XP to next level: " + player.getMiningXpToNextLevel());
                break;
            case "mine":
                player.mine();
                break;
            default:
                System.out.println("Invalid command");
        }
    }

    private void printHelp() {
        System.out.println("Available commands:");
        System.out.println("help - shows this help message");
        System.out.println("hello - prints 'Hi!'");
        System.out.println("exit - exits the game");
    }
}
