
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.domain;

import java.util.Random;

/**
 *
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
     * @param battleTile
     * @param name
     * @param maxHp
     * @param damage
     * @param exp
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
     *
     * @param vahinko
     */
    public void takeDamage(int vahinko) {
        this.currentHp -= vahinko;
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
     * @param vahinko
     */
    public void setDamage(int vahinko) {
        this.damage = vahinko;
    }

    /**
     *
     * @param currentHp
     */
    public void setCurrentHp(int currentHp) {
        this.currentHp = Math.min(currentHp, maxHp);
        
    }
}