package nl.rug.oop.rpg.game;

import nl.rug.oop.rpg.utils.Inspectable;

import java.io.Serializable;
import java.util.Scanner;
import java.util.ArrayList;

public class Room implements Inspectable, Serializable {
    private static final long serialVersionUID = 15L;

    //a room
    private String description;
    //doors in room
    private ArrayList<Door> doors;
    // NPCs and enemies in room
    private ArrayList<NPC> npcs;

    private transient Scanner scanner;

    /**
     * Makes a room
     *
     * @param description the description of the room
     * @param npcs the npc in that room
     */
    //init room with description
        public Room(String description, ArrayList<NPC> npcs) {
        this.description = description;
        this.doors = new ArrayList<>();
        this.npcs = new ArrayList<>();
        for (NPC npc:npcs) {
            addNpc(npc);
        }
    }

    /**
     * adds a door to that room
     * @param door the door which leads to another room
     */
    //adds a door to the room
    public void addDoor(Door door) {
        if (door != null) {
            doors.add(door);
        }
    }

    //adds a npc to the room
    public void addNpc(NPC npc) {
        if (npc != null) {
            npcs.add(npc);
        }
    }

    //inspect room
    @Override
    public void inspect() {
        System.out.println("You see: " + description);
    }

    /**
     * Give the player the option to go through a door in that room
     *
     * @param player the player
     */
    public void allDoors(Player player) {
        scanner = new Scanner(System.in);
        //print all options
        System.out.println("You see:");
        int i = 0;
        for (Door door: doors) {
            System.out.println("    (" + i + ") " + door.getDescription());
            i++;
        }
        //interact with chosen door or go back
        System.out.println("Which door do you take? (-1 : stay here)");
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
            doors.get(input).interact(player);
        } else {
            System.out.println("This is not an option");
        }

    }

    /**
     * Give the player the option to interact with an NPC in that room
     * @param player the player
     */
    public void allNPCs(Player player) {
        //print all options
        scanner = new Scanner(System.in);
        System.out.println("You see:");
        int i = 0;
        for (NPC npc: npcs) {
            System.out.println("    (" + i + ") " + npc.getDescription());
            i++;
        }
        //interact with chosen npc
        System.out.println("Interact? (-1 : do nothing)");
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
        /**
         * gives all option for the different classes
         */
        if (-1 < input && input < i) {
            if(npcs.get(input) instanceof Enemy) {
                Enemy enemy = (Enemy)npcs.get(input);
                enemy.interact(player);
                if (enemy.getHealth() == 0) {
                    npcs.remove(enemy);
                }
            } else {
                npcs.get(input).interact(player);
            }
        } else {
            System.out.println("This is not an option");
        }
    }
}
