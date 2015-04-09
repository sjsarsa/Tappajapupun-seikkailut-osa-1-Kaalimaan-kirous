/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui.level;

import kanipeli.ui.sprites.SpriteSheet;



/**
 *Tile for grass
 * @author Sami
 */
public class GrassTile extends Tile {

    /**
     *
     * @param id
     */
    public GrassTile(int id) {
        super(id);
        tile = SpriteSheet.terrain[0][0];
    }

}
