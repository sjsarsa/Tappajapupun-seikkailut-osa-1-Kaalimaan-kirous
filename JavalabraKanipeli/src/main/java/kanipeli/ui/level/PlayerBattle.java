/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui.level;

import kanipeli.ui.sprites.SpriteSheet;

/**
 *Main characters battle tile.
 * @author Sami
 */
public class PlayerBattle extends Tile{

    /**
     *
     * @param id
     */
    public PlayerBattle(int id) {
        super(id);

        tile = SpriteSheet.battle[0][0];
    }

}