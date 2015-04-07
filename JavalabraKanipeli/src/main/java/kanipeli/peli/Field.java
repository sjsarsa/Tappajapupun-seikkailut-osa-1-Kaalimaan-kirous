
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
import kanipeli.ui.sprites.Sprite;

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
    private Field[] connectedFields;
    private int[][] exits;
    private boolean[][] impassables;
    private Game game;
    public Level level;
    public Sprite sprite;

    public Field(Game game, Sprite sprite, int width, int height, PlayableCreature player, List<CreatureOnField> creaturesOnField, List<Creature> randomEncounters) {
        this.width = width;
        this.height = height;
        this.player = player;
        this.creaturesOnField = creaturesOnField;
        this.randomEncounters = randomEncounters;
        this.game = game;
        this.connectedFields = new Field[4];
        this.exits = new int[4][2];
        this.sprite = sprite;
        this.level = new Level(this);
        level.loadMap(0, 0, 0, 0);
        this.impassables = level.getImpassables();
    }

    public void initField() {
        player.setImpassables(impassables);
    }

    public void addExit(int edge, int x, int y) {
        exits[edge][0] = x;
        exits[edge][1] = y;
    }

    public void addConnectedField(int i, Field field) {
        connectedFields[i] = field;
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
    public void checkEdge() {
        if (player.getX() == 0) {
                System.out.println("0");
                game.setCurrentField(connectedFields[0]);
                player.setX(connectedFields[0].exits[2][0]);
                player.setY(connectedFields[0].exits[2][1]);
                System.out.println("x " + player.getX());
                System.out.println("y " + player.getY());
        }
        else if (player.getY() == 0) {
//            if (connectedFields[1] != null) {
                System.out.println("1");
                game.setCurrentField(connectedFields[1]);
                player.setX(connectedFields[1].exits[3][0]);
                player.setY(connectedFields[1].exits[3][1]);
                System.out.println("x " + player.getX());
                System.out.println("y " + player.getY());
       
//            }
        }
        else if (player.getX() == width - 1) {
//            if (connectedFields[2] != null) {
                System.out.println("2");
                game.setCurrentField(connectedFields[2]);
                player.setX(connectedFields[2].exits[0][0]);
                player.setY(connectedFields[2].exits[0][1]);
                System.out.println("x " + player.getX());
                System.out.println("y " + player.getY());
          
//            }
        }
        else if (player.getY() == height - 1) {

//            if (connectedFields[3] != null) {
                System.out.println("3");
                game.setCurrentField(connectedFields[3]);
                player.setX(connectedFields[3].exits[1][0]);
                player.setY(connectedFields[3].exits[1][1]);
                System.out.println("x " + player.getX());
                System.out.println("y " + player.getY());

//            }
        }
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
        return new Creature(re.getBattleTile(), re.getName(), re.getMaxHp(), re.getDamage(), re.getExp());
    }
}
