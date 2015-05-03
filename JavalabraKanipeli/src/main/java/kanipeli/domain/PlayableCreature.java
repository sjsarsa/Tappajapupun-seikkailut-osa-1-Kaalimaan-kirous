
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * An extension for the class CreatureOnField. A class for the main character of
 * the game. A playable creature has a level factor that determines the power of
 * the creature. It's experience differs from other creatures as it knows how
 * much experience it has and can gain experience. The creature can gain levels
 * knows how much experience it needs to level up.
 *
 * @author Sami
 */
public class PlayableCreature extends CreatureOnField {

    private int lvl = 1;
    private int exp = 0;
    private int requiredExp = 7;
    private ArrayList<Item> items = new ArrayList<Item>();
    private int state = 0; // 0 = normal, 1 = attack+, 2 = defense+
    private int normalHp = 0;

    /**
     *
     * @param fieldTile Defines creature's appearance on the field.
     * @param battleTile Defines creature's appearance when in a battle.
     * @param impassables Defines coordinates on the field for locations on
     * which the creature cannot move.
     * @param x x-coordinate on the field.
     * @param y y-coordinate on the field.
     * @param name creature's name.
     * @param maxHp creature's maximum health.
     * @param damage creatures damage factor.
     * @param exp experience points gained when this creature is defeated.
     * @param item Item to drop
     */
    public PlayableCreature(int fieldTile, int battleTile, boolean[][] impassables, int x, int y, String name, int maxHp, int damage, int exp, Item item) {
        super(fieldTile, battleTile, impassables, x, y, name, maxHp, damage, exp, item);
    }

    /**
     * Adds an item to the creatures item list if it is absent. Otherwise it
     * increases the quantity of the item-to-be-added. Finally the item list is
     * sorted.
     *
     * @param item An Item class object.
     */
    public void addItem(Item item, int amount) {
        boolean added = false;
        for (Item i : items) {
            if (i.equals(item)) {
                i.increaseQuantity(amount);
                added = true;
            }
        }
        if (!added) {
            items.add(item);
            item.increaseQuantity(amount);
        }
        Collections.sort(items);
    }
    
    public int getLvl() {
        return lvl;
    }
    
    @Override
    public int attack() {
        if (state == 1) return (int) (super.attack() * 1.2);
        else if (state == 2) return (int) (super.attack() * 0.9);
        else return super.attack();
    }
    
    @Override
    public int getDamage() {
        if (state == 1) return (int) (1.2 * super.getDamage());
        else if (state == 2) return (int) (0.9 * super.getDamage());
        else return super.getDamage();
    }
    
    @Override
    public void takeDamage(int damage) {
        if (state == 2) super.takeDamage((int) (damage * 0.85));
        else if (state == 1) super.takeDamage((int) (damage * 1.2));
        else super.takeDamage(damage);
    }
    
    /**
     * Increases experience points by given amount. If experience points exceed
     * the required amount for gaining a level the required points are reduced
     * from the creatures experience points. The required points for the next
     * level is increased by a factor of 1.5
     *
     * @param i The amount of experience about to be gained.
     * @return Indicates whether a level is gained or not.
     */
    public boolean addExp(int i) {
        exp += i;
        if (exp >= requiredExp) {
            exp -= requiredExp;
            requiredExp = (int) (requiredExp * 1.5);
            return true;
        }
        return false;
    }
    
    /**
     * Changes state by increasing the value of state variable.
     * If the value is over 2 the value is changed back to zero as there is
     * only three states.
     * If the state changes to calm, health is added depending on current max
     * health.
     * If the state changes from calm, health is changed back to normal.
     */
    
    public void changeState() {
        state += 1;
        if (state == 2) {
            normalHp = super.getMaxHp();
            super.setMaxHp((int) (super.getMaxHp() * 1.3));
            if (super.getCurrentHp() > 0) super.setCurrentHp(super.getCurrentHp() + (super.getMaxHp() - normalHp));
        }
        if (state > 2) state = 0;
        if (state == 0) {
            super.takeDamage(super.getMaxHp() - normalHp);
            super.setMaxHp(normalHp);
        }
    }

    /**
     * Gives the creature full
     * health. Increases creature's level.
     */
    public void levelUp() {
        levelUpNormal();
        super.setCurrentHp(super.getMaxHp());
        lvl++;
    }
    
    /**
     * Increases creature's maximum health and damage.
     */
    
    public void levelUpNormal() {
        super.setMaxHp((int) (super.getMaxHp() * 1.2));
        super.setDamage((int) (super.getDamage() * 1.2));
    }

    /**
     * Increases creature's damage factor considering current level.
     */
    public void levelUpDamage() {
        changeState();
        changeState();
        super.setDamage(super.getDamage() + (40 * lvl) / 3);
    }

    /**
     * Increases creature's maximum health considering current level and sets
     * the current health to maximum. 
     */
    public void levelUpHp() {
        changeState();
        super.setMaxHp(super.getMaxHp() + (150 * lvl) / 3);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int getRequiredExp() {
        return requiredExp;
    }

    public int getState() {
        return state;
    }     

    @Override
    public int getExp() {
        return exp;
    }
}
