/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui.level;

import kanipeli.ui.sprites.SpriteSheet;

/**
 *Yay a bunny!
 * @author Sami
 */
public class PlayerTile extends Tile{

    /**
     *
     * @param id
     */
    public PlayerTile(int id) {
        super(id);
        tile = SpriteSheet.terrain[2][0];
    }
    
}
