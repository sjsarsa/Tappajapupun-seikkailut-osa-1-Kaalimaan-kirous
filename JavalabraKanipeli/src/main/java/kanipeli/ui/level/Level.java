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
import kanipeli.ui.sprites.Sprites;

/**
 *
 * @author Sami
 */
public class Level {

    int w;
    int h;
    int blackRock =  -16777216; // rgb (0, 0, 0)
    int rock = -8355840; // rgb (128, 128, 128) ColourGrabber value
    int grass = -11731200; // rgb(76, 255, 0)
    public int[] tiles;
    private Field field;

    public Level(Field field) {                
        this.field = field;
        this.w = field.getWidth();
        this.h = field.getHeight();    
        this.tiles = new int[w * h];
    }

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
    
    public boolean[][] getImpassables() {
        boolean[][] impassables = new boolean[w][h];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (getTile(x, y).impassable) impassables[x][y] = true;
            }
        }
        return impassables;
    }
   
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= w || y >= h) {
            return Tile.rock;
        }
        return Tile.tiles[tiles[x + y * h]];
    }
    
    public void loadMap(int x0, int y0, int x1, int y1) {
        Sprite sprite = Sprites.level[x0][y0];
        for (int y = 0; y < sprite.h; y++) {
            for (int x = 0; x < sprite.w; x++) {
                tiles[x + x1 + (y + y1) * h] = Tile.grass.id;
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
