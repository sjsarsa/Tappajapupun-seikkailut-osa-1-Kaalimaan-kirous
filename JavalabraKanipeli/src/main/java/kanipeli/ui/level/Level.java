/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui.level;

import kanipeli.domain.CreatureOnField;
import kanipeli.peli.Field;
import kanipeli.ui.Screen;
import kanipeli.ui.sprites.Sprite;
import kanipeli.ui.sprites.SpriteSheet;

/**
 *Creates the fields layout.
 * @see FieldState
 * @author Sami
 */
public class Level {

    int w;
    int h;
    int transition = -65536; //rgb (255, 0, 0)
    int blackRock =  -16777216; // rgb (0, 0, 0)
    int rock = -8355840; // rgb (128, 128, 128) ColourGrabber value
    int grass = -11731200; // rgb(76, 255, 0)

    /**
     *
     */
    public int[] tiles;
    private Field field;

    /**
     *
     * @param field
     */
    public Level(Field field) {                
        this.field = field;
        this.w = field.getWidth();
        this.h = field.getHeight();    
        this.tiles = new int[w * h];
    }

    /**
     *
     * @param xScroll
     * @param yScroll
     * @param screen
     */
    public void renderField(int xScroll, int yScroll, Screen screen) {
        loadMap(0, 0, 0, 0);
        int xo = xScroll >> 4;
        int yo = yScroll >> 4;

        int w = (screen.w * 15) >> 4;
        int h = (screen.h * 15) >> 4;
        
//        screen.setOffSets(xScroll, yScroll);
        for (int y = yo; y <= h + yo; y++) {
            for (int x = xo; x <= w + xo; x++) {
                getTile(x, y).render(x, y, screen);
            }
        }
//        screen.setOffSets(0, 0);
    }
    
    /**
     * Creates a new two-dimensional boolean array.
     *Goes through the levels tiles and marks impassable tiles (rock tiles)
     * as true in the array.
     * @return boolean array telling what coordinates are impassable
     */
    public boolean[][] getImpassables() {
        boolean[][] impassables = new boolean[w][h];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (getTile(x, y).impassable) impassables[x][y] = true;
            }
        }
        return impassables;
    }
   
    /**
     *If tile is out of bounds,returns a rock tile, otherwise a tile on the
     * field in the specified coordinates.
     * @param x coordinate
     * @param y coordinate
     * @return tile
     */
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= w || y >= h) {
            return Tile.rock;
        }
        return Tile.tiles[tiles[x + y * h]];
    }
    
    /**
     *Loads tiles for the field according to fields sprite.
     * @param x0
     * @param y0
     * @param x1
     * @param y1
     */
    public void loadMap(int x0, int y0, int x1, int y1) {
        Sprite sprite = field.sprite;
        for (int y = 0; y < sprite.h; y++) {
            for (int x = 0; x < sprite.w; x++) {
                tiles[x + x1 + (y + y1) * h] = Tile.grass.id;
                if (sprite.pixels[x + y * sprite.h] == transition) {
                    if (x == 0) field.addExit(0, x, y);
                    if (y == 0) field.addExit(1, x, y);
                    if (x == field.getWidth() - 1) field.addExit(2, x, y);       
                    if (y == field.getHeight() - 1) field.addExit(3, x, y);
                    tiles[x + x1 + (y + y1) * h] = Tile.door.id;
                }
                if (sprite.pixels[x + y * sprite.h] == blackRock) {
                    tiles[x + x1 + (y + y1) * h] = Tile.rock.id;
                }
                if (field.getPlayer().getX() == x && field.getPlayer().getY() == y) {
                    tiles[x + x1 + (y + y1) * h] = Tile.player.id;
                }
                for (CreatureOnField cof : field.getCreaturesOnField()) {
                    if (cof.getCurrentHp() > 0 && cof.getX() == x && cof.getY() == y) {
                        tiles[x + x1 + (y + y1) * h] = Tile.tiles[cof.getFieldTile()].id;
                    }
                }           
            }
        }
    }
}
