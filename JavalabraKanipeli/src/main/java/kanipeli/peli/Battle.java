
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.peli;

import java.util.Scanner;
import kanipeli.domain.*;

/**
 *
 * @author Sami
 */
public class Battle {

    private PlayableCreature player;
    private Creature foe;
    public boolean escaped;
    public boolean lost;

    public Battle(PlayableCreature player, Creature enemy) {
        this.player = player;
        this.foe = enemy;
        this.escaped = false;
        this.lost = false;
    }

    public void checkLevelUp() {
        if (!lost && !escaped && player.addExp(foe.getExp())) {
            levelUp();
            while (player.addExp(0)) {
                levelUp();
            }
        }
    }

    public void levelUp() {
        player.levelUpHp();
        player.levelUpDamage();
        player.levelUp();
    }

    public boolean alive(Creature creature) {
        if (creature.getCurrentHp() <= 0) {
            return false;
        }
        return true;
    }

    public int attack(Creature attacker, Creature victim) {
        int damage = attacker.attack();
        victim.takeDamage(damage);
        return damage;
    }

//    public void useItem() {    
//        for (Item i : player.getItems()) {
//               // mikä
//           //kehen
//            //peruuta
//                    i.use(player);
//                    i.use(foe);      
//        }
//    }
    public boolean getEscaped() {
        return escaped;
    }

    public boolean getLost() {
        return lost;
    }

    public PlayableCreature getPlayer() {
        return player;
    }

    public Creature getFoe() {
        return foe;
    }

}
