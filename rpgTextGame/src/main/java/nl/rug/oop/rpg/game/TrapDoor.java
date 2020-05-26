package nl.rug.oop.rpg.game;

public class TrapDoor extends Door {
    private static final long serialVersionUID = 16L;

    private boolean opened;
    private int damage;
    public TrapDoor (String description, Room room, int damage) {
        super(description,room);
        opened = false;
        this.damage = damage;
    }

    /**
     * makes the player interact with the trapdoor
     * @param player the player
     */
    @Override
    public void interact(Player player) {
        //don't take damage if opened before
        if (opened) {
            player.goTo(room);
        }
        //damages the player the first time they pass through
        System.out.println("The door was trapped.\nYou took damage!");
        player.addHealth(-damage);
        opened = true;
        player.goTo(room);
    }
}
