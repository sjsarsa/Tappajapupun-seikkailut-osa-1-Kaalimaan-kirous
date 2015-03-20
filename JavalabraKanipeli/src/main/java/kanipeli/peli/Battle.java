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
