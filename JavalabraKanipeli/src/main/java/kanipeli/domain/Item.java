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
public interface Item {
   
    abstract void use(Creature creature);
    abstract String getName();
    abstract int getQuantity();
    
}
