
package kanipeli.domain;

/**
 *Can be used to inflict mayhem upon it's victim.
 * @author sjsarsa
 */
public class DamagingItem extends Item implements  Comparable<Item> {
    
    /**
     *
     * @param name item name
     * @param quantity amount of item
     * @param quality item power
     */
    public DamagingItem(String name, int quantity, int quality, int dropRate) {
        super(name, quantity, quality, dropRate);
    }
    
    /**
     *Inflicts damage to it's victim corresponding to the item's quality and
     * decreases the item's quantity by one.
     * @param creature victim
     */
    @Override
    public void use(Creature creature) {
        creature.takeDamage(super.getQuality());
        super.decreaseQuantity(1); 
    } 
}
