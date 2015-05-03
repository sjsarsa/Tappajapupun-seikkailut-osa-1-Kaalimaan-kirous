/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui.level;

import kanipeli.ui.sprite.SpriteSheet;

/**
 *Field tile for the main boss.
 * @author Sami
 */
public class BossTile extends Tile{

    /**
     *
     * @param id
     */
    public BossTile(int id) {
        super(id);
        tile = SpriteSheet.terrain[3][0];
    }
    
}
