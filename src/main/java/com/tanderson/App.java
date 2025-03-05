package com.tanderson;

import com.tanderson.player.Player;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to a Simple CLI RPG Game. Type 'exit' or 'x' to quit.");
        System.out.println("Type 'help' for a list of commands.");
        System.out.println("Be aware, saving is not currently supported.");

        Scanner scanner = new Scanner(System.in);

        Player player = new Player("Player 1");

        CommandListener cmdListener = new CommandListener(player, scanner);

        cmdListener.update();
    }
}
