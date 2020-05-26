package nl.rug.oop.rpg.game;

import java.util.Scanner;
import java.util.ArrayList;

public class KeyDoor extends Door {
    private static final long serialVersionUID = 10L;
    private Item key;
    private boolean opened;
    private transient Scanner scanner;

    /**
     * Constructor
     *
     * @param description the description of the room
     * @param room the room to which the door leads
     * @param key the key to open the door
     */
    //init
    public KeyDoor (String description, Room room, Item key) {
        super(description,room);
        this.key = key;
        opened = false;
    }

    /**
     * This method requires the player to use the correct key on the door
     *
     * @param player the player
     */
    @Override
    public void interact(Player player) {
        scanner = new Scanner(System.in);
        //go through if opened before
        if (opened) {
            player.goTo(room);
        }

        //show player inventory
        System.out.println("This door requires a key");
        System.out.println("Would you like to use an item?  (-1 : go back)");
        int i = 0;
        ArrayList<Item> items = player.getInventory();
        for (Item item: items) {
            System.out.println("    (" + i + ") " + item.getDescription());
            i++;
        }

        /**
         * Read input
         */
        //if the key is selected go through the door else go back
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
         * Check validity of the selected item
         */
        if (-1 < input && input < i && items.get(input) == key) {
            opened = true;
            player.goTo(room);
        } else {
            System.out.println("This item does nothing");
        }
    }
}
