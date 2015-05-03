/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui;

import kanipeli.ui.sprites.Sprite;



/**
 *Contains the pixels for rendering. 
 * Sets the colours of pixels of a sprite to the screen in a specified place
 * @author Sami
 */
public class Screen {


    public int w,

    /**
     *width and height of the screen.
     */
    h;
    int xOffSet = 0, yOffSet = 0;

    /**
     *array for pixels
     */
    public int pixels[];

    /**
     *Constructor
     * @param w width
     * @param h height
     */
    public Screen(int w, int h) {
        this.w = w;
        this.h = h;
        pixels = new int[w * h];
    }

    /**
     *Sets the colours of pixels of a sprite to the screen in a specified place
     * @param xPos float for width
     * @param yPos float for height
     * @param sprite 
     */
    public void renderSprite(int xPos, int yPos, Sprite sprite) {
//        loader.grabTile(tile, width, height);
        int height = sprite.h;
        int width = sprite.w;
        xPos -= xOffSet;
        yPos -= yOffSet;
        for (int y = 0; y < height; y++) {
            if (yPos + y < 0 || yPos + y >= h) {
                continue;
            }
            for (int x = 0; x < width; x++) {
                if (xPos + x < 0 || xPos + x >= w) {
                    continue;
                }
                int col = sprite.pixels[x + (y * height)];
                if (col != 65281 && col < 0) {
                    pixels[(x + xPos) + (y + yPos) * w] = col;
                }
            }
        }
    }

//    public void setOffSets(int xOffSet, int yOffSet) {
//        this.xOffSet = xOffSet;
//        this.yOffSet = yOffSet;
//    }
    
}
