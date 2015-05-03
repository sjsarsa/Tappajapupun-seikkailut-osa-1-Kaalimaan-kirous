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
public class GreenBunnyTile extends Tile {
    public GreenBunnyTile(int id) {
        super(id);
        tile = SpriteSheet.terrain[8][0];
    }
}
