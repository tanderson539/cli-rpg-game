import items.DroppableItem;
import items.ores.Ore_Copper;
import items.ores.Ore_Iron;
import player.Player;
import rds.RDSObject;
import rds.RDSTable;

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
