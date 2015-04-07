/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.domain;

import kanipeli.peli.Field;

/**
 *
 * @author Sami
 */
public class CreatureOnField extends Creature {

    private int x;
    private int y;
    private boolean[][] impassables;
    public boolean moved = false;
    private int fieldTile;

    public CreatureOnField(int fieldTile, int battleTile, boolean[][] impassables, int x, int y, String name, int maxHp, int damage, int exp) {
        super(battleTile, name, maxHp, damage, exp);
        this.x = x;
        this.y = y;
        this.impassables = impassables;
        this.fieldTile = fieldTile;
    }

    public int getFieldTile() {
        return fieldTile;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void moveDown(int maxY) {
        if (y < maxY && !impassables[x][y + 1]) {
            y++;
        }
        moved = true;
    }

    public void moveLeft() {
        if (x > 0 && !impassables[x - 1][y]) {
            x--;
        }
        moved = true;
    }

    public void moveRight(int maxX) {
        if (x < maxX && !impassables[x + 1][y]) {
            x++;
        }
        moved = true;
    }

    public void moveUp() {
        if (y > 0 && !impassables[x][y - 1]) {
            y--;
        }
        moved = true;
    }

    public void setImpassables(boolean[][] impassables) {
        this.impassables = impassables;
    }
    
    
}
