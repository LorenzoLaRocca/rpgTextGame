package nl.rug.oop.rpg.game;

import nl.rug.oop.rpg.Scenario;
import nl.rug.oop.rpg.game.*;
import nl.rug.oop.rpg.io.Serializer;

import javax.swing.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game implements Serializable {
    private static final long serialVersionUID = 1L;

    private transient Scanner scanner;
    private Player player;
    private Item trophy;
    private int active;

    /**
     * constructor
     *
     * @param room the starting room
     * @param player the player
     * @param trophy the win condition
     */
    public Game(Room room, Player player, Item trophy){
        this.player = player;
        this.player.goTo(room);
        this.trophy = trophy;
        active = 1;
    }

    /**
     * This Loop is the core of the game, it loops every "turn" to give the player options
     *
     * @param scenario shows in what scenario the game is running for the saving mechanism
     */
    //options for the player
    public void start(Scenario scenario) {
        scanner = new Scanner(System.in);
        while (player.getHealth() > 0) {
            /**
             * checks if the player has won yet
             */
            if (player.inInventory(trophy,1)) {
                System.out.println("You WIN!");
                return;
            }
            if (active == 0) {
                return;
            }
            /**
             * The options for the player
             */
            System.out.println("What do you want to do?");
            System.out.println("    (0) Look around");
            System.out.println("    (1) Look for a way out");
            System.out.println("    (2) Look for company");
            System.out.println("    (3) Check your inventory");
            System.out.println("    (4) QuickSave");
            System.out.println("    (5) QuickLoad");
            System.out.println("    (6) Save");
            System.out.println("    (7) Load");
            /**
             * reading input
             */
            int input = -1;
            try {
                input = scanner.nextInt();
            } catch(Exception e) {
                System.out.println("This is not an option");
            } finally {
                scanner.nextLine();
            }
            if (input < 8) {
                if (input == 0) op0();
                if (input == 1) op1();
                if (input == 2) op2();
                if (input == 3) op3();
                if (input == 4) {
                    save(0,"quickSave",scenario);
                }
                if (input == 5) {
                    load(0, scenario);
                }
                if (input == 6) {
                    System.out.println("Please give the file a name");
                    String saveName = scanner.nextLine();
                    save(1,saveName, scenario);
                }
                if (input == 7) {
                    load(1, scenario);
                }
            } else {
                System.out.println("This is not an option");
            }
        }
        System.out.println("Game Over");
    }

    /**
     * Let the player to read the Room Description
     */
    //look around
    private void op0() {
        Room room = player.getRoom();
        room.inspect();
    }

    /**
     * Let the player to look for doors in the room
     */
    //look for doors
    private void op1() {
        Room room = player.getRoom();
        System.out.println("You look around for doors.");
        room.allDoors(player);
    }

    /**
     * Let the player to find npcs in the room
     */
    //look for npcs
    private void op2() {
        Room room = player.getRoom();
        System.out.println("You look if there's someone here.");
        room.allNPCs(player);
    }

    /**
     * Let the player check the inventory
     */
    private void op3() {
        player.useItem();
    }

    /**
     * Let the player to save the game
     */
    private void save(int option,String fileName, Scenario scenario) {
        if (option == 1) {
            scenario.addSave(fileName);
        }
        Serializer.saveGame(this, fileName);
    }

    /**
     * Let the player to load a saved game
     */
    private void load(int option, Scenario scenario) {
        String fileName = "quickSave";
        List<String> saveFiles = scenario.getSaves();
        if (option == 1) {
            System.out.println("Choose a game to load: (-1 : go back)");
            int i = 0;
            for (String file: saveFiles) {
                System.out.println("    (" + i + ") " + file);
                i++;
            }
            int input = -1;
            try {
                input = scanner.nextInt();
            } catch(Exception e) {
                System.out.println("This is not an option");
            }  finally {
                scanner.nextLine();
            }
            if (input == -1) {
                return;
            }
            if (-1 < input && input < i) {
                fileName = saveFiles.get(input);
            } else {
                System.out.println("This is not an option");
            }
        }
        scenario.loadGame(fileName,this);
    }

    public void stopGame() {
        active = 0;
    }
}
