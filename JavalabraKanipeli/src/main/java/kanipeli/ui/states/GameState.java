/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui.states;

/**
 *An interface for game states for the game state manager.
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
