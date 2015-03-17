/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.domain;

/**
 *
 * @author sjsarsa
 */
public class DamagingItem implements Item {
    
    private String name;
    private int quantity;
    private int quality;

    public DamagingItem(String name, int quantity, int quality) {
        this.name = name;
        this.quantity = quantity;
        this.quality = quality;
    }

    @Override
    public void use(Creature creature) {
        creature.setCurrentHp(creature.getCurrentHp() - quality);
        quantity--;
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
