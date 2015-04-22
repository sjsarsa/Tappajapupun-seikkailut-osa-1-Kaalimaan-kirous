
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.domain;

/**
 *Abstract class for items for the playableCreature.
 * @author Sami
 */
public abstract class Item implements Comparable<Item>{
    private String name;
    private int quantity;
    private int quality;
    private int dropRate;
    /**
     *
     * @param name the name of item
     * @param quantity the amount of item
     * @param quality the power of item
     * @param dropRate the rate at which the item drops
     */
    public Item(String name, int quantity, int quality, int dropRate) {
        this.name = name;
        this.quantity = quantity;
        this.quality = quality;
        this.dropRate = dropRate;
    }
    
    /**
     *
     * @param creature
     */
    abstract public void use(Creature creature);

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return name + ": " + quantity;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    public int getDropRate() {
        return dropRate;
    }
    
    
    
    /**
     *
     * @return
     */
    public int getQuantity() {
        return quantity;
    }
    
    /**
     *Increases quantity by given amount.
     * @param quantity amount
     */
    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }
    
    /**
     *Decreases quantity by given amount.
     * @param quantity amount
     */
    public void decreaseQuantity(int quantity) {
        this.quantity -= quantity;
    }

    /**
     *
     * @return
     */
    public int getQuality() {
        return quality;
    }

    /**
     *Comparison according to quality.
     * @param t comparable item
     * @return
     */
    @Override
    public int compareTo(Item t) {
        return this.quality - t.getQuality(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *Equality according to name and quality.
     * @param t comparable item
     * @return
     */
    public boolean equals(Item t) {
        if (name.equals(t.getName()) && quality == t.getQuality()) {
            return true;
        }
        return false;
    }   
}
