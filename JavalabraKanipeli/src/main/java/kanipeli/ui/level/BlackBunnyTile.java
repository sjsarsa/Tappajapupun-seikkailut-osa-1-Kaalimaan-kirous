/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui.level;

import kanipeli.ui.sprite.SpriteSheet;

/**
 *Field tile of a black bunny.
 * @author Sami
 */
public class BlackBunnyTile extends Tile{

    /**
     *
     * @param id
     */
    public BlackBunnyTile(int id) {
        super(id);
        tile = SpriteSheet.terrain[4][0];
    }
}
