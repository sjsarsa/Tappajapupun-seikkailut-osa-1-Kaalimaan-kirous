/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.domain;
import java.util.ArrayList;
/**
 *
 * @author Sami
 */
public class KehittyvaHahmo extends KartallaOlevaHahmo {
    private int lvl = 1;
    private int exp = 0;
    private int requiredExp = 5;
    private int munny = 0;
    private ArrayList<Item> items = new ArrayList<Item>();

    public KehittyvaHahmo(int x, int y, String Nimi, int maxHp, int damage, int exp) {
        super(x, y, Nimi, maxHp, damage, exp);
    }
    
    public boolean addExp(int i) {
        exp += i;
        if (exp >= requiredExp) {
            return true;
        }
        requiredExp += requiredExp * 1.75;
        return false;
    }
    
    public void levelUpDamage() {        
        super.setDamage(super.getDamage() + (5 * lvl) / 3);       
        lvl++;
        System.out.println("Tuhovoimasi kasvoi!");
    }

    public void levelUpHp() {
        super.setMaxHp(super.getMaxHp() + (15 * lvl) / 3);
        lvl++;
        System.out.println("Kestävyytesi kasvoi!");
    }
    
     public ArrayList<Item> getItems() {
        return items;
    }

    public int getMunny() {
        return munny;
    }
     
    
}
