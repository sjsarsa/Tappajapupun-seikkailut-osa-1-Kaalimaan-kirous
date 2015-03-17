/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.domain;

/**
 *
 * @author Sami
 */
public class CreatureOnField extends Creature {

    private int x;
    private int y;

    public CreatureOnField(int x, int y, String name, int maxHp, int damage,  int exp) {
        super(name, maxHp, damage, exp);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveDown() {
        y++;
    }

    public void moveLeft() {
        if (x > 0) {
            x--;
        }
    }

    public void moveRight() {
        x++;
    }

    public void moveUp() {
        if (y > 0) {
            y--;
        }
    }
}
