<<<<<<< HEAD
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
=======
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
    private Scanner scr;
    private boolean escaped;
    private boolean lost;

    public Battle(PlayableCreature player, Creature enemy, Scanner scr) {
        this.player = player;
        this.foe = enemy;
        this.scr = scr;
        this.escaped = false;
        this.lost = false;
    }

    public void run() {
        while (escaped != true) {
            battleStats();
            playerTurn();
            if (!alive(foe)) {
                break;
            }
            enemyTurn();
            if (!alive(player)) {
                lost = true;
                break;
            }
        }
        if (!lost && !escaped && player.addExp(foe.getExp())) {
            levelUp();
            while (player.addExp(0)) { //Jos saa kerralla törkiästi exp voi saada monta leveliä.
                levelUp();
            }
        }
    }

    public void levelUp() {
        System.out.println("Level up!");
        System.out.println("Haluatko kehittää kestävyyttä (= 1) vai tuhovoimaa (= 2)?");
        System.out.println("");
        while (true) {
            String input = scr.nextLine();
            if (input.equals("1")) {
                player.levelUpHp();
                System.out.println("Sait lisää Hp:ta");
                break;
            } else if (input.equals("2")) {
                player.levelUpDamage();
                System.out.println("Tuhovoimasi kasvoi!");
                break;
            }
        }
        player.levelUp();
    }

    private boolean alive(Creature creature) {
        if (creature.getCurrentHp() <= 0) {
            System.out.println(creature.getName());
            System.out.println("Hp: 0");
            return false;
        }
        return true;
    }

    private void battleStats() {
        System.out.println(player.getName());
        System.out.println("Hp: " + player.getCurrentHp());
        System.out.println(foe.getName());
        System.out.println("Hp: " + foe.getCurrentHp());
        System.out.println("");
    }

    private void attack(Creature attacker, Creature victim) {
        int damage = attacker.attack();
        victim.takeDamage(damage);
        System.out.println("Pow! " + damage + "!");
        System.out.println("");
    }

    private boolean useItem() {
        System.out.println("Tavarat: ");
        for (Item i : player.getItems()) {
            System.out.println(i);
        }
        System.out.println("Mitä haluaisit käyttää?");
        String input = scr.nextLine();
        for (Item i : player.getItems()) {
            if (input.equals(i.getName())) {
                System.out.println("Kehen? (Minä/Vihu)");
                input = scr.nextLine();
                if (input.equals("Minä")) {
                    i.use(player);
                    return true;
                } else {
                    i.use(foe);
                    return true;
                }
            }
        }
        System.out.println("Sinulla ei ole kyseistä tuotetta.");
        return false;
    }

    private void playerTurn() {
        while (true) {
            System.out.println("Anna komento: (1 = hyökkää, 2 = käytä tavara,  3 = pakene)");
            String input = scr.nextLine();
            if (input.equals("1")) {
                attack(player, foe);
                break;
            } else if (input.equals("2")) {
                if (useItem()) {
                    break;
                }
            } else if (input.equals("3")) {
                escaped = true;
                break;
            } else {
                System.out.println("Näppäilit väärin, yritä uudestaan.");
                System.out.println("");
            }
        }
    }

    private void enemyTurn() {
        System.out.println(foe.getName() + " hyökkää.");
        attack(foe, player);
    }

    public boolean getEscaped() {
        return escaped;
    }

    public boolean getLost() {
        return lost;
    }
}
>>>>>>> 271c09424254a94913a0ab1321bfb3ae66a15532
