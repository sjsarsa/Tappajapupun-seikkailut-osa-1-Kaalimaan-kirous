/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.domain;

/**
 *Item that heals i.e. increases creature's current health.
 * @author Sami
 */
public class HealingItem extends Item implements Comparable<Item> {

    /**
     *
     * @param name item name
     * @param quantity amount of items.
     * @param quality item power.
     */
    public HealingItem(String name, int quantity, int quality) {
        super(name, quantity, quality);
    }

    /**
     *Increases the current health of the creature this item is used upon 
     * corresponding to the items quality and decreases the item's quantity by
     * one.
     * @param creature item's target
     */
    @Override
    public void use(Creature creature) {
        creature.setCurrentHp(creature.getCurrentHp() + super.getQuality());
        super.decreaseQuantity(1);
    }
}
