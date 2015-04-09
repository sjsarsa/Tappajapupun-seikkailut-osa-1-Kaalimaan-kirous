/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui.states;

/**
 *
 * @author sjsarsa
 */
public interface GameState {
    
    /**
     *
     */
    public abstract void run();

    /**
     *
     * @param k
     */
    public abstract void keyPressed(int k);
}
