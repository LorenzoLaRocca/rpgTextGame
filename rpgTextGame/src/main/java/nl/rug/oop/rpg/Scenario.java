package nl.rug.oop.rpg;

import nl.rug.oop.rpg.game.*;
import nl.rug.oop.rpg.io.Serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Scenario {


    Scanner scanner;
    Player player;
    String playerName;
    String gameName;
    int potionFromGuard;
    private List<String> saveFiles;
    /**
     * make a new scenario with or without properties
     */
    public Scenario(){
        scanner = new Scanner(System.in);
        System.out.println("First of all enter your Player name please: ");
        playerName = scanner.nextLine();
        player = new Player(playerName,20,4);
        gameName = "cells plus wyverns";
        potionFromGuard = 3;
        saveFiles = new ArrayList<>();
    }

    public Scenario(String playerName, String gameName,int playerBaseHealth, int playerBaseDamage, int potionFromGuard){
        player = new Player(playerName,playerBaseHealth,playerBaseDamage);
        this.gameName = gameName;
        this.potionFromGuard = potionFromGuard;
    }

    /**
     * makes a game
     */
    public void initGame() {
        System.out.println("Welcome to " + gameName);
        System.out.println("In this game you -" + player.getName() + "- will fight through the dungeon in order to defeat the monsters hidden within.");
        System.out.println("Gameplay:\nYou will get the chance to explore a room each turn.");
        System.out.println("In this room you can chose to fight any monsters you see.");
        System.out.println("Apart from that you can also choose to see what you have gathered so far by opening your inventory.");
        System.out.println("Don't forget to equip new items or drink potions before your next battle to be as prepared as possible!");
        System.out.println("Good luck!");

        /**
         * @param dragonTrophy is needed to complete the game
         */
        Item dragonTrophy = new Item("Dragon Trophy","Dropped by defeating the Dragon");

        ArrayList<Room> rooms = initMap(dragonTrophy);

        //make and start the game
        player.addToInventory(new Item("Useless","still useless"),1);
        Game game = new Game(rooms.get(0),player,dragonTrophy);
        startGame(game);
    }

    public void startGame(Game game) {
        game.start(this);
    }

    /**
     * load a game
     * @param fileName the name of the save
     */
    public void loadGame(String fileName, Game oldGame) {
        Game game = null;
        try {
            game = Serializer.loadGame(fileName);
        } catch (IOException e) {
            System.out.println("Could not load from the file");
        } catch (ClassNotFoundException e) {
            System.out.println("The savefile could not be used to load a game");
        }
        if (game == null) {
            System.out.println("something went wrong during loading your new game");
        } else {
            startGame(game);
        }
    }

    /**
     * Makes the map with items and such
     */
    private ArrayList<Room> initMap(Item dragonTrophy) {
        //make a key which can open a door
        Item key = new Item("Key","A key that could open a door");

        //add a potion to the player inventory
        Item potion = new Consumable("Potion","A potion that gives 10 health on use",1);
        //player.addToInventory(potion,1);

        //add items
        Item goblinSkin = new Item("Goblin skin", "Some skin dropped by defeating a goblin");
        Item goblinTrophy = new Item("Goblin Trophy","Some trophy dropped by defeating a bigger goblin");

        Item map = new Map("Map","A map that shows the dungeon layout, the start being at the X");

        player.addToInventory(map,1);


        //init map
        ArrayList<Room> rooms;
        ArrayList<NPC> npcs;
        rooms = new ArrayList<>();
        npcs = new ArrayList<>();

        //room 1
        npcs.add(new GenericNPC("Guard","A guard who guards the dungeon",potion,potionFromGuard));
        rooms.add(new Room("This room seems the be the entrance of a dungeon",npcs));
        npcs.clear();
        //room 2
        npcs.add(new Enemy("Goblin","A goblin",10,2,goblinSkin));
        npcs.add(new Enemy("Mouse","A mouse",4,1));
        npcs.add(new Enemy("Mouse","A mouse",4,1));
        rooms.add(new Room("A big central room",npcs));
        npcs.clear();
        //room 3
        npcs.add(new Healer("Herald the Healer","A mystical Healer"));
        rooms.add(new Room("A room overrun with vines and other plants",npcs));
        npcs.clear();
        //room 4

        //add a shop
        /**
         * adds a shop with the first half bing the items to buy and the second half being items to pay with
         * the amount of each item is in a separate array
         */
        ArrayList<Item> shop = new ArrayList<>();
        shop.add(0,new Equipment("Sword","A Sword with +5 damage",0,5));
        shop.add(1,new Equipment("Chestpiece","Some armor with +8 max health",1,8));
        shop.add(2,potion);
        shop.add(3,goblinSkin);
        shop.add(4,goblinSkin);
        shop.add(5,goblinSkin);
        int shopLength = 3;
        int prices[] = new int[]{3,3,1};

        npcs.add(new Merchant("Bigshop Bob","This merchant has everything you could ever need",shop,shopLength,prices));
        rooms.add(new Room("A chilly hallway",npcs));
        npcs.clear();

        //room 5
        npcs.add(new Enemy("Goblin","A goblin",12,3,goblinSkin));
        npcs.add(new Enemy("Goblin","A goblin",12,3,goblinSkin));
        rooms.add(new Room("You can feel there is something wrong with one of the next rooms",npcs));
        npcs.clear();
        //room 6
        npcs.add(new Enemy("Goblin","A goblin",10,2,goblinSkin));
        npcs.add(new Enemy("Goblin","A goblin",10,2,goblinSkin));
        npcs.add(new Enemy("Goblin","A goblin",10,2,goblinSkin));
        npcs.add(new Enemy("Goblin","A goblin",10,2,goblinSkin));
        rooms.add(new Room("This room seems to be near the base of a the goblins",npcs));
        npcs.clear();
        //room 7
        npcs.add(new Enemy("Big Goblin","A bigger goblin",20,5,key));
        rooms.add(new Room("Is this the boss room???",npcs));
        npcs.clear();
        //room 8
        rooms.add(new Room("There is an eerie silence in this room",npcs));
        npcs.clear();
        //room 9
        rooms.add(new Room("An empty room",npcs));
        npcs.clear();
        //room 10
        npcs.add(new Enemy("Dragon","A dragon!",40,10,dragonTrophy));
        rooms.add(new Room("A dragon lair!",npcs));
        npcs.clear();



        /** connect rooms
         * arg 1 = first room
         * arg 2 = second room
         *
         * arg 3 absent = normal room
         * arg 3 int = trapdoor with damage of the int
         * arg 3 item = door with item as key
         */
        connect(rooms.get(0),rooms.get(1), "A big entrance gate");
        connect(rooms.get(1),rooms.get(3), "A normal door");
        connect(rooms.get(1),rooms.get(5), "A big oak door");
        connect(rooms.get(1),rooms.get(2), "A door with lots of vines everywhere");
        connect(rooms.get(5),rooms.get(6), "A bloody door");
        connect(rooms.get(3),rooms.get(4), "A normal door");
        connect(rooms.get(4),rooms.get(8), "A small hole in the wall", 7);
        connect(rooms.get(4),rooms.get(7), "A golden door");
        connect(rooms.get(7),rooms.get(9), "A daunting gateway to...?",key);

        return rooms;
    }

    //connect 2 rooms with doors
    public static void connect(Room room1, Room room2, String description) {
        room1.addDoor(new Door(description,room2));
        room2.addDoor(new Door("Back door",room1));
    }

    //connect 2 rooms with a trapdoor
    public static void connect(Room room1, Room room2, String description, int damage) {
        room1.addDoor(new TrapDoor(description,room2,damage));
        room2.addDoor(new Door("Back door",room1));
    }

    //connect 2 rooms with doors
    public static void connect(Room room1, Room room2, String description, Item key) {
        room1.addDoor(new KeyDoor(description,room2,key));
        room2.addDoor(new Door("Back door",room1));
    }

    //load save files
    public List<String> getSaves() {
        return saveFiles;
    }

    public void addSave(String fileName) {
        saveFiles.add(fileName);
    }
}

