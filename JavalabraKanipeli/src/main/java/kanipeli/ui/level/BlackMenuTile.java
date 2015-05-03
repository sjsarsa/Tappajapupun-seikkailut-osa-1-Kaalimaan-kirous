/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui.level;

import kanipeli.ui.sprite.SpriteSheet;

/**
 *Why do I do this?
 * @author Sami
 */
public class BlackMenuTile extends Tile {
    
    /**
     *
     * @param id
     */
    public BlackMenuTile(int id) {
        super(id);
        tile = SpriteSheet.battle[4][0];
    }
}
