package nl.rug.oop.rpg.game;

import nl.rug.oop.rpg.utils.Usable;

import java.io.IOException;

/**
 * Consumable class
 * Initialises a consumable item
 */
public class Consumable extends Item implements Usable {

    private static final long serialVersionUID = 2L;

    private int effect;

    /**
     * Constructor
     *
     * @param name The name of the consumable
     * @param description Consumable description
     * @param effect Consumable effect value
     *
     */
    public Consumable(String name, String description,int effect) {
        super(name,description);
        this.effect = effect;
    }

    /**
     * use a consumable on @param entity
     * 1 = heal
     *
     * There is the option for extension
     */
    @Override
    public void use(Entity entity) {
        if (entity instanceof Player) {
            Player player = (Player)entity;
            if (effect == 1) {
                player.addHealth(10);
                player.removeFromInventory(this,1);
            }
        }
    }
}
