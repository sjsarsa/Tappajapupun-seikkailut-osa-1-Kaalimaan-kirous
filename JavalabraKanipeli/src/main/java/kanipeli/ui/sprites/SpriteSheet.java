/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui.sprites;

/**
 *Contains sprite sheets that contain sprites.
 * 
 * @author Sami
 */
public class SpriteSheet {

    /**
     *
     */
    public static Sprite[][] terrain = SpriteSheetLoader.cutFiles("/graphics/terrain.png", 16, 16);

    /**
     *
     */
    public static Sprite[][] level = SpriteSheetLoader.cutFiles("/graphics/level.png", 16, 16);

    /**
     *
     */
    public static Sprite[][] level2 = SpriteSheetLoader.cutFiles("/graphics/level2.png", 16, 16);

    /**
     *
     */
    public static Sprite[][] levelUpOne = SpriteSheetLoader.cutFiles("/graphics/levelUpOne.png", 16, 16);

    /**
     *
     */
    public static Sprite[][] levelDownOne = SpriteSheetLoader.cutFiles("/graphics/levelDownOne.png", 16, 16);

    /**
     *
     */
    public static Sprite[][] levelLeftOne = SpriteSheetLoader.cutFiles("/graphics/levelLeftOne.png", 16, 16);

    /**
     *
     */
    public static Sprite[][] levelRightOne = SpriteSheetLoader.cutFiles("/graphics/levelRightOne.png", 16, 16);

    /**
     *
     */
    public static Sprite[][] battle = SpriteSheetLoader.cutFiles("/graphics/battle.png", 80, 80);
}
