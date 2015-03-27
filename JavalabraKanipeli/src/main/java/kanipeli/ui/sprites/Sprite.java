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
public class Sprite {

    public int w, h;
    public int[] pixels;

    public Sprite(int w, int h) {
        this.w = w;
        this.h = h;
        this.pixels = new int[w * h];
    }

    public void clear(int colour) {
        for (int i = 0;  i < pixels.length; i++) {
            pixels[i] = colour;
        }
    }

}
