
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.domain;

import java.util.Random;

/**

 * Class for basic creatures appearing as random encounters in the game.
 * @author sjsarsa
 */
public class Creature {

    private String name;
    private int maxHp;
    private int currentHp;
    private int damage;
    private int exp;
    private Random rm = new Random();
    private int battleTile;
    private Item item;
    /**
     * 
     * @param battleTile Pixels for rendering the image of this creature object.
     * @param name Quite self explanatory.
     * @param maxHp Defines creature's maximum health or in this case it's initial health in battle
     * @param damage A factor that defines how much health this creatures attack reduces.
     * @param exp the amount of experience gained when this creature is defeated.
     * @param item an item to drop.
     */
    public Creature(int battleTile, String name, int maxHp, int damage, int exp, Item item) {
        this.name = name;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.damage = damage;
        this.exp = exp;
        this.battleTile = battleTile;
        this.item = item;
    }

    public int getBattleTile() {
        return battleTile;
    }
   
    public int getExp() {
        return exp;
    }

    /**
     *returns a sum of creatures damage plus random amount of 0-damage
     * @return
     */
    public int attack() {
        return damage + rm.nextInt(damage);
    }

    /**
     *Reduces creature's current health according to damage taken.
     * @param damage the damage taken by this creature.
     */
    public void takeDamage(int damage) {
        this.currentHp -= damage;
        if (currentHp < 0) currentHp = 0;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public int getDamage() {
        return damage;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public String getName() {
        return name;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = Math.min(currentHp, maxHp);
    }

    public Item getItem() {
        return item;
    }
    
    
}