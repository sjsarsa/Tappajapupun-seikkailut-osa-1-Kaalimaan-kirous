
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.domain;

import java.util.ArrayList;
import java.util.Collections;
import kanipeli.peli.Field;

/**
 * An extension for the class CreatureOnField.
     * A class for the main character of the game.
     * A playable creature has a level factor that determines the power
     * of the creature. It's experience differs from other creatures as it
     * knows how much experience it has and can gain experience. The creature 
     * can gain levels knows how much experience it needs to level up.
 * @author Sami
 */
public class PlayableCreature extends CreatureOnField {

    private int lvl = 1;
    private int exp = 0;
    private int requiredExp = 5;
    private ArrayList<Item> items = new ArrayList<Item>();
    
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
     */
    public PlayableCreature(int fieldTile, int battleTile, boolean[][] impassables, int x, int y, String name, int maxHp, int damage, int exp) {
        super(fieldTile, battleTile, impassables, x, y, name, maxHp, damage, exp);
    }

    /**
     *Adds an item to the creatures item list if it is absent.
     * Otherwise it increases the quantity of the item-to-be-added.
     * Finally the item list is sorted.
     * @param item An Item class object.
     */
    public void addItem(Item item) {
        if (!items.contains(item)) {
            items.add(item);
        } else {
            for (Item i : items) {
                if (i.equals(item)) {
                    i.increaseQuantity(item.getQuantity());
                }
            }
        }
        Collections.sort(items);
    }

    /**
     *
     * @return
     */
    public int getLvl() {
        return lvl;
    }

    /**
     *Increases experience points by given amount.
     * If experience points exceed the required amount for gaining a level
     * the required points are reduced from the creatures experience points.
     * The required points for the next level is increased by a factor of 1.5
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
     *Increases creature's maximum health and damage.
     * Gives the creature full health.
     * Increases creature's level.
     */
    public void levelUp() {
        super.setMaxHp((int) (super.getMaxHp() * 1.2));
        super.setDamage((int) (super.getDamage() * 1.2));
        super.setCurrentHp(super.getMaxHp());
        lvl++;
    }

    /**
     *Increases creature's damage factor considering current level.
     */
    public void levelUpDamage() {        
        super.setDamage(super.getDamage() + (30 * lvl) / 3);        
    }
    
    /**
     *Increases creature's maximum health considering current level.
     */
    public void levelUpHp() {        
        super.setMaxHp(super.getMaxHp() + (75 * lvl) / 3);
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Item> getItems() {
        return items;
    }
  

    /**
     *
     * @return
     */
    public int getRequiredExp() {
        return requiredExp;
    }
}
