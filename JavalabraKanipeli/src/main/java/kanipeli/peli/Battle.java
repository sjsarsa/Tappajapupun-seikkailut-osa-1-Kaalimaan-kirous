
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.peli;

import kanipeli.domain.Creature;
import kanipeli.domain.PlayableCreature;


/**
 *Contains the logic for battles
 * @author Sami
 */
public class Battle {

    private PlayableCreature player;
    private Creature foe;
    private boolean escaped;
    private boolean lost;

    /**
     *
     * @param player 
     * @param enemy
     */
    public Battle(PlayableCreature player, Creature enemy) {
        this.player = player;
        this.foe = enemy;
        this.escaped = false;
        this.lost = false;
    }

    /**
     *Checks first if player hasn't lost or escaped and then adds experience to
     * player according to the defeated enemy. If enough experience points are
     * added calls for level up as long as experience points suffice.
     */
    public void checkLevelUp() {
        if (!lost && !escaped && player.addExp(foe.getExp())) {
            levelUp();
            while (player.addExp(0)) {
                levelUp();
            }
        }
    }

    /**
     *Going to change, hopefully to the better.
     */
    public void levelUp() {
        player.levelUpHp();
        player.levelUpDamage();
        player.levelUp();
    }

    /**
     *Checks whether a creature has any health left.
     * @param creature yes, it's a creature
     * @return true if still alive, else false
     */
    public boolean alive(Creature creature) {
        if (creature.getCurrentHp() <= 0) {
            return false;
        }
        return true;
    }

    /**
     *Inflicts damage upon the victim according to the damage factor of the
     * attacker.
     * @param attacker damage dealer
     * @param victim damage receiver
     * @return the amount of damage inflicted
     */
    public int attack(Creature attacker, Creature victim) {
        int damage = attacker.attack();
        victim.takeDamage(damage);
        if (!alive(player)) lost = true;
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

    public void setEscaped(boolean escaped) {
        this.escaped = escaped;
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
