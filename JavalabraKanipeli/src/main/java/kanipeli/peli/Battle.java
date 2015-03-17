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
    private Creature enemy;
    private Scanner reader;
    private boolean escaped;
    private boolean loss;

    public Battle(PlayableCreature player, Creature enemy, Scanner reader) {
        this.player = player;
        this.enemy = enemy;
        this.reader = reader;
        this.escaped = false;
        this.loss = false;
    }

    public void run() {
        while (escaped != true) {
            printSituation();

            pelaajanVuoro();
            if (enemy.getCurrentHp() <= 0) {
                System.out.println(enemy.getName());
                System.out.println("Hp: 0");
                break;
            }
            koneenVuoro();
            if (player.getCurrentHp() <= 0) {
                System.out.println(player.getName());
                System.out.println("Hp: 0");
                loss = true;
                break;
            }
        }
    }

    public void printSituation() {
        System.out.println(player.getName());
        System.out.println("Hp: " + player.getCurrentHp());
        System.out.println(enemy.getName());
        System.out.println("Hp: " + enemy.getCurrentHp());
        System.out.println("");
    }

    private String askCommand() {
        System.out.println("Anna komento: (1 = hyökkää, 2 = käytä tavara,  3 = pakene)");
        return reader.nextLine();
    }

    public void pelaajanVuoro() {
        while (true) {
            String command = askCommand();
            if (command.equals("1")) {
                int damage = player.attack();
                enemy.takeDamage(damage);
                System.out.println("Pow! " + damage + "!");
                System.out.println("");
                break;
            } else if (command.equals("2")) {
                System.out.println("Tavarat: ");
                for (Item i : player.getItems()) {
                    System.out.println(i);
                }
                System.out.println("Mitä haluaisit käyttää?");
                command  = reader.nextLine();
                for (Item i : player.getItems()) {
                    if (command.equals(i.getName())) {
                        System.out.println("Kehen? (Minä/Vihu)");
                        command = reader.nextLine();
                        if (command.equals("Minä")) {
                            i.use(player);
                            break;
                        } else {
                            i.use(enemy);
                            break;
                        }
                    }
                }
            } else if (command.equals("3")) {
                escaped = true;
                break;
            } else {
                System.out.println("Näppäilit väärin, yritä uudestaan.");
                System.out.println("");
            }
        }
    }

    public void koneenVuoro() {
            System.out.println(enemy.getName() + " hyökkää.");
            int damage = enemy.attack();
            player.takeDamage(damage);
            System.out.println("Pow! " + damage + "!");
            System.out.println(""); 
    }

    public boolean getEscaped() {
        return escaped;
    }

    public boolean getLoss() {
        return loss;
    }
}
