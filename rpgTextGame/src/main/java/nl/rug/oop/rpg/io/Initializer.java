package nl.rug.oop.rpg.io;

import nl.rug.oop.rpg.Scenario;
import nl.rug.oop.rpg.game.Game;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * Class that contains some methods for initialising and loading Java Properties
 */
public class Initializer {

    /**
     * Creates a number of Java Properties and stores it in a file inside of a config directory
     *
     * @param fileName The name of the file the properties should be stored in
     */
    public static void createProperties(String fileName) {
        /* Set up config directory */
        File configDirectory = new File("assignment_1" + File.separator + "config");
        configDirectory.mkdirs();

        /* Create some properties */
        Properties rpgProperties = new Properties();
        rpgProperties.setProperty("gameName", "cells plus wyverns");
        rpgProperties.setProperty("playerName", "Daniël");
        int n = 4;
        rpgProperties.setProperty("playerBaseDamage", String.valueOf(n));
        n = 20;
        rpgProperties.setProperty("playerBaseHealth", String.valueOf(n));
        n = 3;
        rpgProperties.setProperty("potionsFromGuard", String.valueOf(n));

        /* Write everything to a file; note that we often use the .properties extension for this */
        try(FileWriter fileWriter = new FileWriter(configDirectory + File.separator + fileName + ".properties")) {
            rpgProperties.store(fileWriter, "These are the properties of the RPG game");
        } catch (IOException e) {
            System.out.println("Could not write to file");
        }

    }

    /**
     * Initialises a game with properties specified in a properties file
     *
     * @param fileName The name of the file the properties are stored in
     * @return A game initialised with certain properties
     * @throws IOException If the file could not be read
     */
    public static Scenario initGameFromProperties(String fileName) throws IOException {
        File configDirectory = new File("assignment_1" + File.separator + "config");

        try(FileReader fileReader = new FileReader(configDirectory + File.separator + fileName + ".properties")) {
            Properties rpgProperties = new Properties();
            rpgProperties.load(fileReader);

            String gameName = rpgProperties.getProperty("gameName");
            String playerName = rpgProperties.getProperty("playerName");
            int damage = Integer.parseInt(rpgProperties.getProperty("playerBaseDamage"));
            int health = Integer.parseInt(rpgProperties.getProperty("playerBaseHealth"));
            int pots = Integer.parseInt(rpgProperties.getProperty("potionsFromGuard"));
            Scenario scenario = new Scenario(playerName,gameName,health,damage,pots);
            return scenario;
        }
    }
}
