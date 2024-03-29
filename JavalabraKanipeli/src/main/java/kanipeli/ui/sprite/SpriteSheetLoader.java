/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui.sprite;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.imageio.ImageIO;
import kanipeli.ui.GamePanel;

/**
 *Loads an image file and cuts it into sprites. 
 * The sprites are put in a two dimensional array.
 * @author Sami
 */
public class SpriteSheetLoader {

    /**
     *
     */
    public int[] sheetPixels;

    /**
     *
     */
    public int[] pixels;
    int x, y, sheetWidth;

    /**
     *
     */
    public SpriteSheetLoader() {
        
    }
    
    /**
     *Cuts an image file into sprites without offsets.
     * @param fileName tells which file to cut.
     * @param w the width of the cut piece from the file.
     * @param h the height of the cut piece from the file.
     * @return an array of sprites of the cut files 
     */
    public static Sprite[][] cutFiles(String fileName, int w, int h) {
        return cutFiles(fileName, w, h, 0, 0);
    }
    
    /**
     *Cuts an image file into sprites
     * @param fileName tells which file to cut.
     * @param w the width of the cut piece from the file.
     * @param h the height of the cut piece from the file.
     * @param xOffs offset for width
     * @param yOffs offset for height
     * @return an array of sprites of the cut files
     */
    public static Sprite[][] cutFiles(String fileName, int w, int h, int xOffs, int yOffs) {
        try {
            BufferedImage image = ImageIO.read(GamePanel.class.getResource(fileName));
            
            int xTiles = (image.getWidth() - xOffs) / w;
            int yTiles = (image.getHeight() - yOffs) / h;
            
            Sprite[][] result = new Sprite[xTiles][yTiles];
            
            for (int x = 0; x < xTiles; x++) {
                for (int y = 0; y < yTiles; y++) {
                    result[x][y] = new Sprite(w, h);
                    image.getRGB(xOffs + x * w, yOffs + y * h, w, h, result[x][y].pixels, 0, w);
                }
            }
            return result;
        } catch(Exception e) {
            System.err.println(e.getStackTrace());
        }
        return null;
    }

}
