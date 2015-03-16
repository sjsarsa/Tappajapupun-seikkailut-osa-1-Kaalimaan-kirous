/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.domain;

/**
 *
 * @author Sami
 */
public class HealingItem implements Item {
    
    private String name;
    private int quantity;
    private int quality;

    public HealingItem(String name, int quantity, int quality) {
        this.name = name;
        this.quantity = quantity;
        this.quality = quality;
    }

    @Override
    public void use(Hahmo hahmo) {
        hahmo.setCurrentHp(hahmo.getCurrentHp() + quality);
        hahmo.setCurrentHp(Math.max(hahmo.getCurrentHp(), hahmo.getMaxHp()));
    }

    @Override
    public String toString() {
        return name + ": " + quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuality() {
        return quality;
    }

    public int getQuantity() {
        return quantity;
    }
    
    
    
    
    
}
