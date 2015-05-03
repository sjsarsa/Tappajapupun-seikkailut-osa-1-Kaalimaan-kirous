/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui.states;

/**
 *An interface for game states for the game state manager.
 * For some reason bs.show() method from BufferStrategy has to be called twice
 * for anything to happen in the panel when using linux. On windows
 * everything worked fine. (This is said here because all game states call the
 * method).
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
