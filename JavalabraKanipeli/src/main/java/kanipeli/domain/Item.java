<<<<<<< HEAD
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
    
    public Item(String name, int quantity, int quality) {
        this.name = name;
        this.quantity = quantity;
        this.quality = quality;
    }
    
    abstract public void use(Creature creature);
    @Override
    public String toString() {
        return name + ": " + quantity;
    }

    public String getName() {
        return name;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
   
    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }
    
    public void decreaseQuantity(int quantity) {
        this.quantity -= quantity;
    }

    public int getQuality() {
        return quality;
    }

    @Override
    public int compareTo(Item t) {
        return this.quality - t.getQuality(); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean equals(Item i) {
        if (name.equals(i.getName()) && quality == i.getQuality()) {
            return true;
        }
        return false;
    }
    
    
}
=======
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
    
    public Item(String name, int quantity, int quality) {
        this.name = name;
        this.quantity = quantity;
        this.quality = quality;
    }
    
    abstract public void use(Creature creature);
    @Override
    public String toString() {
        return name + ": " + quantity;
    }

    public String getName() {
        return name;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
   
    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }
    
    public void decreaseQuantity(int quantity) {
        this.quantity -= quantity;
    }

    public int getQuality() {
        return quality;
    }

    @Override
    public int compareTo(Item t) {
        return this.quality - t.getQuality(); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean equals(Item i) {
        if (name.equals(i.getName()) && quality == i.getQuality()) {
            return true;
        }
        return false;
    }
    
    
}
>>>>>>> 271c09424254a94913a0ab1321bfb3ae66a15532
