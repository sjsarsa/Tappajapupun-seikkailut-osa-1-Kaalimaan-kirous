
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
 *
 * @author Sami
 */
public class PlayableCreature extends CreatureOnField {

    private int lvl = 1;
    private int exp = 0;
    private int requiredExp = 5;
    private int munny = 0;
    private ArrayList<Item> items = new ArrayList<Item>();
    
    /**
     *
     * @param fieldTile
     * @param battleTile
     * @param impassables
     * @param x
     * @param y
     * @param Nimi
     * @param maxHp
     * @param damage
     * @param exp
     */
    public PlayableCreature(int fieldTile, int battleTile, boolean[][] impassables, int x, int y, String Nimi, int maxHp, int damage, int exp) {
        super(fieldTile, battleTile, impassables, x, y, Nimi, maxHp, damage, exp);
    }

    /**
     *
     * @param item
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
     *
     * @param i
     * @return
     */
    public boolean addExp(int i) {
        exp += i;
        if (exp >= requiredExp) {   
            exp -= requiredExp;
            requiredExp = (int) (requiredExp * 1.75);         
            return true;
        }
        return false;
    }
    
    /**
     *
     */
    public void levelUp() {
        super.setMaxHp((int) (super.getMaxHp() * 1.2));
        super.setDamage((int) (super.getDamage() * 1.2));
        super.setCurrentHp(super.getMaxHp());
        lvl++;
    }

    /**
     *
     */
    public void levelUpDamage() {        
        super.setDamage(super.getDamage() + (30 * lvl) / 3);        
    }
    
    /**
     *
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
    public int getMunny() {
        return munny;
    }

    /**
     *
     * @return
     */
    public int getRequiredExp() {
        return requiredExp;
    }
}
