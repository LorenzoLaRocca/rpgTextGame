package nl.rug.oop.rpg.game;

public class Healer extends NPC {
    private static final long serialVersionUID = 8L;

    /**
     * Constructor
     *
     * @param name Healer NPC name
     * @param description Healer NPC description
     */
    //init
    public Healer(String name, String description) {
        this.name = name;
        this.description = description;
        health = 32;
        damage = 1;
        isHostile = 0;
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }

    /**
     * Heals the player back to full health
     *
     * @param player the player
     */
    @Override
    public void interact(Player player) {
        System.out.println("You have encountered " + name);
        System.out.println("He heals you back to full health!");
        //return player to max health
        player.addHealth(player.getMaxHealth());
    }
}
