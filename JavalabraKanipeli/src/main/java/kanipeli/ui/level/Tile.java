/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.ui.level;

import kanipeli.ui.Screen;
import kanipeli.ui.sprites.Sprite;
import kanipeli.ui.sprites.SpriteSheet;

/**
 * A superclass for tiles containing all actual tiles for use. Tiles contain a
 * sprite
 *
 * @see Sprite
 * @author Sami
 */
public class Tile {

    public boolean impassable = false;
    int id;

    public Sprite tile;

    public static Tile[] tiles = new Tile[25000];

    public static Tile grass = new GrassTile(0);
    public static Tile rock = new RockTile(1);
    public static Tile door = new DoorTile(2);
    public static Tile duck = new DuckTile(3);
    public static Tile player = new PlayerTile(4);
    public static Tile critter = new CritterBattleTile(5);
    public static Tile boss = new BossTile(5);
    public static Tile blackBunny = new BlackBunnyTile(6);
    public static Tile redBunny = new RedBunnyTile(7);
    public static Tile greenBunny = new GreenBunnyTile(8);
    public static Tile purpleBunny = new PurpleBunnyTile(9);

//    battle tiles
    public static Tile menuBlack = new BlackMenuTile(10);
    public static Tile battleEmpty = new BattleEmptyTile(11);
    public static Tile playerBattle = new PlayerBattle(12);
    public static Tile critterBattle = new CritterBattleTile(13);
    public static Tile duckBattle = new DuckBattleTile(14);
    public static Tile blackBunnyBattle = new BlackBunnyBattleTile(15);
    public static Tile redBunnyBattle = new RedBunnyBattleTile(16);
    public static Tile greenBunnyBattle = new GreenBunnyBattleTile(17);
    public static Tile purpleBunnyBattle = new PurpleBunnyBattleTile(18);   
    public static Tile bossBattle = new BossBattleTile(19);

    /**
     *
     * @param id
     */
    public Tile(int id) {
        this.id = id;
        tiles[id] = this;
    }

    /**
     * Renders pixels for the screen.
     *
     * @param x
     * @param y
     * @param screen
     */
    public void render(int x, int y, Screen screen) {
        screen.renderSprite(x * tile.w, y * tile.h, tile);
    }

}
