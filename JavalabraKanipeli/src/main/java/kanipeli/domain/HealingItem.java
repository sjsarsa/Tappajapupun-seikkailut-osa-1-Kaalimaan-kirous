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
public class HealingItem extends Item implements Comparable<Item> {

    /**
     *
     * @param name
     * @param quantity
     * @param quality
     */
    public HealingItem(String name, int quantity, int quality) {
        super(name, quantity, quality);
    }

    /**
     *
     * @param creature
     */
    @Override
    public void use(Creature creature) {
        creature.setCurrentHp(creature.getCurrentHp() + super.getQuality());
        super.decreaseQuantity(1);
    }
}
