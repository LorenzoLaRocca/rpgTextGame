package nl.rug.oop.rpg.game;

public class GenericNPC extends NPC{
    private static final long serialVersionUID = 7L;

    private Item item;
    private int itemCount;
    private boolean interacted;

    /**
     * Constructor
     *
     * @param name GenericNPC Name
     * @param description NPC description
     * @param item Generic npcs have something to offer: this item
     * @param itemCount The amount of item the NPC has to offer
     */
    //init
    public GenericNPC(String name, String description, Item item, int itemCount) {
        this.name = name;
        this.description = description;
        health = 32;
        damage = 1;
        isHostile = 0;
        this.item = item;
        interacted = false;
        this.itemCount = itemCount;
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }

    /**
     * Gives the player the items, but only once
     *
     * @param player the player
     */
    @Override
    public void interact(Player player) {
        System.out.println("Hello  " + player.getName());
        if (!interacted) {
            System.out.println("The dungeon is a scary place, you might need these:");
            player.addToInventory(item, itemCount);
            interacted = true;
        }
    }
}
