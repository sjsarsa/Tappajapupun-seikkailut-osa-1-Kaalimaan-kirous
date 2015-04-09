/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui.level;

import kanipeli.ui.sprites.Sprites;

/**
 *
 * @author Sami
 */
public class RockTile extends Tile {

    /**
     *
     * @param id
     */
    public RockTile(int id) {
        super(id);
        super.impassable = true;
        tile = Sprites.terrain[1][0];
    }

}
