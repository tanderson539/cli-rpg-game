import items.ores.Ore_Coal;
import items.ores.Ore_Copper;
import player.Player;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to the Simple CLI. Type 'exit' to quit.");
        Scanner scanner = new Scanner(System.in);

        Player player = new Player("Player 1");

        CommandListener cmdListener = new CommandListener(player, scanner);

        cmdListener.update();
    }
}
