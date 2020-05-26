package nl.rug.oop.rpg.game;

import java.util.ArrayList;
import java.util.Scanner;

//not fully implemented yet
public class Merchant extends NPC {
    private static final long serialVersionUID = 12L;

    private ArrayList<Item> shop;
    private int shopLength;
    private int[] prices;

    /**
     * Constructor
     *
     * @param name Merchant NPC name
     * @param description Merchant NPC Description
     * @param shop Merchant NPC
     * @param shopLength Merchant NPC total items in a shop
     * @param prices Merchant NPC shops prices
     */
    public Merchant(String name, String description, ArrayList<Item> shop, int shopLength, int[] prices) {
        this.name = name;
        this.description = description;
        health = 32;
        damage = 1;
        isHostile = 0;
        this.shop = new ArrayList<>();
        this.shopLength = shopLength;
        this.shop.addAll(shop);
        this.prices = prices;
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }

    /**
     * Shows the shop to the player and gives the player the opportunity to buy something
     *
     * @param player the player
     */
    @Override
    public void interact(Player player) {
        /**
         * Shows the shop
         */
        scanner = new Scanner(System.in);
        System.out.println("Good day sir, what can i help you with?");
        int i = 0;
        for (Item article: shop) {
            System.out.println("    (" + i + ") " + article.getDescription() + ": " + shop.get(i+shopLength).name + " x" + prices[i]);
            i++;
            if (i == shopLength) break;
        }
        System.out.println("    (" + i + ") Exit");
        /**
         * Reads input
         */
        int input = -1;
        try {
            input = scanner.nextInt();
        } catch(Exception e) {
            System.out.println("This is not an option");
        }  finally {
            scanner.nextLine();
        }
        if (input == i || input == -1) {
            return;
        }
        /**
         * Makes the player 'buy' an item
         */
        if (-1 < input && input < i) {
            if (player.inInventory(shop.get(input + shopLength),prices[input])) {
                player.removeFromInventory(shop.get(input + shopLength),prices[input]);
                player.addToInventory(shop.get(input),1);
            } else {
                System.out.println("You cannot buy this item.");
            }
        } else {
            System.out.println("This is not an option");
        }
    }
}
