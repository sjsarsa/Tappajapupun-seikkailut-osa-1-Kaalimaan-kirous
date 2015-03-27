/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.peli;

import java.util.Scanner;
import kanipeli.domain.*;
import kanipeli.ui.InputHandler;
import kanipeli.ui.UI;

/**
 *
 * @author Sami
 */
public class Battle {

    private PlayableCreature player;
    private Creature foe;
    private boolean escaped;
    private boolean lost;
    private UI ui;
    private InputHandler input;

    public Battle(UI ui, InputHandler input, PlayableCreature player, Creature enemy) {
        this.player = player;
        this.foe = enemy;
        this.escaped = false;
        this.lost = false;
        this.ui = ui;
        this.input = input;
    }

    public void fight() {
        while (!escaped) {
            ui.renderBattle(this);
            if (input.actionSelected) {
                if (playerTurn()) break;
                if (enemyTurn()) break;
            }
        }
        checkLevelUp();
    }

    public boolean playerTurn() {
        selectAction();
        input.actionSelected = false;
        if (!this.alive(foe)) {
            return true;
        }
        return false;
    }

    public boolean enemyTurn() {
        int damage = attack(foe, player); //enemy turn
        for (int i = 0; i < 50; i++) {
            ui.renderBattleEvent(this, damage, 2);
        }
        if (!alive(player)) {
            lost = true;
            return true;
        }
        return false;
    }

    public void checkLevelUp() {
        if (!lost && !escaped && player.addExp(foe.getExp())) {
            levelUp();
            while (player.addExp(0)) {
                levelUp();
            }
        }
    }

    public void selectAction() {
        if (ui.currentChoice == 0) {
            int damage = this.attack(player, this.getFoe());
            for (int i = 0; i < 50; i++) {
                ui.renderBattleEvent(this, damage, 0);
            }
        }
        if (ui.currentChoice == 1) ;
        if (ui.currentChoice == 2) {
            escaped = true;
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
