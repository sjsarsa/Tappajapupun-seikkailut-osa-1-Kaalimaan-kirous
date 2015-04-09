
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
public abstract class Item implements Comparable<Item>{
    private String name;
    private int quantity;
    private int quality;
    
    /**
     *
     * @param name
     * @param quantity
     * @param quality
     */
    public Item(String name, int quantity, int quality) {
        this.name = name;
        this.quantity = quantity;
        this.quality = quality;
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
    
    /**
     *
     * @return
     */
    public int getQuantity() {
        return quantity;
    }
    
    /**
     *
     * @param quantity
     */
    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }
    
    /**
     *
     * @param quantity
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
     *
     * @param t
     * @return
     */
    @Override
    public int compareTo(Item t) {
        return this.quality - t.getQuality(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param i
     * @return
     */
    public boolean equals(Item i) {
        if (name.equals(i.getName()) && quality == i.getQuality()) {
            return true;
        }
        return false;
    }   
}
