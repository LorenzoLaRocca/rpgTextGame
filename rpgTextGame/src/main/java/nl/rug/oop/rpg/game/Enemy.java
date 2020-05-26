package nl.rug.oop.rpg.game;

import nl.rug.oop.rpg.utils.Attackable;

import java.util.ArrayList;
import java.util.Scanner;

public class Enemy extends NPC implements Attackable {
    private static final long serialVersionUID = 4L;

    private Item drop;

    /**
     * Constructor
     * @param name Enemy npc name
     * @param description Enemy description
     * @param health Enemy health value
     * @param damage Enemy damage value
     */
    //init
    public Enemy(String name,String description,int health, int damage) {
        this.name = name;
        this.description = description;
        this.health = health;
        this.damage = damage;
        this.drop = null;
        isHostile = 1;
    }

    //overloaded method
    public Enemy(String name,String description,int health, int damage, Item drop) {
        this.name = name;
        this.description = description;
        this.health = health;
        this.damage = damage;
        this.drop = drop;
        isHostile = 1;
    }

    //inspect
    @Override
    public void inspect() {
        System.out.println(description);
    }

    /**
     * Let the player interact with an enemy npc
     * @param player the player who interacted
     */
    @Override
    public void interact(Player player) {
        //display info
        System.out.println("You have encountered " + name);
        System.out.println("Health: " + health);
        scanner = new Scanner(System.in);
        /*
         * This is the combat in the game
         * You can attack, use an item or flee
         */
        while(health > 0){
            System.out.println("What do you want to do?");
            System.out.println("    (0) Attack!");
            System.out.println("    (1) Use item");
            System.out.println("    (2) Flee!");

            int input = -1;
            try {
                input = scanner.nextInt();
            } catch(Exception e) {
                System.out.println("This is not an option");
            } finally {
                scanner.nextLine();
            }
            if (input != -1 && input < 3) {//plz use switch
                //attack
                if (input == 0) {
                    player.attack(this);
                }
                //use item
                if (input == 1) {
                    player.useItem();
                }
                //flee
                if (input == 2) {
                    return;
                }

                //attacks back if possible
                if (health > 0) {
                    attack(player);
                }
                if (player.getHealth() == 0) {
                    return;
                }
            }
            if(input > 2) {
                System.out.println("This is not an option");
            }
        }
        if (drop != null) {
            player.addToInventory(drop,1);
        }
    }

    /**
     * attack the player
     * @param player the player to attack
     */
    //damage the player
    public void attack(Player player) {
        player.addHealth(-damage);
    }

    //return current health
    public int getHealth() {
        return health;
    }

    //return current damage
    public int getDamage() {
        return damage;
    }

    /**
     * Changes the health of the enemy
     * @param deltaHealth the change in health
     */
    //changes the health of the enemy
    public void addHealth(int deltaHealth) {
        health = Math.max(health + deltaHealth,0);
        System.out.println("New health of enemy " + name + " = " + health);
    }
}
