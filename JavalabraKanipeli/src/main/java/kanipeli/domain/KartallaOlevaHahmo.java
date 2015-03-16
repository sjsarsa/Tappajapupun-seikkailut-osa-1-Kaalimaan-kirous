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
public class KartallaOlevaHahmo extends Hahmo {

    private int x;
    private int y;

    public KartallaOlevaHahmo(int x, int y, String Nimi, int maxHp, int vahinko,  int exp) {
        super(Nimi, maxHp, vahinko, exp);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void liikuAlas() {
        y++;
    }

    public void liikuVasen() {
        if (x > 0) {
            x--;
        }
    }

    public void liikuOikea() {
        x++;
    }

    public void liikuYlos() {
        if (y > 0) {
            y--;
        }
    }

}
