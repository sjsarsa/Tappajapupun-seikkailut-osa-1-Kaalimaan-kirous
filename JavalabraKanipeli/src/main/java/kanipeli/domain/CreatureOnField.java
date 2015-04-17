/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.domain;


/**
 *An Extension to the class Creature.
     * Has coordinates for appearing in the field and can also
     * move on the field.
 * 
 * @author Sami
 */
public class CreatureOnField extends Creature {

    private int x;
    private int y;
    private boolean[][] impassables;

    /**
     *
     */
    public boolean moved = false;
    private int fieldTile;

    /**
     * 
     * @param fieldTile Defines creature's appearance on the field.
     * @param battleTile Defines creature's appearance when in a battle.
     * @param impassables Defines coordinates on the field for locations on 
     * which the creature cannot move.
     * @param x x-coordinate on the field.
     * @param y y-coordinate on the field.
     * @param name creature's name.
     * @param maxHp creature's maximum health.
     * @param damage creatures damage factor.
     * @param exp experience points gained when this creature is defeated.
     */
    public CreatureOnField(int fieldTile, int battleTile, boolean[][] impassables, int x, int y, String name, int maxHp, int damage, int exp) {
        super(battleTile, name, maxHp, damage, exp);
        this.x = x;
        this.y = y;
        this.impassables = impassables;
        this.fieldTile = fieldTile;
    }

    /**
     *
     * @return
     */
    public int getFieldTile() {
        return fieldTile;
    }

    /**
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     *Moves one tile downward on the field if possible.
     * @param maxY The lower edge of the field.
     */
    public void moveDown(int maxY) {
        if (y < maxY && !impassables[x][y + 1]) {
            y++;
        }
        moved = true;
    }

    /**
     *Moves one tile to the left on the field if possible.
     */
    public void moveLeft() {
        if (x > 0 && !impassables[x - 1][y]) {
            x--;
        }
        moved = true;
    }

    /**
     *Moves one tile to the right on the field if possible.
     * @param maxX The edge of the field to the right.
     */
    public void moveRight(int maxX) {
        if (x < maxX && !impassables[x + 1][y]) {
            x++;
        }
        moved = true;
    }

    /**
     *Moves one tile upward on the field if possible.
     */
    public void moveUp() {
        if (y > 0 && !impassables[x][y - 1]) {
            y--;
        }
        moved = true;
    }

    /**
     *
     * @param impassables
     */
    public void setImpassables(boolean[][] impassables) {
        this.impassables = impassables;
    }
    
    
}
