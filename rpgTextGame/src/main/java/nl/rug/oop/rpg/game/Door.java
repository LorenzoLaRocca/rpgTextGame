package nl.rug.oop.rpg.game;

import nl.rug.oop.rpg.utils.Inspectable;
import nl.rug.oop.rpg.utils.Interactable;

import java.io.Serializable;

public class Door implements Inspectable, Interactable, Serializable {
    private static final long serialVersionUID = 3L;

    //a door
    protected String description;
    protected Room room;

    /**
     * Constructor
     *
     * @param description Door Description
     * @param room Room in which the door is linked
     *
     */
    //init door with description
    public Door(String description, Room room) {
        this.description = description;
        this.room = room;
    }

    /**
     * Let the player access the door description
     */
    //inspect door
    @Override
    public void inspect() {
        System.out.println(description);
    }

    /**
     * Let the player interact with the door and change the room
     */
    //interact door
    @Override
    public void interact(Player player) {
        player.goTo(room);
    }

    /**
     * Let the player access the room description
     */
    //get description of the room
    public String getDescription() {
        return description;
    }
}
