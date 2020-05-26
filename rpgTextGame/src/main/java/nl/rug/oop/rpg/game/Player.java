package nl.rug.oop.rpg.game;

import nl.rug.oop.rpg.utils.Attackable;

import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Entity implements Attackable {
    private static final long serialVersionUID = 14L;


    //the player class
    //private String name;
    private Room room;
    private int maxHealth;
    private ArrayList<Item> inventory;
    private int equipment[];
    private transient Scanner scanner;

    /**
     * Makes a new player
     * @param name the name of the player
     * @param health the base health of the player
     * @param damage the base damage of the player
     */
    //init the player
    public Player(String name,int health, int damage) {
        this.name = name;
        this.health = maxHealth = health;
        this.damage = damage;
        inventory = new ArrayList<>();
        equipment = new int[]{0, 0};
    }

    //get room
    public Room getRoom() {
        return room;
    }

    public String getName() {
        return name;
    }

    //changes player location
    public void goTo(Room room) {
        this.room = room;
    }

    //for combat
    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return (damage + equipment[0]);
    }

    public void attack(Enemy enemy) {
        enemy.addHealth(-(damage + equipment[0]));
    }

    /**
     * Changes the player health
     *
     * @param deltaHealth the amount of change
     */
    public void addHealth(int deltaHealth) {
        if (health + deltaHealth > getMaxHealth()) {
            health = getMaxHealth();
        } else if (health + deltaHealth < 0) {
            health = 0;
        } else {
            health += deltaHealth;
        }
        System.out.println("New health of player " + name + " = " + health);
    }

    public int getMaxHealth() {
        return maxHealth + equipment[1];
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    /**
     * Checks if the item is in the player inventory
     * @param item the item
     * @param count the amount of that item
     * @return True if in inventory else False
     */
    public boolean inInventory(Item item, int count) {
        if (inventory.contains(item)) {
            int index = inventory.indexOf(item);
            return (inventory.get(index).getCount() >= count);
        }
        return false;
    }

    /**
     * Removes an item from the player inventory
     * @param item the item
     * @param count the amount of that item
     */
    public void removeFromInventory(Item item, int count) {
        if (inventory.contains(item)) {
            int index = inventory.indexOf(item);
            inventory.get(index).setCount(inventory.get(index).getCount()-count);
            if (inventory.get(index).getCount() <= 0) {
                inventory.remove(item);
            }
        }
    }

    /**
     * Adds an item to the player inventory
     * @param item the item
     * @param count the amount of that item
     */
    public void addToInventory(Item item, int count) {
        if (inventory.contains(item)) {
            int index = inventory.indexOf(item);
            inventory.get(index).setCount(inventory.get(index).getCount()+count);
        } else {
            inventory.add(item);
            int index = inventory.indexOf(item);
            inventory.get(index).setCount(count);
        }
        System.out.println("Player " + name + " had obtained " + item.name + " x" + count);
    }

    /**
     * this toggles the equipment of the player
     * @param item the item which is toggled
     * @param slot slot 0 for damage and slot 1 for health
     * @param stat the amount of extra health and damage
     */
    public void toggleEquipment(Equipment item, int slot, int stat) {
        item.toggle();
        if (item.isToggled()) {
            System.out.println("Equiped " + item.getName());
        } else {
            System.out.println("Unequiped " + item.getName());
        }
        equipment[slot] = stat;
    }

    /**
     * This makes it possible for the player to use items
     */
    public void useItem(){
        System.out.println("Your items: (-1: go back)");
        scanner = new Scanner(System.in);
        int i = 0;
        for (Item item: inventory) {
            System.out.println("    (" + i + ") " + item.getDescription() + " x" + item.getCount());
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
        /**
         * gives the .use for every option
         */
        if (-1 < input && input < i) {
            inventory.get(input).use(this);
        } else {
            System.out.println("This is not an option");
        }
    }
}
