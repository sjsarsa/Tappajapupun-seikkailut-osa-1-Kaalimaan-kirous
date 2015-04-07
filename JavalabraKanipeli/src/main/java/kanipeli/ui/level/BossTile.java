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
public class BossTile extends Tile{

    public BossTile(int id) {
        super(id);
        tile = Sprites.terrain[3][0];
    }
    
}
