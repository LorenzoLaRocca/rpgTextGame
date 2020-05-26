package nl.rug.oop.rpg;

import nl.rug.oop.rpg.io.Initializer;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main class
 * Initialises a game
 */
public class Main {

    /**
     * Main method, creates a game with rooms, door, items, npcs and the player
     *
     * @param args Command-line arguments; not used
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You are about to start the game, what do you want to do?");

        System.out.println("    (0) Play normally");
        System.out.println("    (1) Initialise from config");
        System.out.println("    (2) Set default config");

        int input = scanner.nextInt();
        if (input == 0) {
            Scenario scenario = new Scenario();
            scenario.initGame();
        } else if (input == 1) {
            Scenario scenario = null;
            try {
                scenario = Initializer.initGameFromProperties("rpgConfig");
            } catch (IOException e) {
                System.out.println("Game didn't load correctly");
            }
            try {
                scenario.initGame();
            } catch (Exception e) {
                System.out.println("Something went wrong while initializing the file");
            }
        } else if (input == 2) {
            Initializer.createProperties("rpgConfig");
        }

    }
}