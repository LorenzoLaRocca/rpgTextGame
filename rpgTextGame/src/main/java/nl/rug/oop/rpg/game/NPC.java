package nl.rug.oop.rpg.game;

import nl.rug.oop.rpg.utils.Inspectable;
import nl.rug.oop.rpg.utils.Interactable;

import java.io.Serializable;
import java.util.Scanner;

public abstract class NPC extends Entity implements Inspectable, Interactable, Serializable {
    private static final long serialVersionUID = 13L;

    protected String description;
    protected String name;
    public int isHostile;

    protected transient Scanner scanner;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
