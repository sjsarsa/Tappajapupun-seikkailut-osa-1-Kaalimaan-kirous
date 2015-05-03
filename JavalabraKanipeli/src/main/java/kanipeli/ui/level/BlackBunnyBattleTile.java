/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui.level;

import kanipeli.ui.sprite.SpriteSheet;

/**
 *A battle tile of a mean black bunny.
 * @author Sami
 */
public class BlackBunnyBattleTile extends Tile{

    /**
     *
     * @param id
     */
    public BlackBunnyBattleTile(int id) {
        super(id);
        tile = SpriteSheet.battle[0][1];
    }
}
