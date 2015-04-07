/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui.sprites;

/**
 *
 * @author Sami
 */
public class Sprites {
    public static Sprite[][] terrain = SpriteSheetLoader.cutFiles("/terrain.png", 16, 16);
    public static Sprite[][] level = SpriteSheetLoader.cutFiles("/level.png", 16, 16);
    public static Sprite[][] level2 = SpriteSheetLoader.cutFiles("/level2.png", 16, 16);
    public static Sprite[][] levelUpOne = SpriteSheetLoader.cutFiles("/levelUpOne.png", 16, 16);
    public static Sprite[][] levelDownOne = SpriteSheetLoader.cutFiles("/levelDownOne.png", 16, 16);
    public static Sprite[][] levelLeftOne = SpriteSheetLoader.cutFiles("/levelLeftOne.png", 16, 16);
    public static Sprite[][] levelRightOne = SpriteSheetLoader.cutFiles("/levelRightOne.png", 16, 16);
    public static Sprite[][] battle = SpriteSheetLoader.cutFiles("/battle.png", 80, 80);
}
