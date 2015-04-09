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
public class PlayerBattle extends Tile{

    /**
     *
     * @param id
     */
    public PlayerBattle(int id) {
        super(id);

        tile = Sprites.battle[0][0];
    }

}