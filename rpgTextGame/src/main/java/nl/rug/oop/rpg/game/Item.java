package nl.rug.oop.rpg.game;

import nl.rug.oop.rpg.utils.Usable;

import java.io.Serializable;

/**
 * Items only exist once, they are used as reverence to check whether the player has it and how much of it,
 * the item only "exists" in the player inventory
 */
public class Item implements Serializable, Usable {
    private static final long serialVersionUID = 9L;

    /**
     * @param name this is a name
     */
    protected String name;
    protected String description;
    protected int count;

    /**
     * Constructor
     *
     * @param name Item name
     * @param description Item description
     */
    //init
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        count = 1;
    }

    public Item(String name, String description, int count) {
        this.name = name;
        this.description = description;
        this.count = count;
    }

    @Override
    public void use(Entity entity) {
        System.out.println("You cannot use this item.");
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }
}
