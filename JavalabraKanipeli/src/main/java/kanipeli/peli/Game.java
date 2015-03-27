/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.peli;

import java.util.ArrayList;
import kanipeli.domain.Creature;
import kanipeli.domain.CreatureOnField;
import kanipeli.domain.PlayableCreature;
import kanipeli.ui.InputHandler;
import kanipeli.ui.UI;

/**
 *
 * @author Sami
 */
public class Game {

    private ArrayList<Field> fields;
    private UI ui;
    private InputHandler input;
    private Field field;
    private int fieldId = 0;
    private PlayableCreature player;
    private boolean alive = true;

    public Game(ArrayList<Field> fields) {
        this.fields = fields;
        this.field = fields.get(0);
        this.ui = new UI(field);
        this.input = new InputHandler(ui);
    }

    public void run() {

        while (alive) {
            ui.renderField();
            checkSpot();
            checkRandomEncounter();
        }
        for (int i = 0; i < 50; i++) {
            ui.renderGameOver();
        }
    }

    public void checkRandomEncounter() {
        if (field.getPlayer().moved && field.randomEncounter()) {
            Creature foe = field.createRandomEncounter();
            battle(foe);
        }
        field.getPlayer().moved = false;
    }

    public void checkSpot() {
        CreatureOnField cof = fields.get(fieldId).checkSpot();
        if (cof != null) {
            battle(cof);
        }
    }

    public void battle(Creature foe) {
        input.battle = true;
        Battle battle = new Battle(ui, input, field.getPlayer(), foe);
        battle.fight();
        if (battle.getLost()) {
            alive = false;
        }
        input.battle = false;
    }

//    public void fight() {
//        while (true) {
//            ui.renderBattle(battle);
//            if (input.actionSelected) {
//                selectAction();
//                input.actionSelected = false;
//                if (!battle.alive(battle.getFoe())) {
//                    break;
//                }
//                int damage = battle.attack(battle.getFoe(), player);
//                for (int i = 0; i < 100; i++) {
//                    ui.renderBattleEvent(battle, damage, 2);
//                }
//            }
//            if (!battle.alive(player)) {
//                alive = false;
//                break;
//            }
//        }
//    }
//
//    public void selectAction() {
//        if (ui.currentChoice == 0) {
//            int damage = battle.attack(player, battle.getFoe());
//            for (int i = 0; i < 100; i++) {
//                ui.renderBattleEvent(battle, damage, 0);
//            }
//        }
//        if (ui.currentChoice == 1) ;
//        if (ui.currentChoice == 2) ;
//    }
//    public void tick() {
//        if (input.up) {
//            player.moveUp();
//            if (field.randomEncounter()) {
//            input.battle = true;
//        }
//        }
//        if (input.down) {
//            player.moveDown(field.getHeight() - 1);
//            if (field.randomEncounter()) {
//            input.battle = true;
//        }
//        }
//        if (input.left) {
//            player.moveLeft();
//            if (field.randomEncounter()) {
//            input.battle = true;
//        }
//        }
//        if (input.right) {
//            player.moveRight(field.getWidth() - 1);
//            if (field.randomEncounter()) {
//            input.battle = true;
//        }
//        }
//        
//    }
}
