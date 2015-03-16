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
public class Hahmo {

    private String Nimi;
    private int maxHp;
    private int currentHp;
    private int damage;
    private int exp;
    private Random rm = new Random();

    public Hahmo(String Nimi, int maxHp, int damage, int exp) {
        this.Nimi = Nimi;
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

    public String getNimi() {
        return Nimi;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setDamage(int vahinko) {
        this.damage = vahinko;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }


}
