/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui.level;

import kanipeli.ui.sprites.SpriteSheet;

/**
 *
 * @author Sami
 */
public class RedBunnyBattleTile extends Tile {
    public RedBunnyBattleTile(int id) {
        super(id);
        tile = SpriteSheet.battle[4][1];
    }
}
