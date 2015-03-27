<<<<<<< HEAD
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
    
    public Creature(String name, int maxHp, int damage, int exp) {
        this.name = name;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.damage = damage;
        this.exp = exp;
    }
   
    public int getExp() {
        return exp;
    }

    public int attack() {
        return damage + rm.nextInt(damage);
    }

    public void takeDamage(int vahinko) {
        this.currentHp -= vahinko;
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

    public void setDamage(int vahinko) {
        this.damage = vahinko;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = Math.min(currentHp, maxHp);
        
    }


}
=======
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
    
    public Creature(String name, int maxHp, int damage, int exp) {
        this.name = name;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.damage = damage;
        this.exp = exp;
    }
   
    public int getExp() {
        return exp;
    }

    public int attack() {
        return damage + rm.nextInt(damage);
    }

    public void takeDamage(int vahinko) {
        this.currentHp -= vahinko;
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

    public void setDamage(int vahinko) {
        this.damage = vahinko;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = Math.min(currentHp, maxHp);
        
    }


}
>>>>>>> 271c09424254a94913a0ab1321bfb3ae66a15532
