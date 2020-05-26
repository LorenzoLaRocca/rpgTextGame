package nl.rug.oop.rpg.io;

import nl.rug.oop.rpg.game.Game;

import java.io.*;

public class Serializer {

    /**
     * Saves a game
     *
     * @param game The game that needs to be saved
     * @param fileName The name of the savefile the game should be saved in
     */
    public static void saveGame(Game game, String fileName) {

        File saveDirectory = new File("assignment_1" + File.separator + "saves");
        saveDirectory.mkdirs();

        try(FileOutputStream fileOutputStream = new FileOutputStream(saveDirectory + File.separator + fileName + ".ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(game);
            objectOutputStream.close();
            System.out.println("Data Saved");
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found");
        } catch (IOException e) {
            System.out.println("File could not write to file");
        }
    }

    /**
     * Loads a game from a savefile
     *
     * @param fileName The name of the savefile the game should be loaded from
     * @return The game that was saved in the savefile
     * @throws IOException If the file could not be found or read from
     * @throws ClassNotFoundException If the class could not be properly loaded
     */
    public static Game loadGame(String fileName) throws IOException, ClassNotFoundException {
        File saveDirectory = new File("assignment_1" + File.separator + "saves");

        try(FileInputStream fileInputStream = new FileInputStream(saveDirectory + File.separator + fileName + ".ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            Game game = (Game)objectInputStream.readObject();
            return game;
        }
    }
}
