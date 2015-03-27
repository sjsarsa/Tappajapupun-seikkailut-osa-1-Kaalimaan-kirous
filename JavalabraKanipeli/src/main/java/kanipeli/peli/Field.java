<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.peli;

import java.util.List;
import java.util.Random;
import kanipeli.domain.Creature;
import kanipeli.domain.CreatureOnField;
import kanipeli.domain.PlayableCreature;
import kanipeli.ui.level.Level;


/**
 *
 * @author Sami
 */
public class Field {

    private int width;
    private int height;
    private PlayableCreature player;
    private List<Creature> randomEncounters;
    private List<CreatureOnField> creaturesOnField;
    private boolean[][] impassables;
    public Level level;

    public Field(int width, int height, PlayableCreature player, List<CreatureOnField> creaturesOnField, List<Creature> randomEncounters) {
        this.width = width;
        this.height = height;
        this.player = player;
        this.creaturesOnField = creaturesOnField;
        this.randomEncounters = randomEncounters;
        this.level = new Level(this);
        level.loadMap(0, 0, 0, 0);
        this.impassables = level.getImpassables();
        player.setImpassables(impassables);
    }

    public List<CreatureOnField> getCreaturesOnField() {
        return creaturesOnField;
    }

    public PlayableCreature getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean[][] getImpassables() {
        return impassables;
    }

    public CreatureOnField checkSpot() {
        for (CreatureOnField h : creaturesOnField) {
            if (player.getX() == h.getX() && player.getY() == h.getY()) {
                if (h.getCurrentHp() > 0) {
                    return h;
                }
            }
        }
        return null;
    }

    public boolean randomEncounter() {
        if (checkSpot() != null) {
            return false;
        } else {
            Random rm = new Random();
            if (rm.nextInt(15) == 1) {
                return true;
            }
        }
        return false;
    }

    public Creature createRandomEncounter() {
        Random rm = new Random();
        Creature re = randomEncounters.get(rm.nextInt(randomEncounters.size()));
        return new Creature(re.getName(), re.getMaxHp(), re.getDamage(), re.getExp());
    }
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kanipeli.peli;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import kanipeli.domain.Creature;
import kanipeli.domain.CreatureOnField;
import kanipeli.domain.PlayableCreature;

/**
 *
 * @author Sami
 */
public class Field {

    private int width;
    private int height;
    private PlayableCreature player;
    private List<Creature> randomEncounters;
    private List<CreatureOnField> creaturesOnField;
    private Scanner scr;
    private boolean alive = true;

    public Field(int width, int height, PlayableCreature player, List<CreatureOnField> creaturesOnField, List<Creature> randomEncounters, Scanner scr) {
        this.width = width;
        this.height = height;
        this.scr = scr;
        this.player = player;
        this.creaturesOnField = creaturesOnField;
        this.randomEncounters = randomEncounters;
    }

    public void run() {
        while (alive) {
            printField();
            System.out.println("");
            moveOnField();
        }
    }

    public void fight(Creature foe) {
        System.out.println(foe.getName() + "kävi kimppuusi!");
        System.out.println("");
        Battle battle = new Battle(player, foe, scr);
        battle.run();
        if (battle.getLost()) {
            System.out.println("Hävisit pelin.");
            alive = false;
        } else if (battle.getEscaped()) {
            System.out.println("Lähdit pakoon senkin nössö.");

        } else {
            System.out.println("Voitit!");
        }
    }

    public void printField() {
        StringBuilder sb = new StringBuilder();
        char[][] field = new char[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                field[x][y] = '.';
            }
        }
        field[player.getX()][player.getY()] = '@';
        for (CreatureOnField m : creaturesOnField) {
            if (m.getCurrentHp() > 0) {
                field[m.getX()][m.getY()] = 'B';
            }
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                sb.append(field[x][y]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public void moveOnField() {
        System.out.println("");
        String input = scr.nextLine();

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 's') {
                if (player.getY() < height - 1) {
                    player.moveDown();
                }
                moved();
            }
            if (input.charAt(i) == 'a') {
                if (player.getX() > 0) {
                    player.moveLeft();
                }
                moved();
            }
            if (input.charAt(i) == 'w') {
                if (player.getY() > 0) {
                    player.moveUp();
                }
                moved();
            }
            if (input.charAt(i) == 'd') {
                if (player.getX() < width - 1) {
                    player.moveRight();
                }
                moved();
            }

        }
    }

    public boolean checkSpot() {
        for (CreatureOnField h : creaturesOnField) {
            if (player.getX() == h.getX() && player.getY() == h.getY()) {
                if (h.getCurrentHp() > 0) {
                    fight(h);
                    return true;
                }
            }
        }
        return false;
    }

    public void moved() {
        if (checkSpot()); else {
            Random rm = new Random();
            if (rm.nextInt(3) == 1) {
                Creature h = randomEncounters.get(rm.nextInt(randomEncounters.size()));
                fight(new Creature(h.getName(), h.getMaxHp(), h.getDamage(), h.getExp()));
            }
        }
    }

}
>>>>>>> 271c09424254a94913a0ab1321bfb3ae66a15532
