package nl.rug.oop.rpg.game;

import nl.rug.oop.rpg.utils.Usable;

public class Equipment extends Item implements Usable {
    private static final long serialVersionUID = 6L;

    private int slot;
    private int stat;
    private boolean toggled;

    /**
     * Constructor
     *
     * @param name Equipment name
     * @param description Equipment description
     * @param slot describes the slot of the equipment, 0 is the weapon, 1 is the armor
     * @param stat describes the amount of extra damage or health
     */
    //init
    public Equipment(String name, String description, int slot, int stat) {
        super(name,description);
        this.slot = slot;
        this.stat = stat;
        toggled = false;
    }

    /**
     * Lets the player use equipment
     * @param entity describes the entity which gets the extra stats (in this case only the player)
     */
    @Override
    public void use(Entity entity) {
        if (entity instanceof Player) {
            Player player = (Player)entity;
            player.toggleEquipment(this,slot,stat);
        }
    }

    public boolean isToggled(){
        return toggled;
    }

    /**
     * Toggles whether the armor is equipped or not
     */
    public void toggle(){
        toggled = !toggled;
    }
}