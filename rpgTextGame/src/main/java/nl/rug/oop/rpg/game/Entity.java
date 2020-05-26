package nl.rug.oop.rpg.game;

import java.io.Serializable;

abstract public class Entity implements Serializable {
    private static final long serialVersionUID = 5L;
    protected int health;
    protected int damage;
    protected String name;
}
