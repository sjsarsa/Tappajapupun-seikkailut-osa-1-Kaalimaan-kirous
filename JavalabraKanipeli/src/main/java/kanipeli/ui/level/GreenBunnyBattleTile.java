/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui.level;

import kanipeli.ui.sprite.SpriteSheet;

/**
 *
 * @author Sami
 */
public class GreenBunnyBattleTile extends Tile {
    public GreenBunnyBattleTile(int id) {
        super(id);
        tile = SpriteSheet.battle[3][1];
    }
}
