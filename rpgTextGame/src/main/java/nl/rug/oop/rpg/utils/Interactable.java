package nl.rug.oop.rpg.utils;

import nl.rug.oop.rpg.game.Player;

/**
 * An interface that classes can implement so that they can be interactable
 */
public interface Interactable {
    void interact(Player player);
}
