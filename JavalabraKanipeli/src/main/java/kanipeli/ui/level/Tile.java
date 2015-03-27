/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui.level;

import kanipeli.ui.Screen;
import kanipeli.ui.sprites.Sprite;



/**
 *
 * @author Sami
 */
public class Tile {
    public boolean impassable = false;
    int id;
    public Sprite tile;
   
    public static Tile[] tiles = new Tile[25000];
    
//    field tiles
    public static Tile grass = new GrassTile(0);
    public static Tile rock = new RockTile(1);
    public static Tile player = new PlayerTile(2);
    public static Tile boss  = new BossBattle(3);
//    battle tiles
    public static Tile playerBattle = new PlayerBattle(4);
    public static Tile critterBattle = new CritterBattle(5);
    public static Tile BattleEmpty = new BattleEmptyTile(6);
    
    public Tile(int id) {
        this.id = id;
        tiles[id] = this;   
    }
    
    public void render(int x, int y, Screen screen) {
        screen.renderSprite(x * tile.w, y * tile.h, tile);
    }
}
