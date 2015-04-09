
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
    
    /**
     * 
     * @param battleTile Pixels for rendering the image of this creature object.
     * @param name Quite self explanatory.
     * @param maxHp Defines creature's maximum health or in this case it's initial health in battle
     * @param damage A factor that defines how much health this creatures attack reduces.
     * @param exp the amount of experience gained when this creature is defeated.
     */
    public Creature(int battleTile, String name, int maxHp, int damage, int exp) {
        this.name = name;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.damage = damage;
        this.exp = exp;
        this.battleTile = battleTile;
    }
    /**
     *
     * @return
     */
    public int getBattleTile() {
        return battleTile;
    }
   
    /**
     *
     * @return
     */
    public int getExp() {
        return exp;
    }

    /**
     *
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

    /**
     *
     * @return
     */
    public int getCurrentHp() {
        return currentHp;
    }

    /**
     *
     * @return
     */
    public int getDamage() {
        return damage;
    }

    /**
     *
     * @return
     */
    public int getMaxHp() {
        return maxHp;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param maxHp
     */
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    /**
     *
     * @param damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     *
     * @param currentHp
     */
    public void setCurrentHp(int currentHp) {
        this.currentHp = Math.min(currentHp, maxHp);
    }
}