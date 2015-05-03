/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui.sprite;

/**
 *Sprite contains the pixels for rendering or telling the level design for
 * a field.
 * @author Sami
 */
public class Sprite {

    public int w,

    /**
     *
     */
    h;

    /**
     *
     */
    public int[] pixels;

    /**
     *Constructor of the Sprite class. Sets the sprite's dimensions.
     * @param w sprite's width
     * @param h sprite's height
     */
    public Sprite(int w, int h) {
        this.w = w;
        this.h = h;
        this.pixels = new int[w * h];
    }

    /**
     *Sets all of the pixels' colours.
     * @param colour The new colour of the pixels.
     */
    public void clear(int colour) {
        for (int i = 0;  i < pixels.length; i++) {
            pixels[i] = colour;
        }
    }

}
