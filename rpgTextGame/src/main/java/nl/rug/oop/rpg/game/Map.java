package nl.rug.oop.rpg.game;

import nl.rug.oop.rpg.utils.Usable;

import java.io.Serializable;

public class Map extends Item implements Usable, Serializable {
    private static final long serialVersionUID = 11L;

    public Map(String name, String description) {
        super(name,description);
    }

    /**
     * Shows a simple map of the dungeon
     *
     * @param entity the entity which uses the map
     */
    @Override
    public void use(Entity entity) {
        System.out.println("   []-[]\n   |\n[]-[] []-[]\n   |  |\n   []-[]-[]\n      |\n      X");
    }
}
