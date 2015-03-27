
package kanipeli.domain;

/**
 *
 * @author sjsarsa
 */
public class DamagingItem extends Item implements  Comparable<Item> {
    
    
    public DamagingItem(String name, int quantity, int quality) {
        super(name, quantity, quality);
    }
    
    @Override
    public void use(Creature creature) {
        creature.takeDamage(super.getQuality());
        super.decreaseQuantity(1); 
    } 
}
