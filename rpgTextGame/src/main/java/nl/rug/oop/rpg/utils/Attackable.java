package nl.rug.oop.rpg.utils;

public interface Attackable {
    int getHealth();
    int getDamage();
    void addHealth(int deltaHealth);
}
