
package kanipeli.domain;

/**
 *
 * @author sjsarsa
 */
public class DamagingItem extends Item implements  Comparable<Item> {
    
    /**
     *
     * @param name
     * @param quantity
     * @param quality
     */
    public DamagingItem(String name, int quantity, int quality) {
        super(name, quantity, quality);
    }
    
    /**
     *
     * @param creature
     */
    @Override
    public void use(Creature creature) {
        creature.takeDamage(super.getQuality());
        super.decreaseQuantity(1); 
    } 
}
