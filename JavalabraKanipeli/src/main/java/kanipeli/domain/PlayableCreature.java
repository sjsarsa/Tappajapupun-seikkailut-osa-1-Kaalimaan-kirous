<<<<<<< HEAD
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
    
    public PlayableCreature(boolean[][] impassables, int x, int y, String Nimi, int maxHp, int damage, int exp) {
        super(impassables, x, y, Nimi, maxHp, damage, exp);
    }

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

    public int getLvl() {
        return lvl;
    }

    public boolean addExp(int i) {
        exp += i;
        if (exp >= requiredExp) {   
            exp -= requiredExp;
            requiredExp = (int) (requiredExp * 1.75);         
            return true;
        }
        return false;
    }
    
    public void levelUp() {
        super.setMaxHp((int) (super.getMaxHp() * 1.2));
        super.setDamage((int) (super.getDamage() * 1.2));
        super.setCurrentHp(super.getMaxHp());
        lvl++;
    }

    public void levelUpDamage() {        
        super.setDamage(super.getDamage() + (30 * lvl) / 3);        
    }
    
    public void levelUpHp() {        
        super.setMaxHp(super.getMaxHp() + (75 * lvl) / 3);
    }
    
    public ArrayList<Item> getItems() {
        return items;
    }
    
    public int getMunny() {
        return munny;
    }

    public int getRequiredExp() {
        return requiredExp;
    }
    
    
    
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.domain;

import java.util.ArrayList;
import java.util.Collections;

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
    
    public PlayableCreature(int x, int y, String Nimi, int maxHp, int damage, int exp) {
        super(x, y, Nimi, maxHp, damage, exp);
    }

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

    public int getLvl() {
        return lvl;
    }

    public boolean addExp(int i) {
        exp += i;
        if (exp >= requiredExp) {   
            requiredExp += requiredExp * 1.75;
            return true;
        }
        return false;
    }
    
    public void levelUp() {
        super.setMaxHp((int) (super.getMaxHp() * 1.2));
        super.setDamage((int) (super.getDamage() * 1.2));
        super.setCurrentHp(super.getMaxHp());
        lvl++;
    }

    public void levelUpDamage() {        
        super.setDamage(super.getDamage() + (30 * lvl) / 3);        
    }
    
    public void levelUpHp() {        
        super.setMaxHp(super.getMaxHp() + (75 * lvl) / 3);
    }
    
    public ArrayList<Item> getItems() {
        return items;
    }
    
    public int getMunny() {
        return munny;
    }

    public int getRequiredExp() {
        return requiredExp;
    }
    
    
    
}
>>>>>>> 271c09424254a94913a0ab1321bfb3ae66a15532
